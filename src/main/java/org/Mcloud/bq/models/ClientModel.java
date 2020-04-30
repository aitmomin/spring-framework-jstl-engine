package org.Mcloud.bq.models;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ClientModel {
	@NotEmpty
	@Size(min=3,max=30)
	private String nomClient;
	private String erreur;
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getErreur() {
		return erreur;
	}
	public void setErreur(String erreur) {
		this.erreur = erreur;
	}

}
