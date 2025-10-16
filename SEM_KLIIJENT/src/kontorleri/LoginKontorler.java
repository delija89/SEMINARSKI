/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontorleri;

import domen.OrganizatorLova;
import forme.LoginForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koridnator.Kordinator;

/**
 *
 * @author Dzaja
 */
public class LoginKontorler {

    private final LoginForm lf;

    public LoginKontorler(LoginForm lf) {
        this.lf = lf;
        addActionListeners();
    }

    private void addActionListeners() {
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //OVDE PISEMO KOD VEZAN ZA KLIK DUGMETA ZA LOGIN FORMU
                prijava(e); //moze i poziv posto treba nekad vise metoda
            }

            private void prijava(ActionEvent e) {
                String username = lf.getTxtUsername().getText().trim(); //uzimamo uneti username
                String password = String.valueOf(lf.getTxtPassword().getPassword()); // slicno za password
                System.out.println(username);
                System.out.println(password);

                //OrganizatorLova ol = new OrganizatorLova(); //nemamo sve podatke pa nije bas logicno
                Komunikacija.getInstance().konekcija(); //usposatavimo konekciju sa bazom
                OrganizatorLova ulogovani = Komunikacija.getInstance().login(username, password); //metodu za prijavu korisnika 

                if (ulogovani == null) {
                    JOptionPane.showMessageDialog(lf, "Prijava organizatora u sistem je neuspesna", "GRESKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    Kordinator.getInstanca().setUlogovani(ulogovani); //cuvamo ulogovanog u kordinatoru da bi pisalo na glavnoj ko koristi
                    JOptionPane.showMessageDialog(lf, "Prijava organizatora u sistem je uspesna", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    Kordinator.getInstanca().otvoriMainFormu();
                    lf.dispose();
                }
            }
        });
    }

    public void otvoriFormu() {
        lf.setLocationRelativeTo(null);
        lf.setVisible(true);
    }
}
