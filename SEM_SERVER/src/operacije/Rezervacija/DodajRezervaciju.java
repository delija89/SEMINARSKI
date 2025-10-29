/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.Rezervacija;

import domen.RezervacijaLova;
import domen.StavkaRezervacijeLova;
import operacije.ApstraktnaGenerickaOperacija;
import repository.db.impl.DbRepositoryGeneric;

/**
 *
 * @author Dzaja
 */
public class DodajRezervaciju extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof RezervacijaLova)) {
            throw new Exception("UBACITI GRESKU IZ DOKUMENTACIJE!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        RezervacijaLova rez = (RezervacijaLova) objekat;

        int noviId = ((DbRepositoryGeneric) broker).vratiId(rez);
        rez.setIdRezervacijaLova(noviId);

        for (StavkaRezervacijeLova stavka : rez.getStavke()) {
            stavka.setRezervacijaLova(rez);
            broker.add(stavka);
        }
    }

}
