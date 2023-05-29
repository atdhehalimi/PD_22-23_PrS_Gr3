package gui;

import javax.swing.*;
import java.awt.*;

// Kjo është klasa kryesore e klientit ku do të shfaqen të gjitha panelet
public class ClientFrame extends JFrame {

    // Konstruktori i klasës ClientFrame
    public ClientFrame(String title) {
        super(title);  // Vendos titullin e dritares së klientit

        // Vendos operacionin e mbylljes së dritares
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        // Vendos madhësinë e dritares
        setSize(7500, 650);
        
        // Vendos dritaren në qendër të ekranit
        setLocationRelativeTo(null);
        
        // Lejon ndryshimin e madhësisë së dritares
        setResizable(true);
        
        // Vendos ngjyrën e sfondit të dritares
        setBackground(Color.LIGHT_GRAY);

        // Marrë qelizinë e përmbajtjes së dritares dhe vendos menaxherin e vendosjes së objekteve në të
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

    }

}
