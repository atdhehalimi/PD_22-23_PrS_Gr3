package server;

import client.ScheduleClientI;

import java.rmi.Remote;
import java.rmi.RemoteException;

// Kjo është interfejsa për serverin e orarit.
public interface ScheduleServerI extends Remote {
    // Mënyra për të marrë emrin e serverit.
    String getName() throws RemoteException;

    // Mënyra për të lidhur me serverin.
    // Kthehet true nëse lidhja është e suksesshme, përndryshe false.
    boolean connect(String connectionPassword, ScheduleClientI otherInstance) throws RemoteException;
    
    // Mënyra për të shkëputur nga serveri.
    void disconnect(String connectionPassword, ScheduleClientI clientInstance) throws RemoteException;

    // Mënyra për të marrë një koment nga një klient dhe për ta transmetuar atë te të gjithë klientët e lidhur.
    void receiveComment(String comment) throws RemoteException;
}
