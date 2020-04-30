package org.Mcloud.bq.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name="Groupes")
public class Groupe implements Serializable{

	private static final long serialVersionUID = 6L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idGroupe;
	private String nomGroupe;
	@ManyToMany(mappedBy="groupes",fetch=FetchType.LAZY)
	private Collection<Employe>employes;
	public Groupe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Groupe(String nomGroupe) {
		super();
		this.nomGroupe = nomGroupe;
	}
	public Long getIdGroupe() {
		return idGroupe;
	}
	public void setIdGroupe(Long idGroupe) {
		this.idGroupe = idGroupe;
	}
	public String getNomGroupe() {
		return nomGroupe;
	}
	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}
	public Collection<Employe> getEmployes() {
		return employes;
	}
	public void setEmployes(Collection<Employe> employes) {
		this.employes = employes;
	}
	

}
