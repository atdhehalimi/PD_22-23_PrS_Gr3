import config.Config;
import server.ScheduleServer;

import javax.swing.*;

import VoiceChat.VoiceChatServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {

    public ServerMain() {       
        initServer();
        new VoiceChatServer();
    }

    private void initServer() {
        Runnable serverCreation = () -> {
            try {
                ScheduleServer scheduleServer = new ScheduleServer(
                        Config.SERVER_SERVICE_NAME,
                        Config.SERVER_CONNECTION_PASSWORD);

                Registry registry = LocateRegistry.createRegistry(Config.SERVER_PORT);
                registry.bind(Config.SERVER_SERVICE_NAME, scheduleServer);

                scheduleServer.startScheduleMonitor();

            } catch (Exception e) {
                System.out.println(e.toString());
            }
        };

        Thread serverCreationThread = new Thread(serverCreation);
        serverCreationThread.setDaemon(true);
        serverCreationThread.start();
        try {
            serverCreationThread.join();
        } catch (InterruptedException e) {
            System.out.println("Exception joining server creation thread: " + e);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) { }

        java.awt.EventQueue.invokeLater(ServerMain::new);
    }
}
