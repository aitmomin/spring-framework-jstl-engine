package org.Mcloud.bq.models;

import java.util.List;

import org.Mcloud.bq.entities.Compte;

public class RechercheCompteModel {
	private Long idClient;
	private List<Compte>comptes;
	private String erreur;
	public Long getIdClient() {
		return idClient;
	}
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}
	public List<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
	public String getErreur() {
		return erreur;
	}
	public void setErreur(String erreur) {
		this.erreur = erreur;
	}
	
}
