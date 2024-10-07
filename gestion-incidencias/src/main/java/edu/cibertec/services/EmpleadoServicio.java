package edu.cibertec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cibertec.models.Empleado;
import edu.cibertec.repository.EmpleadoRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServicio {
    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;
    
	public List<Empleado> findAll(String palabraClave) {
		if(palabraClave !=null) {
			return empleadoRepositorio.findAll(palabraClave);
		}
		return empleadoRepositorio.findAll();
	}
	
	public List<Empleado> listaEmp() {
		return empleadoRepositorio.findAll();
	}
	
    public Optional<Empleado> findById(Long id) {
        return empleadoRepositorio.findById(id);
    }
    
	public void save(Empleado empleado) {
		empleadoRepositorio.save(empleado);
	}
	//
	public Empleado get(Long id) {
		return empleadoRepositorio.findById(id).get();
	}
	
	public Empleado obtenerEmpleadoPorCodEmpleado(Long id) {
        return empleadoRepositorio.findById(id).orElse(null);
    }
	//
	public void delete(Long id) {
		empleadoRepositorio.deleteById(id);
	}
	
    public List<Empleado> obtenerEmpleadosTecnicos() {
        return empleadoRepositorio.findByTipoEmp("Tecnico");
    }
}