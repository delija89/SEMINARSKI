/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Dzaja
 */
public class LovackaGrupa implements ApstraktniDomenskiObjekat {

    private int idLovackaGrupa;
    private String imeGrupe;
    private int brojClanova;

    public LovackaGrupa() {
    }

    public LovackaGrupa(int idLovackaGrupa, String imeGrupe, int brojClanova) {
        this.idLovackaGrupa = idLovackaGrupa;
        this.imeGrupe = imeGrupe;
        this.brojClanova = brojClanova;
    }

    public int getIdLovackaGrupa() {
        return idLovackaGrupa;
    }

    public void setIdLovackaGrupa(int idLovackaGrupa) {
        this.idLovackaGrupa = idLovackaGrupa;
    }

    public String getImeGrupe() {
        return imeGrupe;
    }

    public void setImeGrupe(String imeGrupe) {
        this.imeGrupe = imeGrupe;
    }

    public int getBrojClanova() {
        return brojClanova;
    }

    public void setBrojClanova(int brojClanova) {
        this.brojClanova = brojClanova;
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
        final LovackaGrupa other = (LovackaGrupa) obj;
        if (this.idLovackaGrupa != other.idLovackaGrupa) {
            return false;
        }
        if (this.brojClanova != other.brojClanova) {
            return false;
        }
        return Objects.equals(this.imeGrupe, other.imeGrupe);
    }

    @Override
    public String toString() {
        return "LovackaGrupa{" + "idLovackaGrupa=" + idLovackaGrupa + ", imeGrupe=" + imeGrupe + ", brojClanova=" + brojClanova + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "lovackagrupa";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imeGrupe, brojClanova";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return "'" + imeGrupe + "', " + brojClanova;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "lovackagrupa.idLovackaGrupa=" + idLovackaGrupa;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "imeGrupe='" + imeGrupe + "', brojClanova=" + brojClanova;
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idLovackaGrupa = rs.getInt("lovackaGrupa.idLovackaGrupa");
            String imeGrupe = rs.getString("lovackagrupa.imeGrupe");
            int brojClanova = rs.getInt("lovackagrupa.brojClanova");

            LovackaGrupa lg = new LovackaGrupa(idLovackaGrupa, imeGrupe, brojClanova);
            lista.add(lg);
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
