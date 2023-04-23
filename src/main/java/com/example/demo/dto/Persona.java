package com.example.demo.dto;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "personas")
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull(message = "La pkPersona es obligatoria")
	@Min(value = 1, message = "El ID mínimo es 1")
	private Long pkPersona;

	@NotBlank(message = "La propiedad nombre es obligatoria")
	@Size(min = 2, max = 20, message = "El nombre debe tener entre 2 a 20 caracteres")
	private String pNombre;

	@NotBlank(message = "La propiedad apellido es obligatoria")
	@Size(min = 2, max = 20, message = "El apellido debe tener entre 2 a 20 caracteres")
	private String pApellido;

	@NotNull(message = "La edad es obligatoria")
	@Min(value = 0, message = "La edad mínima es 0")
	@Max(value = 100, message = "La edad máxima es 100")
	private Integer pAge;

	public Persona() {
	}

	public Persona(long pkPersona, String pNombre, String pApellido, int pAge) {
		super();
		this.pkPersona = pkPersona;
		this.pNombre = pNombre;
		this.pApellido = pApellido;
		this.pAge = pAge;
	}

	public Long getPkPersona() {
		return pkPersona;
	}

	public void setPkPersona(long pkPersona) {
		this.pkPersona = pkPersona;
	}

	public String getPNombre() {
		return pNombre;
	}

	public void setPNombre(String pNombre) {
		this.pNombre = pNombre;
	}

	public String getPApellido() {
		return pApellido;
	}

	public void setPApellido(String pApellido) {
		this.pApellido = pApellido;
	}

	public Integer getPAge() {
		return pAge;
	}

	public void setPAge(int pAge) {
		this.pAge = pAge;
	}

	@Override
	public String toString() {
		return "Persona [pkPersona=" + pkPersona + ", pNombre=" + pNombre + ", pApellido=" + pApellido + ", pAge="
				+ pAge + "]";
	}

}
