/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.Rezervacija;

import domen.OrganizatorLova;
import domen.RezervacijaLova;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Dzaja
 */
public class UcitajRezervacije extends ApstraktnaGenerickaOperacija {

    List<RezervacijaLova> listaRezervacija;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        //"idRezervacija", "datum", "sezona", "iznosRezervacije", "organizator", "lovackagrupa", "opstina"
        String uslov = " JOIN organizatorlova ON rezervacijalova.idOrganizator = organizatorlova.idOrganizator "
                + "JOIN lovackagrupa ON rezervacijalova.idLovackaGrupa = lovackagrupa.idLovackaGrupa "
                + "JOIN opstina ON rezervacijalova.IdOpstina = opstina.IdOpstina"; //JOIN USLOVI DA BI SE UZELI I OSTALI OBJEKTI
        listaRezervacija = broker.getAll(new RezervacijaLova(), uslov);
    }

    public List<RezervacijaLova> getListaRezervacija() {
        return listaRezervacija;
    }
}
