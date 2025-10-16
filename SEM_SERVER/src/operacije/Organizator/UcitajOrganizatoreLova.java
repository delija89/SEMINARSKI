/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.Organizator;

import domen.OrganizatorLova;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Dzaja
 */
public class UcitajOrganizatoreLova extends ApstraktnaGenerickaOperacija {

    List<OrganizatorLova> listaOrgLova;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String uslov = " o JOIN opstina op ON o.idOpstina = op.idOpstina"; //mora jer ima vezu sa opstinom
        listaOrgLova = broker.getAll(new OrganizatorLova(), uslov); //mora organizator da bi izvukao podatke iz te tabele
    }

    public List<OrganizatorLova> getListaOrgLova() {
        return listaOrgLova;
    }

}
