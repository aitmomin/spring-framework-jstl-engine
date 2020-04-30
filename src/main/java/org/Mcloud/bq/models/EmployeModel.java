package org.Mcloud.bq.models;

import org.hibernate.validator.constraints.NotEmpty;

public class EmployeModel {
	@NotEmpty
	private String nomEmploye;
	private Long idEmploye;
	private String erreur;
	
	public String getErreur() {
		return erreur;
	}
	public void setErreur(String erreur) {
		this.erreur = erreur;
	}
	public String getNomEmploye() {
		return nomEmploye;
	}
	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}
	public Long getIdEmploye() {
		return idEmploye;
	}
	public void setIdEmploye(Long idEmploye) {
		this.idEmploye = idEmploye;
	}
	

}
