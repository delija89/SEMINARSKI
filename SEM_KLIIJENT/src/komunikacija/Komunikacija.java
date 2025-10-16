/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Opstina;
import domen.OrganizatorLova;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import koridnator.Kordinator;

/**
 *
 * @author Dzaja
 */
public class Komunikacija {

    private Socket s;
    private Posiljaoc posiljaoc;
    private Primalac primalac;
    private static Komunikacija instance;

    public Komunikacija() {
    }

    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public void konekcija() {
        try {
            s = new Socket("localhost", 9000);
            posiljaoc = new Posiljaoc(s);
            primalac = new Primalac(s);
        } catch (IOException ex) {
            System.out.println("SERVER NIJE POVEZAN (nesto oko socketa i posiljaoca i primaoca)");
        }
    }

    public OrganizatorLova login(String username, String password) {
        OrganizatorLova ol = new OrganizatorLova();
        ol.setUsername(username);
        ol.setPassword(password);
        Zahtev z = new Zahtev(Operacija.LOGIN, ol);

        posiljaoc.posalji(z); // pozivamo metodu da na out ispisemo objekat tj ulogovanog

        //ovde ide deo za odg
        Odgovor odg = (Odgovor) primalac.primi(); //uzimamo odgovor tj organizatora lova koji je dobijen u OKZ
        ol = (OrganizatorLova) odg.getOdgovor();
        System.out.println(ol);
        return ol;
    }

    public List<OrganizatorLova> ucitajOrganizatore() {
        List<OrganizatorLova> listaOrganizatoriLova = new ArrayList<>();
        Zahtev z = new Zahtev(Operacija.UCITAJ_ORGANIZATORE, null); //nema nikkav obj da prima pa null je drugi parametar

        posiljaoc.posalji(z); //ovo ide ka obradi klij. zahteva

        Odgovor odg = (Odgovor) primalac.primi();
        listaOrganizatoriLova = (List<OrganizatorLova>) odg.getOdgovor();
        return listaOrganizatoriLova;
    }

    public void obrisiOrganizatora(OrganizatorLova ol) throws Exception {
        Zahtev z = new Zahtev(Operacija.OBRISI_ORGANIZATORA, ol);

        posiljaoc.posalji(z);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Organizator lova je uspesno izbrisan");
        } else {
            System.out.println("GRESKA");
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA U BRISANJU");
        }

    }

    public List<Opstina> ucitajSveOpstine() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_SVE_OPSTINE, null);
        posiljaoc.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        List<Opstina> lista = (List<Opstina>) odg.getOdgovor();
        return lista;
    }

    public void dodajNovogOrganizatora(OrganizatorLova ol) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_NOVOG_ORGANIZATORA, ol);
        posiljaoc.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Organizator lova je uspesno kreiran");
        } else {
            System.out.println("GRESKA");
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA U DODAVANJU");
        }

    }

    public void azurirajOrganizatora(OrganizatorLova ol) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_ORGANIZATORA, ol);
        posiljaoc.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() == null) {
            System.out.println("Organizator lova je uspesno kreiran");
            Kordinator.getInstanca().osveziFormu();
        } else {
            System.out.println("GRESKA");
            ((Exception) odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA U IZMENI");
        }
    }
}
