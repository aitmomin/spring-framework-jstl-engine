package org.Mcloud.bq.models;

import java.util.List;

import org.Mcloud.bq.entities.Client;

public class RechercheClientModel {
	private String nomClient;
	private List<Client> clients;
	private String erreur;
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public List<Client> getClients() {
		return clients;
	}
	public void setClients(List<Client> list) {
		this.clients = list;
	}
	public String getErreur() {
		return erreur;
	}
	public void setErreur(String erreur) {
		this.erreur = erreur;
	}
	
}
