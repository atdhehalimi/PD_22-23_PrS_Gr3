package gui;

import config.Config;
import server.ScheduleServerI;

import VoiceChat.VoiceChatClient;
import VoiceChat.VoiceChatServer;

import javax.swing.*;
import java.awt.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// Kjo është një panel chati që përfshin komente dhe lidhjen e zërit
public class ChatPanel extends JPanel {
    private final JTextArea comments;  // Fusha ku shfaqen komentet
    private final JTextField write;  // Fusha ku shkruhen komentet
    
    private final JButton sendButton;  // Butoni për të dërguar komentet
    private final JButton audioButton;  // Butoni për të lidhur zërin

    // Konstruktori për panelin e chatit
    public ChatPanel() {
        super(new FlowLayout(FlowLayout.LEFT));

        // Vendos bordurat për panelin
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Inicializon fushën e komenteve dhe vendos menaxherin e skrolimit
        comments = new JTextArea(25, 23);
        JScrollPane sp = new JScrollPane(comments);
        comments.setEditable(false);

        // Inicializon fushën e shkrimit
        write = new JTextField("", 20);

        // Inicializon butonin për të dërguar
        sendButton = new JButton("Send comment");
        sendButton.setBackground(Color.WHITE);

        // Inicializon butonin për lidhjen e zërit
        audioButton = new JButton("AudioLidhja");
        audioButton.setBackground(Color.WHITE);

        // Vendos dëgjuesin e veprimeve për butonin e lidhjes së zërit
        audioButton.addActionListener(e -> {
            try {
                new VoiceChatClient();
                
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        // Vendos komponentët në panelin e chatit
        add(sp);
        add(write);
        add(sendButton);
        add(audioButton);
        this.setPreferredSize(new Dimension(250, 120));
    }

    // Metoda për të shfaqur komentin
    public void drawComment(String comment) {
        this.comments.setText(this.comments.getText() + "\n" + comment);
        this.repaint();
    }

    // Metoda për të marrë fushën e shkrimit
    public JTextField getWrite() {
        return write;
    }

    // Metoda për të marrë butonin e dërgimit
    public JButton getSendButton() {
        return sendButton;
    }
}
