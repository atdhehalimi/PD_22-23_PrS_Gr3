package gui;

import javax.swing.*;
import java.awt.*;

// Kjo është klasa e panelit ku shfaqet lista e përdoruesve në linjë
public class OnlinePanel extends JPanel {
    public OnlinePanel() {
        // Modeli i listës ku shtohen emrat e përdoruesve
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Ermir Rogova");
        listModel.addElement("Eliot Bytyci");
        listModel.addElement("Qendrim Gashi");
        listModel.addElement("Naim Braha");
        listModel.addElement("Bujar Fejzullahu");
        listModel.addElement("Armend Shabani");
        listModel.addElement("Elver Bajrami");
        listModel.addElement("Edmond Aliaga");
        listModel.addElement("Artan Berisha");

        // Krijimi i listës dhe vendosja e modelit
        JList<String> list = new JList<>(listModel);
        
        // Vendosim ngjyrën e sfondit të listës
        list.setBackground(new Color(215, 244, 228));

        // Qendrojmë tekstin në çdo element të listës
        DefaultListCellRenderer renderer = (DefaultListCellRenderer)list.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Vendosim fontin dhe madhësinë e tekstit
        list.setFont(new Font("Serif", Font.BOLD, 20));
        
        // Vendosim lartësinë e çdo qelie të listës
        list.setFixedCellHeight(70);
        
        // Shton listën në scrollpane për shfletim nëse ka shumë elemente
        JScrollPane sp = new JScrollPane(list);
        
        // Vendosim madhësinë e përdorueshme të scrollpane
        sp.setPreferredSize(new Dimension(300, 600));
        
        // Shton scrollpane në panel
        this.add(sp);

    }
}
