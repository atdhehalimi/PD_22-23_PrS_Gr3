package client;

import config.Config;
import gui.UpdateDisplay;
import server.ScheduleServerI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// Klasa klienti për orarin e leksioneve
public class ScheduleClient extends UnicastRemoteObject implements ScheduleClientI {
    private final String name;
    private final String connectionPassword;

    // Përditëson informacionin në GUI
    protected final UpdateDisplay updateDisplay;
    private ScheduleServerI connectedTo;

    // Konstruktori i klientit
    public ScheduleClient(String name, String connectionPassword, UpdateDisplay updateDisplay) throws RemoteException {
        this.name = name;
        this.connectionPassword = connectionPassword;
        this.updateDisplay = updateDisplay;
        connectedTo = null;
    }

    // Mënyra për të marrë emrin e klientit
    @Override
    public String getName() throws RemoteException {
        return this.name;
    }

    // Mënyra për të marrë orarin dhe për ta përditësuar në GUI
    @Override
    public void receiveSchedule(String[][] schedule) {
        updateDisplay.receiveSchedule((schedule));
    }

    // Mënyra për të marrë komentin dhe për ta përditësuar në GUI
    @Override
    public void receiveComment(String comment) throws RemoteException {
        this.updateDisplay.receiveComment(comment);
    }

    // Mënyra për të marrë fjalëkalimin e lidhjes
    protected String getConnectionPassword() {
        return connectionPassword;
    }

    // Mënyra për të lidhur me serverin
    public boolean connectToServer(ScheduleServerI server) {
        boolean success;

        try {
            boolean connected = server.connect(getConnectionPassword(), this);
            connectedTo = server;
            success = true;
        } catch (RemoteException e) {
            success = false;
        }

        return success;
    }

    // Mënyra për të shkëputur lidhjen me serverin
    public void disconnect() {
        try {
            if (connectedTo != null) {
                connectedTo.disconnect(Config.SERVER_CONNECTION_PASSWORD, this);
            }
        } catch (RemoteException e) {
        }
    }

    // Mënyra për të dërguar një koment te serveri
    public void sendComment(String comment) throws RemoteException {
        this.connectedTo.receiveComment(comment);
    }
}
