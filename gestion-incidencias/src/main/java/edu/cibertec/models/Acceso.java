package edu.cibertec.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Acceso")
@IdClass(AccesoId.class)
public class Acceso {

    @Id
    private int codMenu;

    @Id
    private int codUsu;

    @ManyToOne
    @JoinColumn(name = "codMenu", insertable = false, updatable = false)
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "codUsu", insertable = false, updatable = false)
    private Usuario usuario;
    
    
    
    
    
    // Getters and Setters
    public int getCodMenu() {
        return codMenu;
    }

    public void setCodMenu(int codMenu) {
        this.codMenu = codMenu;
    }

    public int getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(int codUsu) {
        this.codUsu = codUsu;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
