package org.Mcloud.bq.models;

import org.hibernate.validator.constraints.NotEmpty;

public class GroupeModel {
	@NotEmpty
	private String nomGroupe;
	public String getNomGroupe() {
		return nomGroupe;
	}
	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}

}
