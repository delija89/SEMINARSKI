/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.Rezervacija.Stavke;

import domen.StavkaRezervacijeLova;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Dzaja
 */
public class IzmeniStavku extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof StavkaRezervacijeLova)) {
            throw new Exception("Sistem ne može da izmeni stavku – neispravan objekat!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        StavkaRezervacijeLova stavka = (StavkaRezervacijeLova) objekat;
        broker.edit(stavka);
    }

}
