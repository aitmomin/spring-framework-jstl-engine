package org.Mcloud.bq.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TypeCompte",discriminatorType=DiscriminatorType.STRING, length=4)
@DiscriminatorValue("CO")
public class Compte implements Serializable{
	
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numCompte;
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	private double solde;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IdEmploye")
	private Employe employe;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IdClient")
	private Client client;
	@OneToMany(mappedBy="compte",fetch=FetchType.LAZY)
	private Collection<Operation> operations;
	public void setClient(Client client) {
		this.client = client;
	}
	public Client getClient() {
		return client;
	}
	public void setIdClient(Client client) {
		this.client = client;
	}
	public Compte(Date dateCreation, double solde) {
		super();
		this.dateCreation = dateCreation;
		this.solde = solde;
	}
	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getNumCompte() {
		return numCompte;
	}
	public void setNumCompte(Long numCompte) {
		this.numCompte = numCompte;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public Employe getEmploye() {
		return employe;
	}
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	public Collection<Operation> getOperations() {
		return operations;
	}
	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}
}
