/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.VrstaLova;

import domen.VrstaLova;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Dzaja
 */
public class UcitajVrsteLova extends ApstraktnaGenerickaOperacija {

    List<VrstaLova> vrste;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        vrste = broker.getAll(new VrstaLova(), null);
    }

    public List<VrstaLova> getVrste() {
        return vrste;
    }

}
