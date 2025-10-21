/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.OrganizatorLova;
import domen.RezervacijaLova;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dzaja
 */
public class ModelTabeleRezervacija extends AbstractTableModel {

    List<RezervacijaLova> lista;
    String[] kolone = {"idRezervacija", "datum", "sezona", "iznosRezervacije", "organizator", "lovackagrupa", "opstina"};

    public ModelTabeleRezervacija(List<RezervacijaLova> lista) {
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

    public List<RezervacijaLova> getLista() {
        return lista;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //{"idRezervacija", "datum", "sezona", "iznosRezervacije", "organizator", "lovackagrupa", "opstina"};
        RezervacijaLova rezervacijaLova = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch (columnIndex) {
            case 1:
                return rezervacijaLova.getIdRezervacijaLova();
            case 2:
                return sdf.format(rezervacijaLova.getDatumRezervacije());
            case 3:
                return rezervacijaLova.getSezona();
            case 4:
                return rezervacijaLova.getIznosRezervacije();
            case 5:
                return rezervacijaLova.getOrganizatorLova().getIme();
            case 6:
                return rezervacijaLova.getLovackaGrupa().getImeGrupe();
            case 7:
                return rezervacijaLova.getOpstina().getNazivOpstina();
            default:
                return "N/A";
        }
    }
}
