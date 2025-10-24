/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.Rezervacija.Stavke;

import domen.RezervacijaLova;
import domen.StavkaRezervacijeLova;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Dzaja
 */
public class UcitajStavke extends ApstraktnaGenerickaOperacija {

    List<StavkaRezervacijeLova> stavke;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String uslov = " JOIN vrstalova ON vrstalova.idVrstaLova=stavkarezervacijelova.idVrstaLova "
                + " WHERE idRezervacijaLova=" + (int) objekat + " "; //treba da se doda join
        stavke = broker.getAll(new StavkaRezervacijeLova(), uslov);
    }

    public List<StavkaRezervacijeLova> getStavke() {
        return stavke;
    }

}
