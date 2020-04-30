package org.Mcloud.bq.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="Employes")
public class Employe implements Serializable{

	private static final long serialVersionUID = 5L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEmploye;
	private String nomEmploye;
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(name="IdEmployeSup")
	private Employe emplSup;
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Employe_Groupe",
	joinColumns= @JoinColumn(name="idEmploye"),
    inverseJoinColumns=@JoinColumn(name="idGroupe")
     )
	private Collection<Groupe> groupes;
	public Collection<Groupe> getGroupes() {
		return groupes;
	}
	public void setGroupes(Collection<Groupe> groupes) {
		this.groupes = groupes;
	}
	public Employe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employe(String nomEmploye) {
		super();
		this.nomEmploye = nomEmploye;
	}
	public Long getIdEmploye() {
		return idEmploye;
	}
	public void setIdEmploye(Long idEmploye) {
		this.idEmploye = idEmploye;
	}
	public String getNomEmploye() {
		return nomEmploye;
	}
	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}
	public Employe getEmplSup() {
		return emplSup;
	}
	public void setEmplSup(Employe emplSup) {
		this.emplSup = emplSup;
	}
}
