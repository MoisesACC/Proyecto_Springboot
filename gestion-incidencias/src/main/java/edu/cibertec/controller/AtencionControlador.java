package edu.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import edu.cibertec.models.Atencion;
import edu.cibertec.models.Empleado;
import edu.cibertec.models.Incidencia;
import edu.cibertec.services.AtencionServicio;
import edu.cibertec.services.EmpleadoServicio;
import edu.cibertec.services.IncidenciaServicio;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AtencionControlador {
    @Autowired
    private EmpleadoServicio empleadoServicio;

    @Autowired
    private AtencionServicio atencionServicio;
    
    @Autowired
    private IncidenciaServicio incidenciaServicio;

    @RequestMapping("/ate")
    public String listarAtenciones(Model model) {
        List<Atencion> listaAtencion = atencionServicio.listAtenciones();
        List<Empleado> listaEmpleado = empleadoServicio.listaEmp();
        for (Empleado empleado : listaEmpleado) {
            String[] nombres = empleado.getNombre().split(" ");
            String[] apellidos = empleado.getApellido().split(" ");
            if (nombres.length > 0) {
                empleado.setNombre(nombres[0]); // Solo el primer nombre
            }
            if (apellidos.length > 0) {
                empleado.setApellido(apellidos[0]); // Solo el primer apellido
            }
        }
        model.addAttribute("listaAtencion", listaAtencion);
        return "indexAte";
    }

    @RequestMapping("/nuevoAte")
    public String mostrarFormularioDeRegistrar(Model model) {
        Atencion atencion = new Atencion();
        List<Incidencia> incidenciasNoProcesadas = incidenciaServicio.obtenerIncidenciasNoProcesadas();
        model.addAttribute("empleadosTecnicos", empleadoServicio.obtenerEmpleadosTecnicos());
        model.addAttribute("incidencias", incidenciasNoProcesadas);
        model.addAttribute("atencion", atencion);
        return "nueva_atencion";
    }
    
    @RequestMapping("/atencion/detalle/{id}")
    public String detalleAtencion(@PathVariable(name="id") Long id, Model model) {
        Atencion atencion = atencionServicio.get(id);
        model.addAttribute("atencion", atencion);
        return "detalle_atencion";
    }
    
    @RequestMapping(value="/guardarAte", method=RequestMethod.POST)
    public String guardarAtencion(@ModelAttribute("atencion") Atencion atencion) {
        // Verificar si es una nueva atención (sin ID) o una edición (con ID)
        boolean esNuevaAtencion = atencion.getCodAtencion() == null;

        if (esNuevaAtencion) {
            // Obtener la incidencia asociada a la nueva atención
            Incidencia incidencia = atencion.getIncidencia();
            atencion.setFechaAtencion(LocalDateTime.now());
            // Cambiar el estado de la incidencia a "Procesada"
            incidencia.setEstado("Procesada");
            incidenciaServicio.save(incidencia);
        } else {
            // Si es una edición, asegurar que la fecha de atención ya existe
            Atencion atencionExistente = atencionServicio.get(atencion.getCodAtencion());
            atencion.setFechaAtencion(atencionExistente.getFechaAtencion());
        }

        atencionServicio.saveAtencion(atencion);
        return "redirect:/ate";
    }

	@RequestMapping("/editarAte/{id}")
	public ModelAndView mostrarFormularioDeEditar(@PathVariable(name="id") Long id) {
		ModelAndView modelo = new ModelAndView("editar_atencion");
		
		Atencion atencion = atencionServicio.get(id);
		modelo.addObject("atencion", atencion);
		return modelo;
	}

	@RequestMapping("/eliminarAte/{id}")
	public String eliminarAtencion(@PathVariable(name="id") Long id) {
        Atencion atencion = atencionServicio.get(id);
        if (atencion != null) {
            Incidencia incidencia = atencion.getIncidencia();
            atencionServicio.deleteAtencionById(id);
            incidencia.setEstado("No Procesada");
            incidenciaServicio.save(incidencia);
        }
        return "redirect:/ate";
    }
	
}