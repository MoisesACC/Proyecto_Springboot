package edu.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.cibertec.models.Atencion;

@Repository
public interface AtencionRepositorio extends JpaRepository<Atencion, Long> {
	
}