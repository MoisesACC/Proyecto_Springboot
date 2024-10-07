package edu.cibertec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cibertec.models.Atencion;
import edu.cibertec.repository.AtencionRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class AtencionServicio {

    @Autowired
    private AtencionRepositorio atencionRepositorio;

    public List<Atencion> listAtenciones() {
        return atencionRepositorio.findAll();
    }

    public Optional<Atencion> findAtencionById(Long id) {
        return atencionRepositorio.findById(id);
    }

    public Atencion saveAtencion(Atencion atencion) {
        return atencionRepositorio.save(atencion);
    }
    
	public Atencion get(Long id) {
		return atencionRepositorio.findById(id).get();
	}
    public void deleteAtencionById(Long id) {
        atencionRepositorio.deleteById(id);
    }
}