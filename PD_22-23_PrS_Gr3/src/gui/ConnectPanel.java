package gui;

import javax.swing.*;
import java.awt.*;

// Kjo është klasa e panelit ku klienti mund të lidhet me serverin
public class ConnectPanel extends JPanel {

    // Fusha ku përdoruesi shkruan adresën e serverit
    private final JTextField serverAddress;
    
    // Butoni për të lidhur me serverin
    private final JButton connectButton;

    // Konstruktori i klasës ConnectPanel
    public ConnectPanel() {
        super(new FlowLayout(FlowLayout.LEFT));  // Vendosim layout-in si rrjedhë nga e majta

        // Vendosim kufijtë e panelit
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Etiketa që tregon përdoruesin se çfarë të shkruajë
        JLabel titleLabel = new JLabel("Shkruaj adresën e serverit: ");
        
        // Fusha për të shkruar adresën e serverit
        serverAddress = new JTextField("", 20);
        
        // Butoni për të lidhur me serverin
        connectButton = new JButton("Lidhu me serverin");

        // Vendosim ngjyrën e sfondit të butonit si të bardhë
        connectButton.setBackground(Color.WHITE);

        // Shton etiketën, fushën dhe butonin në panel
        add(titleLabel);
        add(serverAddress);
        add(connectButton);
    }

    // Metoda për të marrë adresën e serverit
    public JTextField getServerAddress() {
        return serverAddress;
    }

    // Metoda për të marrë butonin e lidhjes me serverin
    public JButton getConnectButton() {
        return connectButton;
    }
}
