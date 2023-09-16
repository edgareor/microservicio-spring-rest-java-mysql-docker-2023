package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.IPersonasDao;
import com.example.demo.dto.Persona;

@Service
@Transactional
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	private IPersonasDao per;

	@Override
	public List<Persona> getPersonas() {
		List<Persona> lista = per.findAll();
		System.out.println(lista.toString());
		return lista;
	}

	@Override
	public boolean exists(long id) {
		return per.existsById(id);
	}

	@Override
	public void save(Persona input) {
		per.save(input);
	}

	@Override
	public List<Persona> findById(long id) {
		List<Long> ids = new ArrayList<>();
		ids.add(id);
		List<Persona> persona = per.findAllById(ids);
		return persona;
	}

	@Override
	public void delete(long id) {
		per.deleteById(id);
	}

	@Override
	public void deleteByPersona(Persona input) {
		per.delete(input);
	}

	@Override
	public double storeProcedure(int start, int end) {
		double result = per.PROMEDIO_PERSONAS(start, end);
		return result;
	}

	@Override
	public double function(float radio) {
		double result = per.CALCULAR_AREA(radio);
		return result;
	}

	@Override
	public List<Persona> getAllDescByAge() {
		List<Persona> lista = per.getAllDescByAge();
		return lista;
	}
}
