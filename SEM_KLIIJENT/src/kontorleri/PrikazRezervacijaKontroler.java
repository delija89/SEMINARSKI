/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontorleri;

import domen.RezervacijaLova;
import domen.StavkaRezervacijeLova;
import forme.PrikazRezervacijaForm;
import forme.modeli.ModelTabeleRezervacija;
import forme.modeli.ModelTabeleStavkaRezervacije;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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
        prForm.stavkeAddMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = prForm.getTblRezervacije().getSelectedRow();
                if (red != -1) {
                    ModelTabeleRezervacija mtr = (ModelTabeleRezervacija) prForm.getTblRezervacije().getModel();
                    RezervacijaLova rl = mtr.getLista().get(red);

                    List<StavkaRezervacijeLova> stavke = komunikacija.Komunikacija.getInstance().ucitajStavke(rl.getIdRezervacijaLova());
                    ModelTabeleStavkaRezervacije mts = new ModelTabeleStavkaRezervacije(stavke);
                    prForm.getTblStavke().setModel(mts);
                }
            }
        });
    }

    public void otvoriFormu() {
        ucitajRezervacije();
        ucitajStavkeRezervacija();
        prForm.setLocationRelativeTo(null);
        prForm.setVisible(true);
    }

    public void ucitajRezervacije() {
        //ucitavamo listu svih organizatora u tabelu forme
        List<RezervacijaLova> sveRezervacije = komunikacija.Komunikacija.getInstance().ucitajRezervacije();
        ModelTabeleRezervacija mtr = new ModelTabeleRezervacija(sveRezervacije);
        prForm.getTblRezervacije().setModel(mtr);
    }

    private void ucitajStavkeRezervacija() {
        List<StavkaRezervacijeLova> stavke = new ArrayList<>();//kolone dobre ali prazna
        ModelTabeleStavkaRezervacije mtsr = new ModelTabeleStavkaRezervacije(stavke);
        prForm.getTblStavke().setModel(mtsr);
    }

    public void osveziFormu() {
        ucitajRezervacije();
        //ucitajStavkeRezervacija();
    }

}
