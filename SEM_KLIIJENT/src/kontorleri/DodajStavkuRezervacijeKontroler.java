/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontorleri;

import domen.RezervacijaLova;
import domen.StavkaRezervacijeLova;
import domen.VrstaLova;
import forme.DodajStavkuRezervacijeForm;
import forme.mod.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koridnator.Kordinator;

/**
 *
 * @author Dzaja
 */
public class DodajStavkuRezervacijeKontroler {

    private final DodajStavkuRezervacijeForm dsrForm;

    public DodajStavkuRezervacijeKontroler(DodajStavkuRezervacijeForm dsrForm) {
        this.dsrForm = dsrForm;
        addActionListeners();
    }

    private void addActionListeners() {
        dsrForm.getCmbVrstaLova().addActionListener(e -> {  //OVIM UZIMAM CENU IZ VRSTE LOVA I AUTOMATSKI POPUNJAVAM POLJE ZA STAVKU
            VrstaLova v = (VrstaLova) dsrForm.getCmbVrstaLova().getSelectedItem();
            if (v != null) {
                dsrForm.getTxtCena().setText(String.valueOf(v.getCenaPoDanu()));
            }
        });

        dsrForm.addDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RezervacijaLova rez = (RezervacijaLova) Kordinator.getInstanca().vratiParam("rezervacijaLova");
                String uslovi = dsrForm.getTxtUslovi().getText();
                int brojDana = Integer.parseInt(dsrForm.getTxtBrojDana().getText());
                double cena = Double.parseDouble(dsrForm.getTxtCena().getText());
                VrstaLova vrstaLova = (VrstaLova) dsrForm.getCmbVrstaLova().getSelectedItem();
                double iznos = (double) brojDana * cena;

                StavkaRezervacijeLova stavkaZaIzmenu = (StavkaRezervacijeLova) Kordinator.getInstanca().vratiParam("stavkaZaIzmenu");
                if (stavkaZaIzmenu != null) {
                    stavkaZaIzmenu.setUslovi(uslovi);
                    stavkaZaIzmenu.setBrojDana(brojDana);
                    stavkaZaIzmenu.setCena(cena);
                    stavkaZaIzmenu.setVrstaLova(vrstaLova);
                    stavkaZaIzmenu.setIznos(iznos);

                    try {
                        Komunikacija.getInstance().izmeniStavku(stavkaZaIzmenu);
                        JOptionPane.showMessageDialog(null, "Sistem je izmenio stavku rezervacije lova");
                        Kordinator.getInstanca().dodajParam("stavkaZaIzmenu", null); // reset parametra
                        Kordinator.getInstanca().osveziFormuDodajRez();
                        dsrForm.dispose();
                        return;
                    } catch (Exception ex) {
                        Logger.getLogger(DodajStavkuRezervacijeKontroler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                int rb = rez.getStavke().size() + 1;
                StavkaRezervacijeLova stavka = new StavkaRezervacijeLova(rez, rb, uslovi, brojDana, cena, vrstaLova, iznos);
                rez.getStavke().add(stavka);

                try {

                    Kordinator.getInstanca().dodajUListuDodatih(stavka);
                    JOptionPane.showMessageDialog(null, "Sistem je zapamtio stavku rezervacije lova");

                    dsrForm.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti stavku rezervacije lova");
                }
            }
        });
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dsrForm.setLocationRelativeTo(null);
        dsrForm.setVisible(true);
    }

    private void pripremiFormu(FormaMod mod) {
        List<VrstaLova> vrsta = komunikacija.Komunikacija.getInstance().ucitajVrsteLova();
        dsrForm.getCmbVrstaLova().removeAllItems();
        vrsta.forEach(vl -> dsrForm.getCmbVrstaLova().addItem(vl));

        StavkaRezervacijeLova stavkaZaIzmenu = (StavkaRezervacijeLova) Kordinator.getInstanca().vratiParam("stavkaZaIzmenu");
        if (stavkaZaIzmenu != null) {
            dsrForm.getTxtUslovi().setText(stavkaZaIzmenu.getUslovi());
            dsrForm.getTxtBrojDana().setText(String.valueOf(stavkaZaIzmenu.getBrojDana()));
            dsrForm.getTxtCena().setText(String.valueOf(stavkaZaIzmenu.getCena()));
            dsrForm.getCmbVrstaLova().setSelectedItem(stavkaZaIzmenu.getVrstaLova());
        }
    }

}
