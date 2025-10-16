/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.login;

import domen.OrganizatorLova;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Dzaja
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija {

    OrganizatorLova ol;

    public OrganizatorLova getOl() {
        return ol;
    }

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof OrganizatorLova)) {
            throw new Exception("Greska u brisanju organizatora!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String uslov = " o JOIN opstina op ON o.idOpstina = op.idOpstina";
        List<OrganizatorLova> sviOrganizatori = broker.getAll((OrganizatorLova) objekat, uslov); //ovo ce vratiri listu svih zapposlenih preko metode getall
        System.out.println("ORGANIZATORI DOBIJENI IZ BAZE PREKO LOGINOPERACIJE: " + sviOrganizatori);
        if (sviOrganizatori.contains((OrganizatorLova) objekat)) {
            for (OrganizatorLova organizatorLova : sviOrganizatori) {
                if (organizatorLova.equals((OrganizatorLova) objekat)) {
                    ol = organizatorLova; //ako se poklapa mozemo da vratimo tog kao ulogovanog
                    return;
                }
            }
        } else {
            ol = null; //ako ne postoji u bazi znaci da se ne moze ulogogvati pa ga ne pamtimo
        }

    }

}
