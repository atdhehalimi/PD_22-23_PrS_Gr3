package client;

import server.ScheduleServerI;

import java.rmi.Remote;
import java.rmi.RemoteException;

// Kjo është një ndërfaqe për klientin e orarit
public interface ScheduleClientI extends Remote {
    // Mënyra për të marrë emrin e klientit
    String getName() throws RemoteException;

    // Mënyra për të marrë orarin
    void receiveSchedule(String[][] schedule) throws RemoteException;

    // Mënyra për të marrë komentin
    void receiveComment(String comment) throws RemoteException;
}
