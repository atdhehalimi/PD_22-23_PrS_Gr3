package gui;

import javax.swing.*;
import java.awt.*;

// Kjo është klasa e panelit të përditësimeve që përfshin tabelën dhe panelin e bisedës.
public class UpdatesPanel extends JPanel implements UpdateDisplay {
    private final Table table_panel;
    //private final JPanel available_panel;
    private final ChatPanel chat_panel;

    // Konstruktori për panelin e përditësimeve.
    public UpdatesPanel() {
        super(new BorderLayout());
        setLayout(new BorderLayout());
        table_panel = new Table();  // Krijo tabelën.
      //  available_panel = new OnlinePanel();
        chat_panel = new ChatPanel();  // Krijo panelin e bisedës.
        add(table_panel, BorderLayout.WEST);  // Shto tabelën në pjesën perëndimore të panelit.
      //  add(available_panel, BorderLayout.CENTER);
        add(chat_panel, BorderLayout.EAST);  // Shto panelin e bisedës në pjesën lindore të panelit.
        setSize(1500, 600);  // Cakto madhësinë e panelit.
        setBackground(Color.WHITE);  // Cakto ngjyrën e prapavijës së panelit.
        setVisible(true);  // Bëje panelin të dukshëm.
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Krijo një kufi të zbrazët rreth panelit.
        setBackground(Color.white);  // Cakto ngjyrën e prapavijës së panelit.
    }

    // Mënyra e marrjes së orarit dhe vizatimit të tij në tabelë.
    @Override
    public void receiveSchedule(String[][] schedule) {
        this.table_panel.drawSchedule(schedule);
        validate();
    }

    // Mënyra e marrjes së komentit dhe vizatimit të tij në panelin e bisedës.
    @Override
    public void receiveComment(String comment) {
        this.chat_panel.drawComment(comment);
    }

    // Mënyra e marrjes së panelit të bisedës.
    public ChatPanel getChat_panel() {
        return chat_panel;
    }

}
