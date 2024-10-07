package edu.cibertec.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class AccesoId implements Serializable {
	private static final long serialVersionUID = 1L;

    private int codMenu;
    private int codUsu;

    // Getters, Setters, hashCode, and equals methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccesoId accesoId = (AccesoId) o;
        return codMenu == accesoId.codMenu && codUsu == accesoId.codUsu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codMenu, codUsu);
    }

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
}
