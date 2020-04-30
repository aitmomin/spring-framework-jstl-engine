package org.Mcloud.bq.models;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OperationModel {
	private String typeOperation="VER";
	@Min(100)
	@Digits(fraction = 2, integer = 10)
	private double montant;
	@NotNull
	private Long idCompte;
	private Long idCompte2;
	@NotNull
	@Min(1)
	private Long idEmploye;
	private String erreur;
	public String getTypeOperation() {
		return typeOperation;
	}
	public void setTypeOperation(String typeOperation) {
		this.typeOperation = typeOperation;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public Long getIdCompte() {
		return idCompte;
	}
	public void setIdCompte(Long idCompte) {
		this.idCompte = idCompte;
	}
	public Long getIdCompte2() {
		return idCompte2;
	}
	public void setIdCompte2(Long idCompte2) {
		this.idCompte2 = idCompte2;
	}
	public Long getIdEmploye() {
		return idEmploye;
	}
	public void setIdEmploye(Long idEmploye) {
		this.idEmploye = idEmploye;
	}
	public String getErreur() {
		return erreur;
	}
	public void setErreur(String erreur) {
		this.erreur = erreur;
	}
	

}
