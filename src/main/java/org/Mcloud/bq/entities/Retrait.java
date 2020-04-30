package org.Mcloud.bq.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation{
	
	private static final long serialVersionUID = 8L;

	public Retrait() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Retrait(Date dateOperation, double montant) {
		super(dateOperation, montant);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Retrait";
	}
}
