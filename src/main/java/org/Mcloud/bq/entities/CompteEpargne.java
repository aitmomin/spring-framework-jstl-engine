package org.Mcloud.bq.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte{
	private static final long serialVersionUID = 4L;
	private double taux;
	public double getTaux() {
		return taux;
	}
	public void setTaux(double taux) {
		this.taux = taux;
	}
	public CompteEpargne(Date dateCreation, double solde, double taux) {
		super(dateCreation, solde);
		this.taux = taux;
	}
	public CompteEpargne() {
		super();
	}
	@Override
	public String toString() {
		return "Compte epargne";
	}
	
}
