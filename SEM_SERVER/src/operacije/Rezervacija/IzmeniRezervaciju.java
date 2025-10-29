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
public class IzmeniRezervaciju extends ApstraktnaGenerickaOperacija {
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof RezervacijaLova)) {
            throw new Exception("Neispravna rezervacija za izmenu!");
        }
    }
    
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        RezervacijaLova rez = (RezervacijaLova) objekat;
        broker.edit(rez);
        
//        StavkaRezervacijeLova stavka = new StavkaRezervacijeLova();
//        stavka.setRezervacijaLova(rez);
//        broker.delete(stavka);
//        
//        for (StavkaRezervacijeLova stavkaRezervacijeLova : rez.getStavke()) {
//            stavkaRezervacijeLova.setRezervacijaLova(rez);
//            broker.add(stavkaRezervacijeLova);
//        }
    }
    
}
