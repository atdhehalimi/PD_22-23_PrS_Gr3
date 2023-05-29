package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;

// Kjo është klasa e tabelës që do të shfaqet në GUI.
public class Table extends JPanel {
    String[][][] data; // Një varg i vargjeve 2D për të dhënat e çdo dite.
    String[] column;
    JTable[] tables; // Një varg i tabelave për çdo ditë.
    DefaultTableModel[] dtms; // Një varg i modeleve të tabelave për çdo ditë.
    String[] days = {"E hënë", "E martë", "E mërkurë", "E enjte", "E premte"};

    public Table() {
        this.column = new String[]{"", "08:00", "10:00", "12:00", "14:00"};

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        data = new String[days.length][7][5]; // Inicializon vargun e të dhënave.
        tables = new JTable[days.length]; // Inicializon vargun e tabelave.
        dtms = new DefaultTableModel[days.length]; // Inicializon vargun e modeleve të tabelave.

        String[] classes = {"153", "112", "113", "114", "Lab M", "Lab V"}; // Shto këtë rresht.

        for (int i = 0; i < days.length; i++) {
            // Shto ditën si qelizë të parë për të dhënat e çdo dite.
            data[i][0][0] = days[i];

            // Plotëso pjesën tjetër të kolonës me emrat e klasave.
            for (int j = 1; j < 7; j++) {
                data[i][j][0] = classes[j - 1]; // Modifiko këtë rresht.
            }

            dtms[i] = new DefaultTableModel(data[i], column);
            tables[i] = new JTable(dtms[i]);
            // Cilësimet e tjera të tabelës...
            JScrollPane sp = new JScrollPane(tables[i]);
            sp.setPreferredSize(new Dimension(1300, 600));
            this.add(sp);
        }


        this.setPreferredSize(new Dimension(900, 600 * days.length));
        this.setBackground(Color.LIGHT_GRAY);
    }

    public void drawSchedule(String[][] schedule) {
        // Flattening the schedule array into one single array
        String[] flatSchedule = new String[schedule.length * schedule[0].length];
        for (int i = 0; i < schedule.length; i++) {
            System.arraycopy(schedule[i], 0, flatSchedule, i * schedule[0].length, schedule[0].length);
        }
    
        // Loop through all cells in the table
        int scheduleIndex = 0;
        for (int k = 0; k < 5; k++) {
            for (int i = 1; i < data[0].length; i++) {
                for (int j = 1; j < data[0][0].length; j++) {
                    if (scheduleIndex < flatSchedule.length) {
                        data[k][i][j] = flatSchedule[scheduleIndex];
                        scheduleIndex++;
                    } else {
                        data[k][i][j] = "";  // Fill the remaining cells with an empty string or any default value
                    }
                }
            }
            dtms[k].setDataVector(data[k], column);
            dtms[k].fireTableDataChanged();
        }
        this.repaint();
    }
    
    
}
