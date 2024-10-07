package edu.cibertec.models;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codMen;

    @Column(length = 30)
    private String desMen;

    @Column(length = 400)
    private String urlMen;

    @OneToMany(mappedBy = "menu")
    private Set<Acceso> accesos;

    // Getters and Setters
    public int getCodMen() {
        return codMen;
    }

    public void setCodMen(int codMen) {
        this.codMen = codMen;
    }

    public String getDesMen() {
        return desMen;
    }

    public void setDesMen(String desMen) {
        this.desMen = desMen;
    }

    public String getUrlMen() {
        return urlMen;
    }

    public void setUrlMen(String urlMen) {
        this.urlMen = urlMen;
    }

    public Set<Acceso> getAccesos() {
        return accesos;
    }

    public void setAccesos(Set<Acceso> accesos) {
        this.accesos = accesos;
    }
}
