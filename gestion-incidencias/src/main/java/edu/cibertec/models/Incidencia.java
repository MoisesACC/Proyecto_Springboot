package edu.cibertec.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Incidencia {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codIncidencia;

    @Column(length = 100, nullable = false)
    private String descripcion;

    @Column(name = "fechaRegistro")
    private LocalDateTime fechaRegistro;

    @Column(name = "nivelPrioridad", nullable = false, length = 10)
    private String nivelPrioridad;
    
    @Column(length = 15, nullable = false)
    private String categoria;
    
    @Column(length = 20, nullable = false)
    private String estado = "No Procesada";
    
    //Encargado del Reporte
    @ManyToOne
    @JoinColumn(name = "codEmpleado", nullable = false)
    private Empleado empleado;
    
    //Encargado del Resgistro
    
	public Incidencia(Long codIncidencia, String descripcion, LocalDateTime fechaRegistro, String nivelPrioridad,
			String categoria, String estado, Empleado empleado) {
		super();
		this.codIncidencia = codIncidencia;
		this.descripcion = descripcion;
		this.fechaRegistro = fechaRegistro;
		this.nivelPrioridad = nivelPrioridad;
		this.categoria = categoria;
		this.estado = estado;
		this.empleado = empleado;
	}

	public Incidencia() {
		super();
	}

	public Long getCodIncidencia() {
		return codIncidencia;
	}

	public void setCodIncidencia(Long codIncidencia) {
		this.codIncidencia = codIncidencia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getNivelPrioridad() {
		return nivelPrioridad;
	}

	public void setNivelPrioridad(String nivelPrioridad) {
		this.nivelPrioridad = nivelPrioridad;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
    
    
}
