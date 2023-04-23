package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Persona;

public interface IPersonasDao extends JpaRepository<Persona, Long> {
}
