package org.Mcloud.bq.test;

import java.util.Date;

import org.Mcloud.bq.metier.BanqueRemote;
import org.Mcloud.bq.entities.Client;
import org.Mcloud.bq.entities.Compte;
import org.Mcloud.bq.entities.CompteCourant;
import org.Mcloud.bq.entities.CompteEpargne;
import org.Mcloud.bq.entities.Employe;
import org.Mcloud.bq.entities.Groupe;
import org.Mcloud.bq.entities.Versement;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context=
				new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		BanqueRemote remote=(BanqueRemote) context.getBean("metier");
		Client c=remote.addClient(new Client("Cl2"));
		remote.addClient(new Client("Cl1"));
		Groupe g=remote.addGroupe(new Groupe("G1"));
		remote.addGroupe(new Groupe("G2"));
		Employe e=remote.addEmploye(new Employe("Mbarki"), null);
		c=remote.findClient(1L);
		remote.addEmploye(new Employe("kawakada"),e.getIdEmploye());
		e=remote.findEmploye(1L);
		remote.addEmplyeToGroupe(e.getIdEmploye(), g.getIdGroupe());
		Compte com1=remote.addCompte(new CompteCourant(new Date(),100000, 10000), c.getIdClient(), e.getIdEmploye());
		Compte com2=remote.addCompte(new CompteEpargne(new Date(),100000, 5), c.getIdClient(), e.getIdEmploye());
		remote.addOperation(new Versement(new Date(),1222), 1L, 1L);
		remote.virement(com1, com2, 120, 1L);
		System.out.println(c.getNomClient());

	}

}
