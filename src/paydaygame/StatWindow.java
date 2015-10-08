/*
 * Copyright Galen Hite 2015. Reproduce At Your Peril
 */
package paydaygame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;

public class StatWindow {
    
    JFrame toJFrame;
    JPanel toJPanel;
    JTable dataTable;
    
    public StatWindow (int players, int months) {
        toJFrame = new JFrame("Statistics");
        toJPanel = new JPanel();
        toJFrame.add(toJPanel);
        dataTable = new JTable(months, players);
        TableModel tableModel = dataTable.getModel();
        JTableHeader th = dataTable.getTableHeader();
        TableColumnModel tcm = th.getColumnModel();
        int index = 0;
        while (index <= players-1) {
        TableColumn tc = tcm.getColumn(index);
        tc.setHeaderValue("Player " + index);
        index++;}
        th.repaint();
        JScrollPane scrollpane = new JScrollPane(dataTable);
        toJPanel.add(scrollpane);
        toJFrame.setSize(800, 438);
        toJFrame.setVisible(true);
        toJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void setValueAt(String value, int row, int column) {
        dataTable.setValueAt(value, row-1, column);
    }
    
}
