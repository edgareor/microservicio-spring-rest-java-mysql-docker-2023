package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.Persona;

public interface IPersonaService {
	public List<Persona> getPersonas();

	public void save(Persona per);

	public List<Persona> findById(long id);

	public void delete(long id);

	public void deleteByPersona(Persona per);

	public boolean exists(long id);
}
