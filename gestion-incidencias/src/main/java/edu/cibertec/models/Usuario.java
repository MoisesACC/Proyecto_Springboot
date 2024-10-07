package edu.cibertec.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codUsu;

    @Column(length = 20)
    private String logUsu;

    @Column(length = 20)
    private String pasUsu;

    @Column(length = 50)
    private String nomUsu;

    @Column(length = 1)
    private String estUsu;

    @ManyToOne
    @JoinColumn(name = "codDepartamento", nullable = false)
    private Departamento departamento;

    @OneToMany(mappedBy = "usuario")
    private Set<Acceso> accesos;

    // Getters and Setters
    public int getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(int codUsu) {
        this.codUsu = codUsu;
    }

    public String getLogUsu() {
        return logUsu;
    }

    public void setLogUsu(String logUsu) {
        this.logUsu = logUsu;
    }

    public String getPasUsu() {
        return pasUsu;
    }

    public void setPasUsu(String pasUsu) {
        this.pasUsu = pasUsu;
    }

    public String getNomUsu() {
        return nomUsu;
    }

    public void setNomUsu(String nomUsu) {
        this.nomUsu = nomUsu;
    }

    public String getEstUsu() {
        return estUsu;
    }

    public void setEstUsu(String estUsu) {
        this.estUsu = estUsu;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Set<Acceso> getAccesos() {
        return accesos;
    }

    public void setAccesos(Set<Acceso> accesos) {
        this.accesos = accesos;
    }
}
