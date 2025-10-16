/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontorleri;

import domen.OrganizatorLova;
import forme.LoginForm;
import forme.MainForm;
import koridnator.Kordinator;

/**
 *
 * @author Dzaja
 */
public class MainFormKontroler {

    private final MainForm mf;

    public MainFormKontroler(MainForm mf) {
        this.mf = mf;
        addActionListeners();
    }

    private void addActionListeners() { //ova ce reagovati na klik na glavnoj formi

    }

    public void otvoriFormu() {
        OrganizatorLova ulogovani = Kordinator.getInstanca().getUlogovani(); // uzmemo ulogovanog iz kordinatora
        String imePrezime = ulogovani.getIme() + " " + ulogovani.getPrezime();
        mf.setLocationRelativeTo(null);
        mf.setVisible(true);
        mf.getLblUlogovani().setText(imePrezime);//upisemo u labelu ko je ulogovani
    }
}
