package org.Mcloud.bq.models;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.Mcloud.bq.entities.Compte;
import org.Mcloud.bq.entities.Operation;

public class ConsulteCompteModel {
	@NotNull
	@Min(1)
	private Long idCompte=1L;
	private Compte c;
	private String typeCompte;
	private List<Operation>ops;
	private String erreur;
	private int nbPages;
	private int nbEnregistrements=3;
	private int page=0;
	public String getTypeCompte() {
		return typeCompte;
	}
	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}
	public List<Operation> getOps() {
		return ops;
	}
	public void setOps(List<Operation> ops) {
		this.ops = ops;
	}
	public String getErreur() {
		return erreur;
	}
	public void setErreur(String erreur) {
		this.erreur = erreur;
	}
	public Long getIdCompte() {
		return idCompte;
	}
	public void setIdCompte(Long idCompte) {
		this.idCompte = idCompte;
	}
	public Compte getC() {
		return c;
	}
	public void setC(Compte c) {
		this.c = c;
	}
	public int getNbPages() {
		return nbPages;
	}
	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}
	public int getNbEnregistrements() {
		return nbEnregistrements;
	}
	public void setNbEnregistrements(int nbEnregistrements) {
		this.nbEnregistrements = nbEnregistrements;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
}
