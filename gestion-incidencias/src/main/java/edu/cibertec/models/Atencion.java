package edu.cibertec.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "Atencion")
public class Atencion {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codAtencion;
	
    @Column(nullable = false, length = 250)
    private String acciones;
	
	@Column(nullable = false)
    private LocalDateTime fechaAtencion;
    
	@Column(nullable = false, length = 60)
	private float presupuesto;
	
    @Column(nullable = false)
    private String detallePresupuesto;
	
    @Column(nullable = false, length = 250)
    private String observaciones;
    
    @ManyToOne
    @JoinColumn(name = "codEmpleado", nullable = false)
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "codIncidencia", nullable = false)
    private Incidencia incidencia;
    
    public Atencion(Long codAtencion, String acciones, LocalDateTime fechaAtencion, float presupuesto, String detallePresupuesto,
			String observaciones, Empleado empleado, Incidencia incidencia) {
		super();
		this.codAtencion = codAtencion;
		this.acciones = acciones;
		this.fechaAtencion = fechaAtencion;
		this.presupuesto = presupuesto;
		this.detallePresupuesto = detallePresupuesto;
		this.observaciones = observaciones;
		this.empleado = empleado;
		this.incidencia = incidencia;
	}
    
	public Atencion() {
		super();
	}

	public Long getCodAtencion() {
		return codAtencion;
	}

	public void setCodAtencion(Long codAtencion) {
		this.codAtencion = codAtencion;
	}

	public String getAcciones() {
		return acciones;
	}

	public void setAcciones(String acciones) {
		this.acciones = acciones;
	}

	public LocalDateTime getFechaAtencion() {
		return fechaAtencion;
	}

	public void setFechaAtencion(LocalDateTime fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}

	public float getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(float presupuesto) {
		this.presupuesto = presupuesto;
	}

	public String getDetallePresupuesto() {
		return detallePresupuesto;
	}

	public void setDetallePresupuesto(String detallePresupuesto) {
		this.detallePresupuesto = detallePresupuesto;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Incidencia getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}
	
}