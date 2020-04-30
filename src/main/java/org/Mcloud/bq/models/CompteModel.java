package org.Mcloud.bq.models;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//@Pattern(regexp = "[a-zA-Z0-9 ]")
public class CompteModel {
	private String typeCompte="CC";
	@Min(100)
	@Digits(fraction = 2, integer = 10)
	private double montant;
	private double tauxInteret;
	private double decouvert;
	@NotNull
	private Long idClient;
	@NotNull
	private Long idEmploye;
	private String erreur;
	
	public String getErreur() {
		return erreur;
	}
	public void setErreur(String erreur) {
		this.erreur = erreur;
	}
	public String getTypeCompte() {
		return typeCompte;
	}
	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public double getTauxInteret() {
		return tauxInteret;
	}
	public void setTauxInteret(double tauxInteret) {
		this.tauxInteret = tauxInteret;
	}
	public double getDecouvert() {
		return decouvert;
	}
	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	public Long getIdClient() {
		return idClient;
	}
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}
	public Long getIdEmploye() {
		return idEmploye;
	}
	public void setIdEmploye(Long idEmploye) {
		this.idEmploye = idEmploye;
	}
}
