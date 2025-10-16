/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontorleri;

import domen.OrganizatorLova;
import forme.PrikazSvihOrganizatoraForm;
import forme.modeli.ModelTabeleOrganizator;
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
public class PrikazSvihOrganizatoraKontroler {

    private final PrikazSvihOrganizatoraForm psoForm;

    public PrikazSvihOrganizatoraKontroler(PrikazSvihOrganizatoraForm psoForm) {
        this.psoForm = psoForm;
        addActionListeners();
    }

    private void addActionListeners() { //ova ce reagovati na klik na glavnoj formi
        psoForm.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = psoForm.getTblOrganizatori().getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(psoForm, "Nije izabran nijedan red/organizator iz tabele!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleOrganizator mto = (ModelTabeleOrganizator) psoForm.getTblOrganizatori().getModel();
                    OrganizatorLova ol = mto.getLista().get(row);
                    try {
                        Komunikacija.getInstance().obrisiOrganizatora(ol);
                        JOptionPane.showMessageDialog(psoForm, "Sistem je uspesno obrisao organizatora lova", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        ucitajOrganizatore();//da bi se refreshovala tabela
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(psoForm, "Sistem ne moze da obrise organizatora lova!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        psoForm.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = psoForm.getTblOrganizatori().getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(psoForm, "Nije izabran nijedan red/organizator iz tabele!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleOrganizator mto = (ModelTabeleOrganizator) psoForm.getTblOrganizatori().getModel();
                    OrganizatorLova ol = mto.getLista().get(row);

                    Kordinator.getInstanca().dodajParam("organizatorlova", ol);
                    Kordinator.getInstanca().otvoriIzmeniOrganizatoraFormu();
                }
            }
        });
    }

    public void otvoriFormu() {
        ucitajOrganizatore();
        psoForm.setLocationRelativeTo(null);
        psoForm.setVisible(true);
    }

    public void ucitajOrganizatore() {
        //ucitavamo listu svih organizatora u tabelu forme
        List<OrganizatorLova> sviOrganizatoriLova = komunikacija.Komunikacija.getInstance().ucitajOrganizatore(); //ovako pozivamo obradu klij. zahteva
        ModelTabeleOrganizator mto = new ModelTabeleOrganizator(sviOrganizatoriLova); //ovime sve ucitane ubacimo u nas model tabele
        psoForm.getTblOrganizatori().setModel(mto);
    }

    public void osveziFormu() {
        ucitajOrganizatore();
    }

}
