/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontorleri;

import domen.Opstina;
import domen.OrganizatorLova;

import forme.DodajNovogOrganizatoraForm;
import forme.mod.FormaMod;
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
public class DodajNovogOrganizatoraKontroler {

    private final DodajNovogOrganizatoraForm dnoForm;

    public DodajNovogOrganizatoraKontroler(DodajNovogOrganizatoraForm dnoForm) {
        this.dnoForm = dnoForm;
        addActionListeners();
    }

    private void addActionListeners() { //ova ce reagovati na klik na glavnoj formi
        dnoForm.addDodajOrganizatoraActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ime = dnoForm.getTxtImeOrganizatora1().getText().trim();
                String prezime = dnoForm.getTxtPrezimeOrganizatora().getText().trim();
                String email = dnoForm.getTxtEmail().getText().trim();
                Opstina o = (Opstina) dnoForm.getCmbOpstina().getSelectedItem();
                String username = dnoForm.getTxtUsername().getText();
                String password = String.valueOf(dnoForm.getTxtPassword().getPassword());

                if (ime.isEmpty() || prezime.isEmpty() || email.isEmpty() || o == null || username.isEmpty() || password.isEmpty()
                        || ime == "" || prezime == "" || email == "" || username == "" || password == "") {
                    JOptionPane.showMessageDialog(null,
                            "Sva polja moraju biti popunjena!",
                            "Greška",
                            JOptionPane.ERROR_MESSAGE);
                    return; // prekida dalje izvršenje
                } else {
                    OrganizatorLova ol = new OrganizatorLova(0, ime, prezime, email, o, username, password);
                    try {
                        Komunikacija.getInstance().dodajNovogOrganizatora(ol);
                        JOptionPane.showMessageDialog(null, "Sistem je zapamtio organizatora lova");
                        dnoForm.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti organizatora lova");
                        ex.printStackTrace();
                    }
                }
            }

        }
        );
        dnoForm.addIzmeniOrganizatoraActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrganizatorLova ol = (OrganizatorLova) Kordinator.getInstanca().vratiParam("organizatorlova");
                String ime = dnoForm.getTxtImeOrganizatora1().getText().trim();
                String prezime = dnoForm.getTxtPrezimeOrganizatora().getText().trim();
                String email = dnoForm.getTxtEmail().getText().trim();
                Opstina o = (Opstina) dnoForm.getCmbOpstina().getSelectedItem();
                String username = dnoForm.getTxtUsername().getText();
                String password = String.valueOf(dnoForm.getTxtPassword().getPassword());

                if (ime.isEmpty() || prezime.isEmpty() || email.isEmpty() || o == null || username.isEmpty() || password.isEmpty()
                        || ime == "" || prezime == "" || email == "" || username == "" || password == "") {
                    JOptionPane.showMessageDialog(null,
                            "Sva polja moraju biti popunjena!",
                            "Greška",
                            JOptionPane.ERROR_MESSAGE);
                    return; // prekida dalje izvršenje
                } else {
                    OrganizatorLova ol1 = new OrganizatorLova(ol.getIdOrganizator(), ime, prezime, email, o, username, password);
                    try {
                        Komunikacija.getInstance().azurirajOrganizatora(ol1);
                        JOptionPane.showMessageDialog(null, "Sistem je zapamtio organizatora lova");
                        dnoForm.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti organizatora lova");
                        ex.printStackTrace();
                    }

                }
            }
        }
        );
    }

    public void otvoriFormu(FormaMod mod) { //SA RAZLICITIM MODOM CE MORATI DRUGACIJE COMBOBOX
        pripremiFormu(mod); //ovde bi trebalo da pozovemo da popunimo check box sa opstinama
        dnoForm.setLocationRelativeTo(null);
        dnoForm.setVisible(true);
    }

    private void pripremiFormu(FormaMod mod) {
        if (mod == FormaMod.DODAJ) {
            dnoForm.getBtnDodajOrganizatora().setVisible(true);
            dnoForm.getBtnAzuriraj().setVisible(false);
            dnoForm.getCmbOpstina().removeAllItems(); //pripremimo combo box za popunjavanje
            List<Opstina> sveOpstine = komunikacija.Komunikacija.getInstance().ucitajSveOpstine();
            for (Opstina opstina : sveOpstine) {
                dnoForm.getCmbOpstina().addItem(opstina);
            }
        } else {
            dnoForm.getBtnDodajOrganizatora().setVisible(false);
            dnoForm.getBtnAzuriraj().setVisible(true);

            OrganizatorLova ol = (OrganizatorLova) Kordinator.getInstanca().vratiParam("organizatorlova");
            dnoForm.getTxtImeOrganizatora1().setText(ol.getIme());
            dnoForm.getTxtPrezimeOrganizatora().setText(ol.getPrezime());
            dnoForm.getTxtEmail().setText(ol.getEmail());
            //deo za opstinu
            List<Opstina> sveOpstine = komunikacija.Komunikacija.getInstance().ucitajSveOpstine();
            for (Opstina opstina : sveOpstine) {
                dnoForm.getCmbOpstina().addItem(opstina);
            }
            dnoForm.getCmbOpstina().setSelectedItem(dnoForm);
            //
            dnoForm.getTxtUsername().setText(ol.getUsername());
            dnoForm.getTxtPassword().setText(ol.getPassword());

        }
    }
}
