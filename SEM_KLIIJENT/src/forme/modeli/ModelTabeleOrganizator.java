/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.OrganizatorLova;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dzaja
 */
public class ModelTabeleOrganizator extends AbstractTableModel {

    List<OrganizatorLova> lista;
    String[] kolone = {"idOrganizator", "ime", "prezime", "email", "idOpstina"}; //inace ima i username i passsword ali necemo to ispisivati

    public ModelTabeleOrganizator(List<OrganizatorLova> lista) {
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

    public List<OrganizatorLova> getLista() {
        return lista;
    }

    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OrganizatorLova organizatorLova = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return organizatorLova.getIdOrganizator();
            case 1:
                return organizatorLova.getIme();
            case 2:
                return organizatorLova.getPrezime();
            case 3:
                return organizatorLova.getEmail();
            case 4:
                return organizatorLova.getOpstina().getIdOpstina();
            default:
                return "N/A";
        }
    }

}
