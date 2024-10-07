package edu.cibertec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cibertec.models.Incidencia;
import edu.cibertec.repository.IncidenciaRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class IncidenciaServicio {

    @Autowired
    private IncidenciaRepositorio incidenciaRepositorio;
    
	public List<Incidencia> findAll(String palabraClave) {
		if(palabraClave !=null) {
			return incidenciaRepositorio.findAll(palabraClave);
		}
		return incidenciaRepositorio.findAll();
	}
	
	public List<Incidencia> findAll() {
		return incidenciaRepositorio.findAll();
	}
	
	public Optional<Incidencia> findById(Long id) {
		return incidenciaRepositorio.findById(id);
	}
    
	public void save(Incidencia incidencia) {
		incidenciaRepositorio.save(incidencia);
	}
	
	public Incidencia get(Long id) {
		return incidenciaRepositorio.findById(id).get();
	}
	
	public void delete(Long id) {
		incidenciaRepositorio.deleteById(id);
	}
	
    public List<Incidencia> obtenerIncidenciasNoProcesadas() {
        return incidenciaRepositorio.findByEstado("No Procesada");
    }
}