import client.ScheduleClient;
import gui.*;
import server.ScheduleServerI;
import config.Config;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientMain extends ClientFrame {

    private ScheduleClient scheduleClient;
    private final UpdateDisplay updateDisplay;
    private final ConnectPanel connectPanel;

    public ClientMain() {
        super(Config.CLIENT_SERVICE_NAME);

        setSize(1200,600);

        connectPanel = new ConnectPanel();
        getContentPane().add(connectPanel);

        UpdatesPanel updatesPanel = new UpdatesPanel();
        getContentPane().add(updatesPanel);

        updateDisplay = updatesPanel;

        String name = JOptionPane.showInputDialog("Emri?");

        connectPanel.getConnectButton().addActionListener(ignored -> {
            final String address = connectPanel.getServerAddress().getText();

            ScheduleServerI server = null;
            try {
                Registry registry = LocateRegistry.getRegistry(address, Config.SERVER_PORT);
                server = (ScheduleServerI) registry.lookup(Config.SERVER_SERVICE_NAME);
            } catch (RemoteException | NotBoundException e) {
               // updateDisplay.newUpdate("Exception connecting to server: " + e);
                return;
            }

            if (initClient(server)) {
                connectPanel.getServerAddress().setEnabled(false);
                connectPanel.getConnectButton().setEnabled(false);
            }
        });

        updatesPanel.getChat_panel().getSendButton().addActionListener(ignored -> {
            final String comment = updatesPanel.getChat_panel().getWrite().getText();
            try {
                this.scheduleClient.sendComment(name + ": " + comment);
                updatesPanel.getChat_panel().getWrite().setText("");
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (scheduleClient!= null) {
                    scheduleClient.disconnect();
                }
            }
        });

        setVisible(true);
    }

    private boolean initClient(final ScheduleServerI server) {
        Runnable clientCreation = () -> {
            try {
                this.scheduleClient = new ScheduleClient(
                        Config.CLIENT_SERVICE_NAME,
                        Config.SERVER_CONNECTION_PASSWORD,
                        updateDisplay);

                int randomClientPort = (int) (1500 + Math.random() * Config.CLIENT_PORT);

                Registry registry = LocateRegistry.createRegistry(randomClientPort);
                registry.bind(Config.CLIENT_SERVICE_NAME, scheduleClient);

                this.scheduleClient.connectToServer(server);
            } catch (Exception e) {
                //updateDisplay.newUpdate(e.toString());
            }
        };
        boolean success = true;
        Thread clientCreatingThread = new Thread(clientCreation);
        clientCreatingThread.setDaemon(true);
        clientCreatingThread.start();
        try {
            clientCreatingThread.join();
        } catch (InterruptedException e) {
            success = false;
            //updateDisplay.newUpdate("Exception joining client creation thread: " + e);
        }
        return success;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) { }

        java.awt.EventQueue.invokeLater(ClientMain::new);
    }

}
