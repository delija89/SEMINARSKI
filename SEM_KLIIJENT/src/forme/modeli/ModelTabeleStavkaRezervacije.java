/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.RezervacijaLova;
import domen.StavkaRezervacijeLova;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dzaja
 */
public class ModelTabeleStavkaRezervacije extends AbstractTableModel {

    List<StavkaRezervacijeLova> lista;
    String[] kolone = {"rb", "uslovi", "brojDana", "cena", "iznos", "vrsta lova"}; //da li prikazati id, i da li pisati vrstu lova ili id kao u sqlyogu

    public ModelTabeleStavkaRezervacije(List<StavkaRezervacijeLova> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<StavkaRezervacijeLova> getLista() {
        return lista;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaRezervacijeLova stavka = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return stavka.getRb();
            case 1:
                return stavka.getUslovi();
            case 2:
                return stavka.getBrojDana();
            case 3:
                return stavka.getCena();
            case 4:
                return stavka.getIznos();
            case 5:
                return stavka.getVrstaLova().getNaziv();
            default:
                return "N/A";
        }
    }
}
