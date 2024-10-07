package edu.cibertec.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.cibertec.models.Atencion;
import edu.cibertec.models.Incidencia;
import edu.cibertec.services.IncidenciaServicio;

@Controller
public class IncidenciaControlador {
	
	@Autowired
	private IncidenciaServicio incidenciaServicio;
	
	@RequestMapping("/inc")
	public String verPaginaDeInicio(Model model, @Param("palabraClave") String palabraClave) {
		List<Incidencia> listaIncidencia = incidenciaServicio.findAll(palabraClave);
		model.addAttribute("listaIncidencia", listaIncidencia);
		model.addAttribute("palabraClave", palabraClave);
		return "indexInc";
	}
	
    @GetMapping("/listarIncidencias")
    public ModelAndView listarIncidencias() {
        ModelAndView modelAndView = new ModelAndView("lista_incidencias");
        List<Incidencia> incidencias = incidenciaServicio.findAll();
        modelAndView.addObject("incidencias", incidencias);
        return modelAndView;
    }
    
    @RequestMapping("/incidencia/detalle/{id}")
    public String detalleIncidencia(@PathVariable(name="id") Long id, Model model) {
        Incidencia incidencia = incidenciaServicio.get(id);
        model.addAttribute("incidencia", incidencia);
        return "detalle_incidencia";
    }
	
	@RequestMapping("/nuevoInc")
	public String mostrarFormularioDeRegistrar(Model model) {
		Incidencia incidencia = new Incidencia();
		model.addAttribute("incidencia", incidencia);
		return "nueva_incidencia";
	}
	
	@RequestMapping(value="/guardarInc", method=RequestMethod.POST)
	public String guardarIncidencia(@ModelAttribute("incidencia") Incidencia incidencia) {
		
        // Verificar si es una nueva atención (sin ID) o una edición (con ID)
        boolean esNuevaIncidencia = incidencia.getCodIncidencia() == null;
        
        if(esNuevaIncidencia) {
    		incidencia.setFechaRegistro(LocalDateTime.now());
        }else {
            Incidencia incidenciaExistente = incidenciaServicio.get(incidencia.getCodIncidencia());
            incidencia.setFechaRegistro(incidenciaExistente.getFechaRegistro());
        }
		incidenciaServicio.save(incidencia);
		return "redirect:/inc";
	}
	
	@RequestMapping("/editarInc/{id}")
	public ModelAndView mostrarFormularioDeEditar(@PathVariable(name="id") Long id) {
		ModelAndView modelo = new ModelAndView("editar_incidencia");
		
		Incidencia incidencia = incidenciaServicio.get(id);
		modelo.addObject("incidencia", incidencia);
		return modelo;
	}
	
	@RequestMapping("/eliminarInc/{id}")
	public String eliminarIncidencia(@PathVariable(name="id") Long id) {
		incidenciaServicio.delete(id);
		return "redirect:/inc";
	}
}