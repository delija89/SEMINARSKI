/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.Opstina;

import domen.Opstina;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Dzaja
 */
public class UcitajSveOpstine extends ApstraktnaGenerickaOperacija {

    List<Opstina> lista;

    public List<Opstina> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        lista = broker.getAll(objekat, kljuc);//kljuc je svakako null jer nema uslova
    }

}
