/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;

/**
 *
 * @author Dzaja
 */
public interface Repository<T> { //sve operacije CRUD, potpisi. Odnosti se na odredjeni entitet iz nase baze

    List<T> getAll(T param, String uslov) throws Exception; // ista kao i dole samo ovo je za neku pretragu uz uslov

    void add(T param) throws Exception; // dodavanje

    void edit(T param) throws Exception; //izmena

    void delete(T param) throws Exception; //brise zadatati obj T

    List<T> getAll(); //vraca sve objekte, T je genericki ali moze biti bilo koja klasa

}
