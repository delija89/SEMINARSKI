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
public class VrstaLova implements ApstraktniDomenskiObjekat {

    private int idVrstaLova;
    private String naziv;
    private TIpDivljaci tIpDivljaci;
    private double cenaPoDanu;

    public VrstaLova() {
    }

    public VrstaLova(int idVrstaLova, String naziv, TIpDivljaci tIpDivljaci, double cenaPoDanu) {
        this.idVrstaLova = idVrstaLova;
        this.naziv = naziv;
        this.tIpDivljaci = tIpDivljaci;
        this.cenaPoDanu = cenaPoDanu;
    }

    public int getIdVrstaLova() {
        return idVrstaLova;
    }

    public void setIdVrstaLova(int idVrstaLova) {
        this.idVrstaLova = idVrstaLova;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public TIpDivljaci gettIpDivljaci() {
        return tIpDivljaci;
    }

    public void settIpDivljaci(TIpDivljaci tIpDivljaci) {
        this.tIpDivljaci = tIpDivljaci;
    }

    public double getCenaPoDanu() {
        return cenaPoDanu;
    }

    public void setCenaPoDanu(double cenaPoDanu) {
        this.cenaPoDanu = cenaPoDanu;
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
        final VrstaLova other = (VrstaLova) obj;
        return this.idVrstaLova == other.idVrstaLova;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String vratiNazivTabele() {
        return "vrstalova";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv, tipDivljaci, cenaPoDanu";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return "'" + naziv + "', '" + tIpDivljaci.toString() + "', " + cenaPoDanu;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "vrstalova.idVrstaLova=" + idVrstaLova;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "naziv='" + naziv + "', tipDivljaci='" + tIpDivljaci.toString() + ", cenaPoDanu=" + cenaPoDanu;
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idVrstaLova = rs.getInt("vrstalova.idVrstaLova");
            String naziv = rs.getString("vrstalova.naziv");
            TIpDivljaci tIpDivljaci = TIpDivljaci.valueOf(rs.getString("vrstalova.tipDivljaci"));
            Double cenaPoDanu = rs.getDouble("vrstalova.cenaPoDanu");

            VrstaLova vl = new VrstaLova(idVrstaLova, naziv, tIpDivljaci, cenaPoDanu);
            lista.add(vl);
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
