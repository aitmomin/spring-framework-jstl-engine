package org.Mcloud.bq.metier;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.Mcloud.bq.entities.Client;
import org.Mcloud.bq.entities.Compte;
import org.Mcloud.bq.entities.CompteCourant;
import org.Mcloud.bq.entities.Employe;
import org.Mcloud.bq.entities.Groupe;
import org.Mcloud.bq.entities.Operation;
import org.Mcloud.bq.entities.Retrait;
import org.Mcloud.bq.entities.Versement;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BanqueBean implements BanqueRemote{
	@PersistenceContext
	EntityManager em;
	@Override
	public Client addClient(Client c) {
		em.persist(c);
		return c;
	}

	@Override
	public Employe addEmploye(Employe e, Long idEmployeSup) {
		if(idEmployeSup!=null)
		{
			Employe emSup=em.find(Employe.class,idEmployeSup);
			if(emSup==null)
				throw new RuntimeException("Employe superieur introuvable");
			e.setEmplSup(emSup);
		}
		em.persist(e);
		return e;
	}

	@Override
	public Groupe addGroupe(Groupe g) {
		em.persist(g);
		return g;
	}

	@Override
	public void addEmplyeToGroupe(Long IdEmploye, Long IdGroupe) {
		Employe e=findEmploye(IdEmploye);//em.find(Employe.class,IdEmploye);//
		Groupe g=findGroupe(IdGroupe);//em.find(Groupe.class, IdGroupe);//
		g.getEmployes().add(e);
		e.getGroupes().add(g);
	}

	@Override
	public Compte addCompte(Compte c, Long IdClient, Long IdEmploye) {
		if(IdClient==null)
			throw new RuntimeException("Id de client ne doit pas être null");
		Client cl=em.find(Client.class, IdClient);//findClient(IdClient);
		if(cl==null)
			throw new RuntimeException("Client introuvable !!");
		c.setClient(cl);
		if(IdEmploye!=null)
		{
			Employe emp=em.find(Employe.class, IdEmploye);//findEmploye(IdEmploye);
			if(emp==null)
				throw new RuntimeException("Employé introuvable !!");
			c.setEmploye(emp);
		}
		em.persist(c);
		return c;
	}

	@Override
	public Operation addOperation(Operation op, Long IdCompte, Long IdEmploye) {
		Compte cp=findCompte(IdCompte);//em.find(Compte.class, IdCompte);//
		Employe e=findEmploye(IdEmploye);//em.find(Employe.class, IdEmploye);//
		op.setCompte(cp);
		op.setEmploye(e);
		em.persist(op);
		return null;
	}

	@Override
	public void verser(Compte cc, double mt, Long IdEmploye) {
		Versement vr=new Versement(new Date(), mt);
		vr.setCompte(cc);
		Employe e=findEmploye(IdEmploye);//em.find(Employe.class, IdEmploye);//
		vr.setEmploye(e);
		em.persist(vr);
		Compte c=findCompte(cc.getNumCompte());
		c.setSolde(c.getSolde()+mt);		
	}

	@Override
	public void retrait(Compte cc, double mt, Long IdEmploye) {
		Compte c=findCompte(cc.getNumCompte());
		if(c.getSolde()>mt)
		{
			Retrait re=new Retrait(new Date(), mt);
			re.setCompte(c);
			Employe e=findEmploye(IdEmploye);//em.find(Employe.class,IdEmploye);//
			re.setEmploye(e);
			em.persist(re);
			c.setSolde(c.getSolde()-mt);
		}
		else
		{
			if(c instanceof CompteCourant)
			{
				CompteCourant ccou=(CompteCourant)c;
				if(ccou.getDecourevt()+ccou.getSolde()-mt>0)
				{
					Retrait re=new Retrait(new Date(), mt);
					re.setCompte(c);
					Employe e=findEmploye(IdEmploye);//em.find(Employe.class,IdEmploye);//
					re.setEmploye(e);
					em.persist(re);
					c.setSolde(c.getSolde()-mt);
				}
				else
				{
					throw new RuntimeException("Solde insuffessent");
				}
			}
			else
			{
				throw new RuntimeException("Solde insuffessent");
			}
			
		}	
	}

	@Override
	public void virement(Compte c1, Compte c2, double mt, Long IdEmploye) {
		
		retrait(c1, mt, IdEmploye);
		verser(c2, mt, IdEmploye);
		
	}

	@Override
	public Compte findCompte(Long IdCompte) {
		Compte c=em.find(Compte.class, IdCompte);
		if(c==null)
			throw new RuntimeException("compte introuvable!");
		c.getClient().getNomClient();
		c.getEmploye().getNomEmploye();
		return c;
	}

	@Override
	public List<Compte> listCompteByClient(Long IdClient) {
		Query req=em.createQuery("select c from Compte c where c.client.idClient=:x");
		req.setParameter("x", IdClient);
		return req.getResultList();
	}

	@Override
	public List<Compte> listCompteByEmploye(Long IdEmploye) {
		Query req=em.createQuery("select c from Compte c where c.employe.idEmploye=:x");
		req.setParameter("x", IdEmploye);
		return req.getResultList();
	}

	@Override
	public List<Operation> listOperations(Long IdCompte,int position, int nbOperations) {
		Query req=em.createQuery("select o from Operation o where o.compte.numCompte=:x order by o.dateOperation desc");
		req.setParameter("x", IdCompte);
		req.setFirstResult(position);
		req.setMaxResults(nbOperations);
		return req.getResultList();
	}

	@Override
	public Client findClient(Long IdClient) {
		Client c=em.find(Client.class, IdClient);
		if(c==null)
			throw new RuntimeException("client introuvable!");
		return c;
	}

	@Override
	public List<Client> listClients(String mc) {
		Query req=em.createQuery("select c from Client c where c.nomClient like :mc");
		req.setParameter("mc", "%"+mc+"%");
		return req.getResultList();
	}

	@Override
	public Employe findEmploye(Long IdEmploye) {
		Employe e=em.find(Employe.class, IdEmploye);
		if(e==null)
			throw new RuntimeException("employe introuvable!");
		return e;
	}

	@Override
	public List<Employe> listEmployes() {
		Query req=em.createQuery("select e from Employe e");
		return req.getResultList();
	}

	@Override
	public List<Groupe> listGroupes() {
		Query req=em.createQuery("select g from Groupe g");
		return req.getResultList();
	}

	@Override
	public List<Employe> listEmployesByGroupe(Long IdGroupe) {
		Query req=em.createQuery("select e from Employe e where e.groupes.idGroupe=:x");
		req.setParameter("x", IdGroupe);
		return req.getResultList();
	}

	@Override
	public Groupe findGroupe(Long IdGroupe) {
		Groupe g=em.find(Groupe.class, IdGroupe);
		if(g==null)
			throw new RuntimeException("groupe introuvable!");
		return g;
	}

	@Override
	public long getNombreOperations(Long numCompte) {
		Query req=em.createQuery("select count(o) from Operation o where o.compte.numCompte=:x order by o.dateOperation desc");
		req.setParameter("x", numCompte);
		long n=(Long) req.getResultList().get(0);
		return n;
	}

}
