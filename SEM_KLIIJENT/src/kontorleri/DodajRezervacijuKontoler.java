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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import koridnator.Kordinator;

/**
 *
 * @author Dzaja
 */
public class DodajRezervacijuKontoler {

    private final DodajNovuRezervacijuForm dnrForm;
    RezervacijaLova rezervacijaLova;
    List<StavkaRezervacijeLova> stavke = new ArrayList<>();

    public List<StavkaRezervacijeLova> getStavke() {
        return stavke;
    }

    public DodajRezervacijuKontoler(DodajNovuRezervacijuForm dnrForm) {
        this.dnrForm = dnrForm;
        addActionListeners();
    }

    public void otvoriFormu(FormaMod mod) {
        if (mod.equals(FormaMod.DODAJ)) {
            this.rezervacijaLova = new RezervacijaLova();
            this.rezervacijaLova.setStavke(new ArrayList<>());
        } else {
            this.rezervacijaLova = (RezervacijaLova) Kordinator.getInstanca().vratiParam("rezervacijaLova");
        }
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
            popuniFormuSaPodacima();
            dnrForm.getBtnIzmeniRez().setVisible(true);
            dnrForm.getBtnDodajRez().setVisible(false);
        }
    }

    private void addActionListeners() {

        dnrForm.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dnrForm.getTxtDatum().getText().trim().isEmpty()
                        || dnrForm.getCmbSezona().getSelectedItem() == null
                        || dnrForm.getCmbOrganizatori().getSelectedItem() == null
                        || dnrForm.getCmbLovackeGrupe().getSelectedItem() == null
                        || dnrForm.getCmbOpstine().getSelectedItem() == null
                        || rezervacijaLova.getStavke().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Sva polja moraju biti popunjena i mora postojati barem jedna stavka rezervacije!",
                            "Nevalidan unos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                Date datumRezervacije = new Date();
                try {
                    datumRezervacije = sdf.parse(dnrForm.getTxtDatum().getText().strip());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Unet nevalidan datum");
                }

                Sezona sezona = (Sezona) dnrForm.getCmbSezona().getSelectedItem();
                OrganizatorLova organizatorLova = (OrganizatorLova) dnrForm.getCmbOrganizatori().getSelectedItem();
                LovackaGrupa lovackaGrupa = (LovackaGrupa) dnrForm.getCmbLovackeGrupe().getSelectedItem();
                Opstina opstina = (Opstina) dnrForm.getCmbOpstine().getSelectedItem();

                double sumaStavki = 0;
                for (StavkaRezervacijeLova stavkaRezervacijeLova : rezervacijaLova.getStavke()) {
                    sumaStavki += stavkaRezervacijeLova.getIznos();
                }
                LovackaGrupa selektovanaGrupa = (LovackaGrupa) dnrForm.getCmbLovackeGrupe().getSelectedItem();
                double iznosRezervacije = sumaStavki * selektovanaGrupa.getBrojClanova();
                dnrForm.getTxtIznos().setText(String.valueOf(iznosRezervacije));
                rezervacijaLova.setIznosRezervacije(iznosRezervacije);

                rezervacijaLova = new RezervacijaLova(-1, datumRezervacije, sezona, iznosRezervacije,
                        organizatorLova, lovackaGrupa, opstina);

                rezervacijaLova.setStavke(stavke);

                try {
                    komunikacija.Komunikacija.getInstance().dodajRezervaciju(rezervacijaLova);
                    JOptionPane.showMessageDialog(null, "Sistem je zapamtio rezervaciju.");
                    dnrForm.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti rezervaciju.");
                }
            }
        }
        );

        dnrForm.izmeniAddActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    rezervacijaLova.setDatumRezervacije(sdf.parse(dnrForm.getTxtDatum().getText().strip()));
                    rezervacijaLova.setSezona((Sezona) dnrForm.getCmbSezona().getSelectedItem());
                    rezervacijaLova.setOrganizatorLova((OrganizatorLova) dnrForm.getCmbOrganizatori().getSelectedItem());
                    rezervacijaLova.setLovackaGrupa((LovackaGrupa) dnrForm.getCmbLovackeGrupe().getSelectedItem());
                    rezervacijaLova.setOpstina((Opstina) dnrForm.getCmbOpstine().getSelectedItem());

                    //preracunajIznose();
                    preracunajIznosZaCuvanje();

                    try {
                        komunikacija.Komunikacija.getInstance().izmeniRezervaciju(rezervacijaLova);
                    } catch (Exception ex) {
                        Logger.getLogger(DodajRezervacijuKontoler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "Sistem je izmenio rezervaciju lova.");
                    dnrForm.dispose();
                    Kordinator.getInstanca().getPrKontroler().osveziFormu();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Sistem ne moze da izmeni rezervaciju!");
                    ex.printStackTrace();
                }
            }
        }
        );

        dnrForm.dodajStavkuAddActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                Kordinator.getInstanca().dodajParam("rezervacijaLova", rezervacijaLova);
                Kordinator.getInstanca().otvoriDodajStavkuRezervacijeFormu();
                preracunajIznose();
            }
        }
        );

        dnrForm.obrisiStavkuAddActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                int red = dnrForm.getTblStavke().getSelectedRow();
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
                    ModelTabeleStavkaRezervacije mtsr = (ModelTabeleStavkaRezervacije) dnrForm.getTblStavke().getModel();
                    StavkaRezervacijeLova stavka = mtsr.getLista().get(red);

                    try {
                        rezervacijaLova.getStavke().remove(red);
                        osveziTabeluStavki();
                        for (int i = 0; i < rezervacijaLova.getStavke().size(); i++) {
                            rezervacijaLova.getStavke().get(i).setRb(i + 1);
                        }

                        mtsr.setLista(rezervacijaLova.getStavke());
                        mtsr.fireTableDataChanged();
                        preracunajIznose();
                        JOptionPane.showMessageDialog(null, "Sistem je obrisao stavku rezervacije lova!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sistem ne moze da obrise stavku rezervacije lova!");
                        ex.printStackTrace();
                    }
                }
            }

        }
        );

        dnrForm.addIzmeniStavkuActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                int red = dnrForm.getTblStavke().getSelectedRow();

                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Niste odabrali stavku!");
                    return;
                }

                ModelTabeleStavkaRezervacije mtsr = (ModelTabeleStavkaRezervacije) dnrForm.getTblStavke().getModel();
                StavkaRezervacijeLova stavka = mtsr.getLista().get(red);

                Kordinator.getInstanca().dodajParam("stavkaZaIzmenu", stavka);
                Kordinator.getInstanca().dodajParam("rezervacijaLova", rezervacijaLova);

                Kordinator.getInstanca().otvoriIzmeniStavkuFormu();
                preracunajIznose();
                osveziTabeluStavki();
            }
        }
        );
    }

    private void preracunajIznose() {
        double sumaStavki = 0;
        for (StavkaRezervacijeLova stavkaRezervacijeLova : rezervacijaLova.getStavke()) {
            sumaStavki += stavkaRezervacijeLova.getIznos();
        }
        LovackaGrupa selektovanaGrupa = (LovackaGrupa) dnrForm.getCmbLovackeGrupe().getSelectedItem();
        double iznosRezervacije = sumaStavki * selektovanaGrupa.getBrojClanova();
        dnrForm.getTxtIznos().setText(String.valueOf(iznosRezervacije));
        //rezervacijaLova.setIznosRezervacije(iznosRezervacije);
    }

    private void preracunajIznosZaCuvanje() {
        double sumaStavki = 0;
        for (StavkaRezervacijeLova s : rezervacijaLova.getStavke()) {
            sumaStavki += s.getIznos();
        }
        LovackaGrupa grupa
                = (LovackaGrupa) dnrForm.getCmbLovackeGrupe().getSelectedItem();
        double iznos = sumaStavki * grupa.getBrojClanova();
        rezervacijaLova.setIznosRezervacije(iznos);
    }

    private void popuniFormuSaPodacima() {
        dnrForm.getTxtDatum().setText(new SimpleDateFormat("dd.MM.yyyy").format(rezervacijaLova.getDatumRezervacije()));
        dnrForm.getCmbSezona().setSelectedItem(rezervacijaLova.getSezona());
        dnrForm.getCmbOrganizatori().setSelectedItem(rezervacijaLova.getOrganizatorLova());
        dnrForm.getCmbLovackeGrupe().setSelectedItem(rezervacijaLova.getLovackaGrupa());
        dnrForm.getCmbOpstine().setSelectedItem(rezervacijaLova.getOpstina());
        dnrForm.getTxtIznos().setText(String.valueOf(rezervacijaLova.getIznosRezervacije()));

        ModelTabeleStavkaRezervacije mtsr = new ModelTabeleStavkaRezervacije(rezervacijaLova.getStavke());
        dnrForm.getTblStavke().setModel(mtsr);

        dnrForm.getBtnDodajRez().setVisible(false);
        dnrForm.getBtnIzmeniRez().setVisible(true);
    }

    public void osveziTabeluStavki() {
        ModelTabeleStavkaRezervacije mtsr = new ModelTabeleStavkaRezervacije(rezervacijaLova.getStavke());
        dnrForm.getTblStavke().setModel(mtsr);
        preracunajIznose();
    }
}
