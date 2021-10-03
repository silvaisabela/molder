package br.com.sistema.molde.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_AREA_PROFISSIONAL")
public class AreaProfissional {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id_area_profissional;
	String ds_area;
	
	public int getId_area_profissional() {
		return id_area_profissional;
	}
	public void setId_area_profissional(int id_area_profissional) {
		this.id_area_profissional = id_area_profissional;
	}
	public String getDs_area() {
		return ds_area;
	}
	public void setDs_area(String ds_area) {
		this.ds_area = ds_area;
	}
	
	
}
