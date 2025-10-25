/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.LovackaGrupa;

import domen.LovackaGrupa;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Dzaja
 */
public class UcitajLovackeGrupe extends ApstraktnaGenerickaOperacija {

    List<LovackaGrupa> listaGrupa;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        listaGrupa = broker.getAll(new LovackaGrupa(), null);
    }

    public List<LovackaGrupa> getListaGrupa() {
        return listaGrupa;
    }

}
