package edu.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.cibertec.models.Incidencia;

@Repository
public interface IncidenciaRepositorio extends JpaRepository<Incidencia, Long> {
	@Query("Select i from Incidencia i where i.descripcion like %?1%"
            + "OR str(i.nivelPrioridad) like %?1%")
	public List<Incidencia> findAll(String palabraClave);
	
    List<Incidencia> findByEstado(String estado);
}
