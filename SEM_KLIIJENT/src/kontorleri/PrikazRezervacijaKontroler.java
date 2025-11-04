/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontorleri;

import domen.RezervacijaLova;
import domen.StavkaRezervacijeLova;
import forme.PrikazRezervacijaForm;
import forme.mod.FormaMod;
import forme.modeli.ModelTabeleRezervacija;
import forme.modeli.ModelTabeleStavkaRezervacije;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
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

        prForm.ObrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = prForm.getTblRezervacije().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Niste odabrali rezervaciju za brisanje!");
                    return;
                }

                int potvrda = JOptionPane.showConfirmDialog(
                        null,
                        "Da li ste sigurni da želite da obrišete izabranu rezervaciju?",
                        "Potvrda",
                        JOptionPane.YES_NO_OPTION
                );

                if (potvrda == JOptionPane.YES_OPTION) {
                    ModelTabeleRezervacija mtr = (ModelTabeleRezervacija) prForm.getTblRezervacije().getModel();
                    RezervacijaLova rez = mtr.getLista().get(red);

                    try {
                        komunikacija.Komunikacija.getInstance().obrisiRezervaciju(rez);
                        JOptionPane.showMessageDialog(null, "Sistem je obrisao rezervaciju lova!");
                        osveziFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sistem ne moze da obrise rezervaciju lova!");
                        ex.printStackTrace();
                    }
                }
            }
        });

        prForm.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = prForm.getTblRezervacije().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Niste odabrali rezervaciju!");
                    return;
                }

                ModelTabeleRezervacija mtr = (ModelTabeleRezervacija) prForm.getTblRezervacije().getModel();
                RezervacijaLova rez = mtr.getLista().get(red);

                List<StavkaRezervacijeLova> stavke = komunikacija.Komunikacija.getInstance().ucitajStavke(rez.getIdRezervacijaLova());
                rez.setStavke(stavke);

                Kordinator.getInstanca().dodajParam("rezervacijaLova", rez);
                Kordinator.getInstanca().otvoriIzmeniRezervacijuFormu();
            }
        });

        prForm.obrisiStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = prForm.getTblStavke().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Niste odabrali stavku!");
                    return;
                }

                int potvrda = JOptionPane.showConfirmDialog(
                        null,
                        "Da li ste sigurni da želite da obrišete izabranu stavku?",
                        "Potvrda brisanja",
                        JOptionPane.YES_NO_OPTION
                );

                if (potvrda == JOptionPane.YES_OPTION) {
                    ModelTabeleStavkaRezervacije mtsr = (ModelTabeleStavkaRezervacije) prForm.getTblStavke().getModel();
                    StavkaRezervacijeLova stavka = mtsr.getLista().get(red);

                    try {
                        komunikacija.Komunikacija.getInstance().obrisiStavku(stavka);
                        JOptionPane.showMessageDialog(null, "Sistem je obrisao stavku rezervacije lova!");
                        osveziFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sistem ne moze da obrise stavku rezervacije lova!");
                        ex.printStackTrace();
                    }
                }
            }
        });

        prForm.pretraziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = prForm.getTxtImeOrganizatora().getText().trim();

                if (ime.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Unesite ime organizatora za pretragu!");
                    return;
                }

                try {
                    List<RezervacijaLova> sveRezervacije = komunikacija.Komunikacija.getInstance().ucitajRezervacije();

                    List<RezervacijaLova> filtrirane = new ArrayList<>();
                    for (RezervacijaLova rl : sveRezervacije) {
                        if (rl.getOrganizatorLova().getIme().equalsIgnoreCase(ime)) {
                            filtrirane.add(rl);
                        }
                    }

                    if (filtrirane.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nema rezervacija za datog organizatora!");
                        return;
                    }

                    ModelTabeleRezervacija mtr = new ModelTabeleRezervacija(filtrirane);
                    prForm.getTblRezervacije().setModel(mtr);

                    JOptionPane.showMessageDialog(null, "Sistem je nasao rezervacije lova po zadatim kriterijumima");
                    ucitajStavkeRezervacija();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Greška pri filtriranju rezervacija!");
                    ex.printStackTrace();
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
        ucitajStavkeRezervacija();
    }

}
