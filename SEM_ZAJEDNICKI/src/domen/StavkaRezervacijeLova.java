/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Dzaja
 */
public class StavkaRezervacijeLova implements ApstraktniDomenskiObjekat {

    RezervacijaLova rezervacijaLova;
    private int rb;
    private String uslovi;
    private int brojDana;
    private double cena;
    private VrstaLova vrstaLova;
    private double iznos;

    public StavkaRezervacijeLova() {
    }

    public StavkaRezervacijeLova(RezervacijaLova rezervacijaLova, int rb, String uslovi, int brojDana, double cena, VrstaLova vrstaLova, double iznos) {
        this.rezervacijaLova = rezervacijaLova;
        this.rb = rb;
        this.uslovi = uslovi;
        this.brojDana = brojDana;
        this.cena = cena;
        this.vrstaLova = vrstaLova;
        this.iznos = iznos;
    }

    public RezervacijaLova getRezervacijaLova() {
        return rezervacijaLova;
    }

    public void setRezervacijaLova(RezervacijaLova rezervacijaLova) {
        this.rezervacijaLova = rezervacijaLova;
    }


    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public String getUslovi() {
        return uslovi;
    }

    public void setUslovi(String uslovi) {
        this.uslovi = uslovi;
    }

    public int getBrojDana() {
        return brojDana;
    }

    public void setBrojDana(int brojDana) {
        this.brojDana = brojDana;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public VrstaLova getVrstaLova() {
        return vrstaLova;
    }

    public void setVrstaLova(VrstaLova vrstaLova) {
        this.vrstaLova = vrstaLova;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
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
        final StavkaRezervacijeLova other = (StavkaRezervacijeLova) obj;
        return this.rb == other.rb;
    }

    @Override
    public String toString() {
        return "StavkaRezervacijeLova{" + "rb=" + rb + ", uslovi=" + uslovi + ", brojDana=" + brojDana + ", cena=" + cena + ", vrstaLova=" + vrstaLova + ", iznos=" + iznos + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkarezervacijelova";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "rb, uslovi, brojDana, cena, iznos, idVrstaLova";
    }

    @Override
    public String vratiVrednostZaUbacivanje() { 
        return rb + ", '" + uslovi + "', " + brojDana + ", " + cena + ", " + iznos + ", " + vrstaLova.getIdVrstaLova();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "";
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "rb=" + rb + ", uslovi='" + uslovi + ", brojDana=" + brojDana + ", cena=" + cena
                + "iznos=" + iznos + ", idVrstaLova=" + vrstaLova.getIdVrstaLova();
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
