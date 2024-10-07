package edu.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.cibertec.models.Empleado;
import edu.cibertec.services.EmpleadoServicio;

@Controller
public class EmpleadoControlador {
	
	@Autowired
	private EmpleadoServicio empleadoServicio;
	
	@RequestMapping("/emp")
	public String verPaginaDeInicio(Model model, @Param("palabraClave") String palabraClave) {
		List<Empleado> listaEmpleado = empleadoServicio.findAll(palabraClave);
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
		model.addAttribute("listaEmpleado", listaEmpleado);
		model.addAttribute("palabraClave", palabraClave);
		return "indexEmp";
	}
	
	@RequestMapping("/empleado/detalle/{id}")
	public String detalleEmpleado(@PathVariable(name="id") Long id, Model model) {
	    Empleado empleado = empleadoServicio.get(id);
	    model.addAttribute("empleado", empleado);
	    return "detalle_empleado";
	}
	
	@RequestMapping("/nuevoEmp")
	public String mostrarFormularioDeRegistrar(Model model) {
		Empleado empleado = new Empleado();
		model.addAttribute("empleado", empleado);
		return "nuevo_empleado";
	}
	
	@RequestMapping(value="/guardarEmp", method=RequestMethod.POST)
	public String guardarEmpleado(@ModelAttribute("empleado") Empleado empleado) {
		empleadoServicio.save(empleado);
		return "redirect:/emp";
    }
	
	@RequestMapping("/editarEmp/{id}")
	public ModelAndView mostrarFormularioDeEditar(@PathVariable(name="id") Long id) {
		ModelAndView modelo = new ModelAndView("editar_empleado");
		
		Empleado empleado = empleadoServicio.get(id);
		modelo.addObject("empleado", empleado);
		return modelo;
	}
	
	@RequestMapping("/eliminar/{id}")
	public String eliminarEmpleado(@PathVariable(name="id") Long id) {
		empleadoServicio.delete(id);
		return "redirect:/emp";
	}
}