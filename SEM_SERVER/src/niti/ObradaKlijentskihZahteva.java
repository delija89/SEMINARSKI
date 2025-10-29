/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.LovackaGrupa;
import domen.Opstina;
import domen.OrganizatorLova;
import domen.RezervacijaLova;
import domen.StavkaRezervacijeLova;
import domen.VrstaLova;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import static komunikacija.Operacija.UCITAJ_VRSTE_LOVA;
import komunikacija.Posiljaoc;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import kontroler.Kontroler;

/**
 *
 * @author Dzaja
 */
public class ObradaKlijentskihZahteva extends Thread {
    
    Socket s;
    Posiljaoc posiljaoc;
    Primalac primalac;
    boolean kraj = false;
    
    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
        posiljaoc = new Posiljaoc(s);
        primalac = new Primalac(s);
    }
    
    @Override
    public void run() {
        try {
            while (!kraj) {
                
                Zahtev zahtev = (Zahtev) primalac.primi();
                if (zahtev == null) {
                    return;
                }
                System.out.println("KLASA OKZ (PARAMETAR OD KLIJENTA): " + zahtev.getParametar());
                System.out.println("KLASA OKZ (OPERACIJA OD KLIJENTA): " + zahtev.getOperacija());
                Odgovor odgovor = new Odgovor();
                switch (zahtev.getOperacija()) {
                    case LOGIN: //kada je login zahtev poslat
                        OrganizatorLova ol = (OrganizatorLova) zahtev.getParametar(); //vratice ulogovanog 
                        ol = Kontroler.getInstance().login(ol); //ovde nam se vrati ol preko kontorlera
                        odgovor.setOdgovor(ol);
                        break;
                    case UCITAJ_ORGANIZATORE:
                        List<OrganizatorLova> listaOrganizatoriLova = Kontroler.getInstance().ucitajOrganizatore(); //ovo ide kontroleru i on vraca sve org

                        odgovor.setOdgovor(listaOrganizatoriLova);//ovde setujemo i vracamo se u kontroler za prikaz svih
                        break;
                    case OBRISI_ORGANIZATORA:
                        try {
                            OrganizatorLova ol1 = (OrganizatorLova) zahtev.getParametar();
                            Kontroler.getInstance().obrisiOrganizatora(ol1);
                            odgovor.setOdgovor(null); //sve je u redu
                        } catch (Exception e) {
                            odgovor.setOdgovor(e); //doslo do greske
                        }
                        break;
                    case UCITAJ_SVE_OPSTINE:
                        List<Opstina> sveOpstine = Kontroler.getInstance().ucitajSveOpstine();
                        odgovor.setOdgovor(sveOpstine);
                        break;
                    case DODAJ_NOVOG_ORGANIZATORA:
                        OrganizatorLova ol2 = (OrganizatorLova) zahtev.getParametar();
                        Kontroler.getInstance().dodajNovogOrganizatora(ol2);
                        odgovor.setOdgovor(null);//svee uredu
                        break;
                    case AZURIRAJ_ORGANIZATORA:
                        OrganizatorLova ol3 = (OrganizatorLova) zahtev.getParametar();
                        Kontroler.getInstance().azurirajOrganizatora(ol3);
                        odgovor.setOdgovor(null);
                        break;
                    
                    case UCITAJ_REZERVACIJE:
                        List<RezervacijaLova> sveRezervacije = Kontroler.getInstance().ucitajRezervacije();
                        System.out.println("KLASA OKZ: " + sveRezervacije);
                        odgovor.setOdgovor(sveRezervacije);
                        break;
                    case UCITAJ_STAVKE:
                        List<StavkaRezervacijeLova> stavke = Kontroler.getInstance().ucitajStavke((int) zahtev.getParametar());
                        System.out.println("KLASA OKZ: " + stavke);
                        odgovor.setOdgovor(stavke);
                        break;
                    
                    case UCITAJ_LOVACKE_GRUPE:
                        List<LovackaGrupa> grupe = Kontroler.getInstance().ucitajLovackeGrupe();
                        System.out.println("KLASA OKZ: " + grupe);
                        odgovor.setOdgovor(grupe);
                        break;
                    case UCITAJ_VRSTE_LOVA:
                        List<VrstaLova> vrste = Kontroler.getInstance().ucitajVrsteLova();
                        System.out.println("KLASA OKZ: " + vrste);
                        odgovor.setOdgovor(vrste);
                        break;
                    
                    case DODAJ_REZERVACIJU_LOVA:
                        try {
                            Kontroler.getInstance().dodajRezervaciju((RezervacijaLova) zahtev.getParametar());
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    default:
                        System.out.println("NIJE UNETA VALIDNA OPERACIJA!!!");
                    
                }
                System.out.println("KLASA OBRADAKLZAHTEVA: " + odgovor.getOdgovor());
                posiljaoc.posalji(odgovor);
            }
        } catch (Exception ex) {
            System.out.println("GRESKA U OBRADI KLIJ ZAHTEVA!");
            ex.printStackTrace();
        } finally { //ovo mozda ne treba ovde?
            try {
                s.close();
                System.out.println("Zatvorena konekcija sa klijentom.");
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void prekiniNit() {
        kraj = true;
        try {
            s.close();
            interrupt();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
