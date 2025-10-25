/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontorleri;

import domen.LovackaGrupa;
import domen.Opstina;
import domen.OrganizatorLova;
import domen.RezervacijaLova;
import domen.Sezona;
import domen.VrstaLova;
import forme.DodajNovuRezervacijuForm;
import forme.mod.FormaMod;
import java.util.List;
import koridnator.Kordinator;

/**
 *
 * @author Dzaja
 */
public class DodajRezervacijuKontoler {

    private final DodajNovuRezervacijuForm dnrForm;

    public DodajRezervacijuKontoler(DodajNovuRezervacijuForm dnrForm) {
        this.dnrForm = dnrForm;
        addActionListeners();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dnrForm.setLocationRelativeTo(null);
        dnrForm.setVisible(true);
    }

    private void pripremiFormu(FormaMod mod) {
        List<OrganizatorLova> organizatori = komunikacija.Komunikacija.getInstance().ucitajOrganizatore();
        List<LovackaGrupa> grupe = komunikacija.Komunikacija.getInstance().ucitajLovackeGrupe();
        List<Opstina> opstine = komunikacija.Komunikacija.getInstance().ucitajSveOpstine();
        List<VrstaLova> vrsta = komunikacija.Komunikacija.getInstance().ucitajVrsteLova();

        dnrForm.getCmbOrganizatori().removeAllItems();
        dnrForm.getCmbLovackeGrupe().removeAllItems();
        dnrForm.getCmbOpstine().removeAllItems();
        dnrForm.getCmbVrstaLova().removeAllItems();

        organizatori.forEach(o -> dnrForm.getCmbOrganizatori().addItem(o));
        grupe.forEach(g -> dnrForm.getCmbLovackeGrupe().addItem(g));
        opstine.forEach(op -> dnrForm.getCmbOpstine().addItem(op));
        vrsta.forEach(vl -> dnrForm.getCmbVrstaLova().addItem(vl));

        for (Sezona s : Sezona.values()) {
            dnrForm.getCmbSezona().addItem(s);  //POPUNJAVANJE SEZONA IZ ENUMA
        }

        if (mod.equals(FormaMod.DODAJ)) {
            dnrForm.getBtnIzmeniRez().setVisible(false);
            dnrForm.getBtnDodajRez().setVisible(true);
        } else {
            // mod izmena – popuni polja sa postojećim podacima
            RezervacijaLova rezervacija = (RezervacijaLova) Kordinator.getInstanca().vratiParam("rezervacijalova");
            popuniFormuSaPodacima();
        }
    }

    private void addActionListeners() {
    }

    private void popuniFormuSaPodacima() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
