package edu.cibertec.models;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
// @Table(name = "Empleado")
public class Empleado {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codEmpleado;
	
	@Column(length = 50)
    private String nombre;

    @Column(length = 50)
    private String apellido;

    @Column(length = 50)
    private String email;

    @Column(name = "fechaNac")
    //@Temporal(TemporalType.DATE)
    private LocalDate fechaNac;

    @Column(length = 50)
    private String direccion;

    @Column(length = 50)
    private String tipoEmp;

    @ManyToOne
    @JoinColumn(name = "codDepartamento", nullable = false)
    private Departamento departamento;

	public Empleado(Long codEmpleado, String nombre, String apellido, String email, LocalDate fechaNac,
			String direccion, String tipoEmp, Departamento departamento) {
		super();
		this.codEmpleado = codEmpleado;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNac = fechaNac;
		this.direccion = direccion;
		this.tipoEmp = tipoEmp;
		this.departamento = departamento;
	}

	public Empleado() {
		super();
	}

	public Long getCodEmpleado() {
		return codEmpleado;
	}

	public void setCodEmpleado(Long codEmpleado) {
		this.codEmpleado = codEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTipoEmp() {
		return tipoEmp;
	}

	public void setTipoEmp(String tipoEmp) {
		this.tipoEmp = tipoEmp;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
    public int getEdad() {
        return Period.between(this.fechaNac, LocalDate.now()).getYears();
    }
}
