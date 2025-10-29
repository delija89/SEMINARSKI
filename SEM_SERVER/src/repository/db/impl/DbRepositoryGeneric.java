/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import java.sql.*;
import domen.ApstraktniDomenskiObjekat;
import java.util.LinkedList;
import java.util.List;
import repository.db.DbConnectionFactory;
import repository.db.DbRepository;

/**
 *
 * @author Dzaja
 */
public class DbRepositoryGeneric implements DbRepository<ApstraktniDomenskiObjekat> { //OVDE SU CRUD OPERACIIJE NAD APSTRAKTNIM/SVIM NASIM OBJ

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new LinkedList<>();
        String upit = "SELECT * FROM " + param.vratiNazivTabele();
        if (uslov != null) { // ako ima neki uslov pretrage dodajemo ga ovde
            upit += uslov;
        }
        System.out.println("UPIT GET ALL: " + upit);

        Statement st = DbConnectionFactory.getInstance().getConn().createStatement();
        ResultSet rs = st.executeQuery(upit);
        lista = param.vratiListu(rs); //POSLACE U KONKRETNI ENTITET/KLASUU i vratice sta treba iz nje

        rs.close();
        st.close();

        return lista;
    }

    @Override
    public void add(ApstraktniDomenskiObjekat param) throws Exception {
        //insert
        String upit = "INSERT INTO " + param.vratiNazivTabele() + "(" + param.vratiKoloneZaUbacivanje()
                + ") VALUES (" + param.vratiVrednostZaUbacivanje() + ") ";
        System.out.println(upit); //provera upita zbog greski

        Statement st = DbConnectionFactory.getInstance().getConn().createStatement();
        st.executeUpdate(upit);

        st.close();
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "UPDATE " + param.vratiNazivTabele() + " SET " + param.vratiVrednostZaIzmenu() + " WHERE " + param.vratiPrimarniKljuc();;
        System.out.println(upit); //provera upita zbog greski

        Statement st = DbConnectionFactory.getInstance().getConn().createStatement();
        st.executeUpdate(upit);

        st.close();
    }

    @Override
    public void delete(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "DELETE FROM " + param.vratiNazivTabele() + " WHERE " + param.vratiPrimarniKljuc();
        System.out.println(upit); //provera upita zbog greski

        Statement st = DbConnectionFactory.getInstance().getConn().createStatement();
        st.executeUpdate(upit);

        st.close();
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int vratiId(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "INSERT INTO " + param.vratiNazivTabele()
                + "(" + param.vratiKoloneZaUbacivanje() + ") VALUES(" + param.vratiVrednostZaUbacivanje() + ")";
        Statement st = DbConnectionFactory.getInstance().getConn().createStatement();
        st.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = st.getGeneratedKeys();
        int id = -1;
        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();
        st.close();
        return id;
    }

}
