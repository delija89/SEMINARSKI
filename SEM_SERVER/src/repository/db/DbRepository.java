/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.db;

import repository.Repository;

/**
 *
 * @author Dzaja
 */
public interface DbRepository<T> extends Repository<T> { //ovaj repository se odnosi na povezivanje, odvezivanje, komitovanje i rollback na bazi

    default public void connect() throws Exception {
        DbConnectionFactory.getInstance().getConn();
    }

    default public void disconnect() throws Exception {
        DbConnectionFactory.getInstance().getConn().close();
    }

    default public void commit() throws Exception {
        DbConnectionFactory.getInstance().getConn().commit();
    }

    default public void rollback() throws Exception {
        DbConnectionFactory.getInstance().getConn().rollback();
    }
}
