package server;

import client.ScheduleClientI;
import config.Config;
import gui.UpdateDisplay;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


// Kjo është klasa e serverit për shërbimin e orarit.
public class ScheduleServer extends UnicastRemoteObject implements ScheduleServerI {
    private final String name;  // Emri i serverit
    private final String connectionPassword;  // Fjalëkalimi i lidhjes
    private final List<ScheduleClientI> connectedClients;  // Lista e klientëve të lidhur

    // Konstruktori i serverit të orarit.
    public ScheduleServer(String name, String connectionPassword) throws RemoteException {
        this.name = name;
        this.connectionPassword = connectionPassword;
        System.out.println(name + " starting.");
        this.connectedClients = new ArrayList<>();
    }

    @Override
    public String getName() throws RemoteException {
        return this.name;
    }

    // Mënyra për të marrë fjalëkalimin e lidhjes.
    protected String getConnectionPassword() {
        return connectionPassword;
    }

    // Metoda për lidhje me serverin.
    @Override
    public boolean connect(String connectionPassword, ScheduleClientI clientInstance) throws RemoteException {
        if (!this.getConnectionPassword().equals(connectionPassword)) return false;

        connectedClients.add(clientInstance);
        System.out.println("Connected to client: " + clientInstance.getName());
        return true;
    }

    // Metoda për shkëputjen nga serveri.
    @Override
    public void disconnect(String connectionPassword, ScheduleClientI clientInstance) throws RemoteException {
        if (!this.getConnectionPassword().equals(connectionPassword)) return;

        connectedClients.remove(clientInstance);
        System.out.println("Client disconnected. Clients remaining: " + connectedClients.size());
    }

    // Metoda për të marrë komentin dhe për ta transmetuar atë te të gjithë klientët e lidhur.
    @Override
    public void receiveComment(String comment) throws RemoteException {
        for (ScheduleClientI client : connectedClients) {
            try {
                client.receiveComment(comment);
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Metoda për të lexuar orarin nga një file CSV.
    private String[][] readSchedule() {
        String[][] records;

        try {
            records = new String[30][4];
            File source = new File("timetable.csv");
            Scanner sc = new Scanner(source);

            List<String> lines = new ArrayList<>();
            while (sc.hasNext()) {
                lines.add(sc.next());
            }

            for (int i = 0; i < lines.size(); i++) {
                String[] tokens = lines.get(i).split(",");
                for (int j = 0; j < tokens.length; j++) {
                    records[i][j] = tokens[j];
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return records;
    }

    // Metoda për të filluar monitorimin e orarit dhe për ta transmetuar atë te të gjithë klientët e lidhur në intervala të


    public void startScheduleMonitor() {
        Runnable scheduleMonitor = () -> {
            System.out.println("Sending new schedule");
            String[][] schedule = readSchedule();
            for (ScheduleClientI client : connectedClients) {
                try {
                    System.out.println("Client " + client.getName());
                    client.receiveSchedule(schedule);
                } catch (RemoteException e) {
                    System.out.println(e.getMessage());
                }
            }
        };

        ScheduledExecutorService executor =
                Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(scheduleMonitor, 0, Config.CHECK_INTERVAL, Config.CHECK_INTERVAL_UNIT);
    }

}
