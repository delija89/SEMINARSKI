/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontorleri;

import domen.OrganizatorLova;
import domen.RezervacijaLova;
import forme.PrikazRezervacijaForm;
import forme.PrikazSvihOrganizatoraForm;
import forme.modeli.ModelTabeleOrganizator;
import forme.modeli.ModelTabeleRezervacija;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koridnator.Kordinator;

/**
 *
 * @author Dzaja
 */
public class PrikazRezervacijaKontroler {

    private final PrikazRezervacijaForm prForm;

    public PrikazRezervacijaKontroler(PrikazRezervacijaForm prForm) {
        this.prForm = prForm;
        addActionListeners();
    }

    private void addActionListeners() { //ova ce reagovati na klik na glavnoj formi

    }

    public void otvoriFormu() {
        ucitajRezervacije();
        prForm.setLocationRelativeTo(null);
        prForm.setVisible(true);
    }

    public void ucitajRezervacije() {
        //ucitavamo listu svih organizatora u tabelu forme
        List<RezervacijaLova> sveRezervacije = komunikacija.Komunikacija.getInstance().ucitajRezervacije(); 
        ModelTabeleRezervacija mtr = new ModelTabeleRezervacija(sveRezervacije); 
        prForm.getTblRezervacije().setModel(mtr);
    }

    public void osveziFormu() {
        ucitajRezervacije();
    }
}
