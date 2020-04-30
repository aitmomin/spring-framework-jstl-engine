package org.Mcloud.bq.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {

	private static final long serialVersionUID = 3L;
	private double decourevt;
	public double getDecourevt() {
		return decourevt;
	}
	public void setDecourevt(double decourevt) {
		this.decourevt = decourevt;
	}
	public CompteCourant(Date dateCreation, double solde, double decourevt) {
		super(dateCreation, solde);
		this.decourevt = decourevt;
	}
	public CompteCourant() {
		super();
	}
	@Override
	public String toString() {
		return "Compte courant";
	}
	
}
