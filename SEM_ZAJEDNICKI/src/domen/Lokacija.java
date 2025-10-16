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
public class Lokacija implements ApstraktniDomenskiObjekat {

    private int idLokacija;
    private String naziv;
    private TipTerena tipTerena;
    private Pristupacnost pristupacnost;

    public Lokacija() {
    }

    public Lokacija(int idLokacija, String naziv, TipTerena tipTerena, Pristupacnost pristupacnost) {
        this.idLokacija = idLokacija;
        this.naziv = naziv;
        this.tipTerena = tipTerena;
        this.pristupacnost = pristupacnost;
    }

    public int getIdLokacija() {
        return idLokacija;
    }

    public void setIdLokacija(int idLokacija) {
        this.idLokacija = idLokacija;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public TipTerena getTipTerena() {
        return tipTerena;
    }

    public void setTipTerena(TipTerena tipTerena) {
        this.tipTerena = tipTerena;
    }

    public Pristupacnost getPristupacnost() {
        return pristupacnost;
    }

    public void setPristupacnost(Pristupacnost pristupacnost) {
        this.pristupacnost = pristupacnost;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Lokacija other = (Lokacija) obj;
        return this.idLokacija == other.idLokacija;
    }

    @Override
    public String toString() {
        return "Lokacija{" + "idLokacija=" + idLokacija + ", naziv=" + naziv + ", tipTerena=" + tipTerena + ", pristupacnost=" + pristupacnost + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "lokacija";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv, tipTerena, pristupacnost"; // vraca kolone koje se unose insert upitom
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return "'" + naziv + "', '" + tipTerena.toString() + "', " + pristupacnost.toString() + "'";
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "naziv='" + naziv + "', tipTerena='" + tipTerena.toString() + "', pristupacnost='" + pristupacnost.toString() + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "lokacija.idLokacija=" + idLokacija;
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idLokacija = rs.getInt("lokacija.idLokacija");
            String naziv = rs.getString("lokacija.idLokacija");
            TipTerena tipTerena = TipTerena.valueOf(rs.getString("lokacija.tipTerena"));
            Pristupacnost pristupacnost = Pristupacnost.valueOf(rs.getString("lokacija.pristupacnost"));

            Lokacija l = new Lokacija(idLokacija, naziv, tipTerena, pristupacnost);
            lista.add(l);
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
