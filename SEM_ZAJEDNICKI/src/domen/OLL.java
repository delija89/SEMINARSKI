/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Dzaja
 */
public class OLL implements ApstraktniDomenskiObjekat {

    private OrganizatorLova organizatorLova;
    private Lokacija lokacija;
    private Date datumodrzavanja;

    public OLL() {
    }

    public OLL(OrganizatorLova organizatorLova, Lokacija lokacija, Date datumodrzavanja) {
        this.organizatorLova = organizatorLova;
        this.lokacija = lokacija;
        this.datumodrzavanja = datumodrzavanja;
    }

    public OrganizatorLova getOrganizatorLova() {
        return organizatorLova;
    }

    public void setOrganizatorLova(OrganizatorLova organizatorLova) {
        this.organizatorLova = organizatorLova;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    public Date getDatumodrzavanja() {
        return datumodrzavanja;
    }

    public void setDatumodrzavanja(Date datumodrzavanja) {
        this.datumodrzavanja = datumodrzavanja;
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
        final OLL other = (OLL) obj;
        return Objects.equals(this.organizatorLova, other.organizatorLova);
    }

    @Override
    public String toString() {
        return "OLL{" + "organizatorLova=" + organizatorLova + ", lokacija=" + lokacija + ", datumodrzavanja=" + datumodrzavanja + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "oll";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idOrganizator, idLokacija, datumOdrzavanja";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return organizatorLova.getIdOrganizator() + ", " + lokacija.getIdLokacija() + ", '" + datumodrzavanja + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "";
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "idOrganizator=" + organizatorLova.getIdOrganizator() + ", idLokacija=" + lokacija.getIdLokacija()
                + ", datumOdrzavanja='" + datumodrzavanja + "'";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
