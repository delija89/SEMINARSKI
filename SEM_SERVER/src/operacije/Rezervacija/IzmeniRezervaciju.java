/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.Rezervacija;

import domen.RezervacijaLova;
import domen.StavkaRezervacijeLova;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;
import repository.db.DbConnectionFactory;

/**
 *
 * @author Dzaja
 */
public class IzmeniRezervaciju extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof RezervacijaLova)) {
            throw new Exception("Neispravna rezervacija za izmenu!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        RezervacijaLova rez = (RezervacijaLova) objekat;
        broker.edit(rez);

        String uslov = " JOIN vrstalova ON vrstalova.idVrstaLova = stavkarezervacijelova.idVrstaLova ";
        List<StavkaRezervacijeLova> sveStavke = broker.getAll(new StavkaRezervacijeLova(), uslov);
        for (StavkaRezervacijeLova s : sveStavke) {
            if (s.getRezervacijaLova() != null
                    && s.getRezervacijaLova().getIdRezervacijaLova() == rez.getIdRezervacijaLova()) {
                // ovde st IMA ispravan rb (iz baze) → delete radi precizan PK
                broker.delete(s);
            }
        }

        // 3) Reindex i INSERT novih stavki
        int rb = 1;
        if (rez.getStavke() != null) {
            for (StavkaRezervacijeLova s : rez.getStavke()) {
                s.setRezervacijaLova(rez);  // postavi FK
                s.setRb(rb++);              // jedinstveni redni broj u okviru rezervacije
                broker.add(s);              // INSERT sa oba PK dela → nema duplikata
            }
        }

        // 4) Commit transakcije (ako radiš manual commit u Opštoj SO, ovo može biti tamo)
        DbConnectionFactory.getInstance().getConn().commit();
    }

}
