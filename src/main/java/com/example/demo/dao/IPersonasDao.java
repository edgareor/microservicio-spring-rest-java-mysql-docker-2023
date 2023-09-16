package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.Persona;

public interface IPersonasDao extends JpaRepository<Persona, Long> {

	@Procedure
	public double PROMEDIO_PERSONAS(int start, int end);

	@Query(nativeQuery = true, value = "SELECT sql10646261.CALCULAR_AREA(:radio)")
	public double CALCULAR_AREA(@Param("radio") float radio);

	@Query(nativeQuery = true, value = "SELECT * FROM sql10646261.personas ORDER BY p_age DESC;")
	public List<Persona> getAllDescByAge();
}
