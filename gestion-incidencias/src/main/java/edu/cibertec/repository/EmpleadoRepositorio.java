package edu.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.cibertec.models.Empleado;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {
	@Query("Select e from Empleado e where e.nombre like %?1%"
			+ "OR e.apellido like %?1%")
	public List<Empleado> findAll(String palabraClave);
	
	List<Empleado> findByTipoEmp(String tipoEmp);
}

