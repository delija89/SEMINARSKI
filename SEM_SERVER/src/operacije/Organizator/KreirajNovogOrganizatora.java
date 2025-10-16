/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.Organizator;

import domen.OrganizatorLova;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Dzaja
 */
public class KreirajNovogOrganizatora extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof OrganizatorLova)) {
            throw new Exception("Sistem nije mogao da kreira novog organizatora");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((OrganizatorLova) objekat);
    }

}
