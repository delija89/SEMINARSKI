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
import domen.StavkaRezervacijeLova;
import forme.DodajNovuRezervacijuForm;
import forme.mod.FormaMod;
import forme.modeli.ModelTabeleStavkaRezervacije;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import koridnator.Kordinator;

/**
 *
 * @author Dzaja
 */
public class DodajRezervacijuKontoler {

    private final DodajNovuRezervacijuForm dnrForm;
    RezervacijaLova rezervacijaLova; //ovde ne bi smelo new vrvt
    List<StavkaRezervacijeLova> stavke = new ArrayList<>();

    public List<StavkaRezervacijeLova> getStavke() {
        return stavke;
    }

    public DodajRezervacijuKontoler(DodajNovuRezervacijuForm dnrForm) {
        this.dnrForm = dnrForm;
        addActionListeners();
    }

    public void otvoriFormu(FormaMod mod) {
        this.rezervacijaLova = new RezervacijaLova();
        this.rezervacijaLova.setStavke(new ArrayList<>());
        Kordinator.getInstanca().dodajParam("rezervacijaLova", this.rezervacijaLova);
        pripremiFormu(mod);
        dnrForm.setLocationRelativeTo(null);
        dnrForm.setVisible(true);
    }

    private void pripremiFormu(FormaMod mod) {
        List<OrganizatorLova> organizatori = komunikacija.Komunikacija.getInstance().ucitajOrganizatore();
        List<LovackaGrupa> grupe = komunikacija.Komunikacija.getInstance().ucitajLovackeGrupe();
        List<Opstina> opstine = komunikacija.Komunikacija.getInstance().ucitajSveOpstine();

        dnrForm.getCmbOrganizatori().removeAllItems();
        dnrForm.getCmbLovackeGrupe().removeAllItems();
        dnrForm.getCmbOpstine().removeAllItems();

        organizatori.forEach(o -> dnrForm.getCmbOrganizatori().addItem(o));
        grupe.forEach(g -> dnrForm.getCmbLovackeGrupe().addItem(g));
        opstine.forEach(op -> dnrForm.getCmbOpstine().addItem(op));

        for (Sezona s : Sezona.values()) {
            dnrForm.getCmbSezona().addItem(s);  //POPUNJAVANJE SEZONA IZ ENUMA
        }

        if (mod.equals(FormaMod.DODAJ)) {
            dnrForm.getBtnIzmeniRez().setVisible(false);
            dnrForm.getBtnDodajRez().setVisible(true);
        } else {
            // mod izmena – popuni polja sa postojećim podacima
            //RezervacijaLova rezervacija = (RezervacijaLova) Kordinator.getInstanca().vratiParam("rezervacijalova");
            //popuniFormuSaPodacima();
        }
    }

    private void addActionListeners() {

        // Dodavanje rezervacije
        dnrForm.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                Date datumRezervacije = new Date();
                try {
                    datumRezervacije = sdf.parse(dnrForm.getTxtDatum().getText().strip());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Unet nevalidan dantum");
                }

                Sezona sezona = (Sezona) dnrForm.getCmbSezona().getSelectedItem();
                OrganizatorLova organizatorLova = (OrganizatorLova) dnrForm.getCmbOrganizatori().getSelectedItem();
                LovackaGrupa lovackaGrupa = (LovackaGrupa) dnrForm.getCmbLovackeGrupe().getSelectedItem();
                Opstina opstina = (Opstina) dnrForm.getCmbOpstine().getSelectedItem();

                double sumaStavki = 0;
                for (StavkaRezervacijeLova stavkaRezervacijeLova : stavke) {
                    sumaStavki += stavkaRezervacijeLova.getIznos();
                }
                double iznosRezervacije = sumaStavki * lovackaGrupa.getBrojClanova();

                rezervacijaLova = new RezervacijaLova(-1, datumRezervacije, sezona, iznosRezervacije,
                        organizatorLova, lovackaGrupa, opstina);

                //StavkaRezervacijeLova s = new StavkaRezervacijeLova(rez, 0, null, 0, 0, null, 0);
                //List<StavkaRezervacijeLova> stavke1 = new ArrayList<>();
                //stavke1.add(s);
                rezervacijaLova.setStavke(stavke);

                try {
                    komunikacija.Komunikacija.getInstance().dodajRezervaciju(rezervacijaLova);
                    JOptionPane.showMessageDialog(null, "Sistem je zapamtio rezervaciju.");
                    dnrForm.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti rezervaciju.");
                }
            }
        });

        dnrForm.dodajStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Kordinator.getInstanca().dodajParam("rezervacijaLova", rezervacijaLova);
                Kordinator.getInstanca().otvoriDodajStavkuRezervacijeFormu();

            }
        });
    }

    private void popuniFormuSaPodacima() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void osveziTabeluStavki() {
        ModelTabeleStavkaRezervacije mtsr = new ModelTabeleStavkaRezervacije(new ArrayList<>(stavke));
        dnrForm.getTblStavke().setModel(mtsr);
    }

}
