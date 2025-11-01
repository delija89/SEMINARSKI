/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koridnator;

import domen.OrganizatorLova;
import domen.StavkaRezervacijeLova;
import forme.DodajNovogOrganizatoraForm;
import forme.DodajNovuRezervacijuForm;
import forme.DodajStavkuRezervacijeForm;
import forme.LoginForm;
import forme.MainForm;
import forme.PrikazRezervacijaForm;
import forme.PrikazSvihOrganizatoraForm;
import forme.mod.FormaMod;
import java.util.HashMap;
import java.util.Map;
import kontorleri.DodajNovogOrganizatoraKontroler;
import kontorleri.DodajRezervacijuKontoler;
import kontorleri.DodajStavkuRezervacijeKontroler;
import kontorleri.LoginKontorler;
import kontorleri.MainFormKontroler;
import kontorleri.PrikazRezervacijaKontroler;
import kontorleri.PrikazSvihOrganizatoraKontroler;

/**
 *
 * @author Dzaja
 */
public class Kordinator {

    //OVDE SE MOGU CUVATI ULOGOVANI, ILI NEKE DRUGE VAZNE INFO KOJE NEMAJU GDE
    private OrganizatorLova ulogovani;
    private Map<String, Object> parametri;
    //on treba da upravlja svim kontorlerima
    private LoginKontorler loginKontorler;
    private MainFormKontroler mainFormKontorler;
    private PrikazSvihOrganizatoraKontroler psoKontorler;
    private DodajNovogOrganizatoraKontroler dnoKontroler;

    private PrikazRezervacijaKontroler prKontroler;
    private DodajRezervacijuKontoler drKontroler;
    private DodajStavkuRezervacijeKontroler dsrKontroler;

    private static Kordinator instanca;

    private Kordinator() {
        parametri = new HashMap<>();
    }

    public static Kordinator getInstanca() {
        if (instanca == null) {
            instanca = new Kordinator();
        }
        return instanca;
    }

    public void otvoriLoginForm() {
        loginKontorler = new LoginKontorler(new LoginForm());//pravimo kontrolera za login formu ali i samu formu otvaramo
        loginKontorler.otvoriFormu(); //samo saljemo kontroleru da otvori formu
    }

    public void otvoriMainFormu() {
        mainFormKontorler = new MainFormKontroler(new MainForm());
        mainFormKontorler.otvoriFormu();
    }

    public void otvoriPrikazSvihOrganizatoraFormu() {
        psoKontorler = new PrikazSvihOrganizatoraKontroler(new PrikazSvihOrganizatoraForm());
        psoKontorler.otvoriFormu();
    }

    public void otvoriDodajNovogOrganizatoraFormu() {
        dnoKontroler = new DodajNovogOrganizatoraKontroler(new DodajNovogOrganizatoraForm());
        dnoKontroler.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniOrganizatoraFormu() {
        dnoKontroler = new DodajNovogOrganizatoraKontroler(new DodajNovogOrganizatoraForm());
        dnoKontroler.otvoriFormu(FormaMod.IZMENI);
    }

    public void otvoriPrikazSvihRezervacijaFormu() {
        prKontroler = new PrikazRezervacijaKontroler(new PrikazRezervacijaForm());
        prKontroler.otvoriFormu();
    }

    public PrikazRezervacijaKontroler getPrKontroler() {
        return prKontroler;
    }

    public void otvoriDodajNovuRezervacijuFormu() {
        drKontroler = new DodajRezervacijuKontoler(new DodajNovuRezervacijuForm());
        drKontroler.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniRezervacijuFormu() {
        drKontroler = new DodajRezervacijuKontoler(new DodajNovuRezervacijuForm());
        drKontroler.otvoriFormu(FormaMod.IZMENI);
    }

    public void otvoriDodajStavkuRezervacijeFormu() {
        dsrKontroler = new DodajStavkuRezervacijeKontroler(new DodajStavkuRezervacijeForm());
        dsrKontroler.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniStavkuFormu() {
        dsrKontroler = new DodajStavkuRezervacijeKontroler(new DodajStavkuRezervacijeForm());
        dsrKontroler.otvoriFormu(FormaMod.IZMENI);
    }

    public void dodajUListuDodatih(StavkaRezervacijeLova stavka) {
        drKontroler.getStavke().add(stavka);
        drKontroler.osveziTabeluStavki();
    }

    public void osveziFormuDodajRez() {
        drKontroler.osveziTabeluStavki();
    }

    public void osveziFormu() {
        psoKontorler.osveziFormu();
    }

    public OrganizatorLova getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(OrganizatorLova ulogovani) {
        this.ulogovani = ulogovani;
    }

    public void dodajParam(String s, Object o) {
        parametri.put(s, o);
    }

    public Object vratiParam(String s) {
        return parametri.get(s);
    }

}
