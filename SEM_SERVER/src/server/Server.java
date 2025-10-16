/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author Dzaja
 */
public class Server extends Thread {

    boolean kraj = false;
    ServerSocket ss;
    List<ObradaKlijentskihZahteva> klijenti = new LinkedList<>();

    @Override
    public void run() {
        try {
            ss = new ServerSocket(9000);
            System.out.println("SERVERSKI SOKET OTVOREN");
            while (!kraj) {
                try {
                    Socket s = ss.accept();
                    System.out.println("Klijent povezan");
                    ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                    klijenti.add(okz);
                    okz.start();
                } catch (SocketException ex) {
                    System.out.println("SERVERSKI SOKET ZATVOREN");
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void zaustaviServer() {
        kraj = true;
        try {
            for (ObradaKlijentskihZahteva k : klijenti) {
                k.prekiniNit();
            }
            ss.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
