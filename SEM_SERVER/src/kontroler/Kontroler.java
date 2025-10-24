/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Opstina;
import domen.OrganizatorLova;
import domen.RezervacijaLova;
import domen.StavkaRezervacijeLova;
import java.util.List;
import operacije.Opstina.UcitajSveOpstine;
import operacije.Organizator.AzurirajOrganizatora;
import operacije.Organizator.KreirajNovogOrganizatora;
import operacije.Organizator.ObrisiOrganizatoraLova;
import operacije.login.LoginOperacija;
import operacije.Organizator.UcitajOrganizatoreLova;
import operacije.Rezervacija.Stavke.UcitajStavke;
import operacije.Rezervacija.UcitajRezervacije;

/**
 *
 * @author Dzaja
 */
public class Kontroler {

    private static Kontroler instance;

    private Kontroler() {
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public OrganizatorLova login(OrganizatorLova ol) throws Exception {
        LoginOperacija lo = new LoginOperacija(); //samo je napravljen objekat login operacija
        lo.izvrsi(ol, null); //prosledjujemo objekat, tj organizatora koji je posalt sa login forme, tj obradeklij zahteva
        System.out.println("KONTROLER JE DOBIO ORGANIZATORA: " + lo.getOl());
        return lo.getOl(); //vratimo samo tog organizatora
    }

    public List<OrganizatorLova> ucitajOrganizatore() throws Exception {
        UcitajOrganizatoreLova uol = new UcitajOrganizatoreLova();
        uol.izvrsi(null, null); //setovace listu
        System.out.println("KONTORLER JE DOBIO SVE ORGANIZATORE: " + uol.getListaOrgLova());
        return uol.getListaOrgLova();
    }

    public void obrisiOrganizatora(OrganizatorLova ol) throws Exception {
        ObrisiOrganizatoraLova ool = new ObrisiOrganizatoraLova();
        ool.izvrsi(ol, null);
    }

    public List<Opstina> ucitajSveOpstine() throws Exception {
        Opstina o = new Opstina();
        UcitajSveOpstine operacija = new UcitajSveOpstine();
        operacija.izvrsi(o, null);
        return operacija.getLista();
    }

    public void dodajNovogOrganizatora(OrganizatorLova ol2) throws Exception {
        KreirajNovogOrganizatora operacija = new KreirajNovogOrganizatora();
        operacija.izvrsi(ol2, null);
    }

    public void azurirajOrganizatora(OrganizatorLova ol3) throws Exception {
        AzurirajOrganizatora operacija = new AzurirajOrganizatora();
        operacija.izvrsi(ol3, null);
    }

    public List<RezervacijaLova> ucitajRezervacije() throws Exception {
        UcitajRezervacije operacija = new UcitajRezervacije();
        operacija.izvrsi(null, null); //setovace listu
        System.out.println("KONTORLER JE DOBIO SVE REZERVACIJE: " + operacija.getListaRezervacija());
        return operacija.getListaRezervacija();
    }

    public List<StavkaRezervacijeLova> ucitajStavke(int id) throws Exception {
        UcitajStavke operacija = new UcitajStavke();
        operacija.izvrsi(id, null);
        System.out.println("KONTORLER JE DOBIO STAVKE: " + operacija.getStavke());
        return operacija.getStavke();
    }
}
