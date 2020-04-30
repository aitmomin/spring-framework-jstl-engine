package org.Mcloud.bq.metier;

import java.util.List;

import org.Mcloud.bq.entities.*;

public interface BanqueRemote {
	public Client addClient(Client c);
	public Employe addEmploye(Employe e,Long idEmployeSup);
	public Groupe addGroupe(Groupe g);
	public void addEmplyeToGroupe(Long IdEmploye, Long IdGroupe);
	public Compte addCompte(Compte c, Long IdClient, Long IdEmploye);
	public Operation addOperation(Operation op, Long IdCompte, Long IdEmploye);
	public void verser(Compte c, double mt, Long IdEmploye);
	public void retrait(Compte c, double mt, Long IdEmploye);
	public void virement(Compte c1, Compte c2,double mt, Long IdEmploye);
	public Compte findCompte(Long IdCompte);
	public List<Compte> listCompteByClient(Long IdClient);
	public List<Compte> listCompteByEmploye(Long IdEmploye);
	public List<Operation> listOperations(Long IdCompte,int position, int nbOperations);
	public Client findClient(Long IdClient);
	public List<Client> listClients(String mc);
	public Employe findEmploye(Long IdEmploye);
	public Groupe findGroupe(Long IdGroupe);
	public List<Employe> listEmployes();
	public List<Groupe> listGroupes();
	public List<Employe> listEmployesByGroupe(Long IdGroupe);
	public long getNombreOperations(Long numCompte);
}
