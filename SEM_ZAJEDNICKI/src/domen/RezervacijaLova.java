/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dzaja
 */
public class RezervacijaLova implements ApstraktniDomenskiObjekat {

    private int idRezervacijaLova;
    private Date datumRezervacije;
    private Sezona sezona;
    private double iznosRezervacije;
    private OrganizatorLova organizatorLova;
    private LovackaGrupa lovackaGrupa;
    private Opstina opstina;
    private List<StavkaRezervacijeLova> stavke = new ArrayList<>();

    public RezervacijaLova() {
    }

    public RezervacijaLova(int idRezervacijaLovaa, Date datumRezervacije, Sezona sezona, double iznosRezervacije, OrganizatorLova organizatorLova, LovackaGrupa lovackaGrupa, Opstina opstina) {
        this.idRezervacijaLova = idRezervacijaLovaa;
        this.datumRezervacije = datumRezervacije;
        this.sezona = sezona;
        this.iznosRezervacije = iznosRezervacije;
        this.organizatorLova = organizatorLova;
        this.lovackaGrupa = lovackaGrupa;
        this.opstina = opstina;
    }

    public int getIdRezervacijaLova() {
        return idRezervacijaLova;
    }

    public void setIdRezervacijaLova(int idRezervacijaLova) {
        this.idRezervacijaLova = idRezervacijaLova;
    }

    public Date getDatumRezervacije() {
        return datumRezervacije;
    }

    public void setDatumRezervacije(Date datumRezervacije) {
        this.datumRezervacije = datumRezervacije;
    }

    public Sezona getSezona() {
        return sezona;
    }

    public void setSezona(Sezona sezona) {
        this.sezona = sezona;
    }

    public double getIznosRezervacije() {
        return iznosRezervacije;
    }

    public void setIznosRezervacije(double iznosRezervacije) {
        this.iznosRezervacije = iznosRezervacije;
    }

    public OrganizatorLova getOrganizatorLova() {
        return organizatorLova;
    }

    public void setOrganizatorLova(OrganizatorLova organizatorLova) {
        this.organizatorLova = organizatorLova;
    }

    public LovackaGrupa getLovackaGrupa() {
        return lovackaGrupa;
    }

    public void setLovackaGrupa(LovackaGrupa lovackaGrupa) {
        this.lovackaGrupa = lovackaGrupa;
    }

    public Opstina getOpstina() {
        return opstina;
    }

    public void setOpstina(Opstina opstina) {
        this.opstina = opstina;
    }

    public List<StavkaRezervacijeLova> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRezervacijeLova> stavke) {
        this.stavke = stavke;
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
        final RezervacijaLova other = (RezervacijaLova) obj;
        return this.idRezervacijaLova == other.idRezervacijaLova;
    }

    @Override
    public String toString() {
        return "RezervacijaLova{" + "idRezervacijaLova=" + idRezervacijaLova + ", datumRezervacije=" + datumRezervacije + ", sezona=" + sezona + ", iznosRezervacije=" + iznosRezervacije + ", organizatorLova=" + organizatorLova + ", lovackaGrupa=" + lovackaGrupa + ", opstina=" + opstina + ", stavke=" + stavke + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "rezervacijalova";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumRezervacije, sezona, iznosRezervacije, idOrganizator, idLovackaGrupa, idOpstina";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datumString = sdf.format(datumRezervacije);
        return "'" + datumString + "', '" + sezona.toString() + "', " + iznosRezervacije + ", "
                + organizatorLova.getIdOrganizator() + ", " + lovackaGrupa.getIdLovackaGrupa() + ", " + opstina.getIdOpstina();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "rezervacijalova.idRezervacijaLova=" + idRezervacijaLova;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datumString = sdf.format(datumRezervacije);
        return "datumRezervacije='" + datumString + "', sezona='" + sezona.toString() + "', iznosRezervacije=" + iznosRezervacije
                + ", idOrganizator=" + organizatorLova.getIdOrganizator() + ", idLovackaGrupa=" + lovackaGrupa.getIdLovackaGrupa()
                + ", idOpstina=" + opstina.getIdOpstina();
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idOpstina = rs.getInt("opstina.idOpstina");
            String naziv = rs.getString("opstina.naziv");
            Opstina opstina = new Opstina(idOpstina, naziv);

            int idOrganizator = rs.getInt("organizatorlova.idOrganizator");
            String ime = rs.getString("organizatorlova.ime");
            String prezime = rs.getString("organizatorlova.prezime");
            String email = rs.getString("organizatorlova.email");
            String username = rs.getString("organizatorlova.username");
            String password = rs.getString("organizatorlova.password");
            OrganizatorLova ol = new OrganizatorLova(idOrganizator, ime, prezime, email, opstina, username, password);

            int idLovackaGrupa = rs.getInt("lovackagrupa.idLovackaGrupa");
            String imeGrupe = rs.getString("lovackagrupa.imeGrupe");
            LovackaGrupa lovackaGrupa = new LovackaGrupa(idLovackaGrupa, imeGrupe, idOpstina);

            int idRezervacijaLova = rs.getInt("rezervacijalova.idRezervacijaLova");
            Date datumRezervacije = rs.getDate("rezervacijalova.datumRezervacije");
            Sezona sezona = Sezona.valueOf(rs.getString("rezervacijalova.sezona"));
            double iznosRezervacije = rs.getDouble("rezervacijalova.iznosRezervacije");

            RezervacijaLova rezervacijaLova = new RezervacijaLova(idRezervacijaLova, datumRezervacije, sezona,
                    iznosRezervacije, ol, lovackaGrupa, opstina);
            lista.add(rezervacijaLova);
        }

        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
