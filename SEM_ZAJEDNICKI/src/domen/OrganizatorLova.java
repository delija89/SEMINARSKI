/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Dzaja
 */
public class OrganizatorLova implements ApstraktniDomenskiObjekat {

    private int idOrganizator;
    private String ime;
    private String prezime;
    private String email;
    private Opstina opstina;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public OrganizatorLova() {
    }

    public OrganizatorLova(int idOrganizator, String ime, String prezime, String email, Opstina opstina, String username, String password) {
        this.idOrganizator = idOrganizator;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.opstina = opstina;
        this.username = username;
        this.password = password;
    }

    public Opstina getOpstina() {
        return opstina;
    }

    public void setOpstina(Opstina opstina) {
        this.opstina = opstina;
    }

    @Override
    public String toString() {
        return ime;
    }

    public int getIdOrganizator() {
        return idOrganizator;
    }

    public void setIdOrganizator(int idOrganizator) {
        this.idOrganizator = idOrganizator;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String vratiNazivTabele() {
        return "organizatorlova";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, email, idOpstina, username, password";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return "'" + ime + "', '" + prezime + "', '" + email + "', " + opstina.getIdOpstina()
                + ", '" + username + "', '" + password + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "organizatorlova.idOrganizator=" + idOrganizator;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "ime='" + ime + "', prezime='" + prezime + "', email='" + email + "', idOpstina=" + opstina.getIdOpstina()
                + ", username='" + username + "', password='" + password + "'";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            // Podaci iz tabele organizatorlova sa alias-om o
            int idOrganizator = rs.getInt("o.idOrganizator");
            String ime = rs.getString("o.ime");
            String prezime = rs.getString("o.prezime");
            String email = rs.getString("o.email");
            int idOpstina = rs.getInt("o.idOpstina");
            String username = rs.getString("o.username");
            String password = rs.getString("o.password");

            // Podaci iz tabele opstina sa alias-om op
            String naziv = rs.getString("op.naziv");

            // Napravi objekat opstina
            Opstina opstina = new Opstina(idOpstina, naziv);

            // Napravi objekat organizator lova
            OrganizatorLova ol = new OrganizatorLova(idOrganizator, ime, prezime, email, opstina, username, password);

            lista.add(ol);
        }
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrganizatorLova other = (OrganizatorLova) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

}
