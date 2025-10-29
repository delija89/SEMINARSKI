/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.Rezervacija;

import domen.RezervacijaLova;
import domen.StavkaRezervacijeLova;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Dzaja
 */
public class ObrisiRezervaciju extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof RezervacijaLova)) {
            throw new Exception("Neispravan objekat rezervacije za brisanje!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        RezervacijaLova rez = (RezervacijaLova) objekat;
        StavkaRezervacijeLova stavka = new StavkaRezervacijeLova();
        stavka.setRezervacijaLova(rez);
        broker.delete(stavka);
        broker.delete(rez);
    }

}
