/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dzaja
 */
public class Opstina implements ApstraktniDomenskiObjekat {

    private int idOpstina;
    private String nazivOpstina;

    public Opstina() {
    }

    public Opstina(int idOpstina, String nazivOpstina) {
        this.idOpstina = idOpstina;
        this.nazivOpstina = nazivOpstina;
    }

    public int getIdOpstina() {
        return idOpstina;
    }

    public void setIdOpstina(int idOpstina) {
        this.idOpstina = idOpstina;
    }

    public String getNazivOpstina() {
        return nazivOpstina;
    }

    public void setNazivOpstina(String nazivOpstina) {
        this.nazivOpstina = nazivOpstina;
    }

    @Override
    public String toString() {
        return nazivOpstina;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Opstina other = (Opstina) obj;
        return this.idOpstina == other.idOpstina;
    }

    @Override
    public String vratiNazivTabele() {
        return "opstina";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return "'" + nazivOpstina + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "opstina.idOpstina=" + idOpstina;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "naziv='" + nazivOpstina + "'";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idOpstina = rs.getInt("opstina.idOpstina");
            String naziv = rs.getString("opstina.naziv");

            Opstina o = new Opstina(idOpstina, naziv);
            lista.add(o);
        }
        return lista;
    }

}
