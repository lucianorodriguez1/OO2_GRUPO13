package com.oo2.grupo13.dtos;

import java.util.ArrayList;
import java.util.List;

public class EspecialidadDTO {

	 	private long id;
	    private String nombre;
	    private List<SoporteDTO> soportes = new ArrayList<SoporteDTO>();
	    
	    public EspecialidadDTO(String nombre) {
	        this.nombre = nombre;
	    }
	    public EspecialidadDTO() {} 

	    public long getId() {
	        return id;
	    }
	    public void setId(long id) {
	        this.id = id;
	    }
	    public String getNombre() {
	        return nombre;
	    }
	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }
		public List<SoporteDTO> getSoportes() {
			return soportes;
		}
		public void setSoportes(List<SoporteDTO> soportes) {
			this.soportes = soportes;
		}
}



