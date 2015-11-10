/*
 * Copyright Galen Hite 2015. Reproduce At Your Peril
 */
package paydaygame;

/*StatWindow opens a window on top of the initial GUI, which currently prints 
out the Players' balences at the end of each month. Hopefully, one day, it will 
print much more.*/

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;

public class StatWindow {
    
    JFrame toJFrame;
    JPanel toJPanel;
    JTable dataTable;
    
    public StatWindow (int players, int months) { /*This is the consturctor, which
        is called from startGame in Interface. It sets up the window. I find most
        J Components very cumbersome of introduce into a program, and so I like 
        to build my own versions when I can. This was not one of those times.*/
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
    
    public void setValueAt(String value, int row, int column) { /*This is a setter
        called by printCash in Player.*/
        dataTable.setValueAt(value, row-1, column);
    }
    
    public void close() {
        System.out.println("The window was supposed to close.");
        //toJFrame.dispatchEvent(new WindowEvent(toJFrame, WindowEvent.WINDOW_CLOSING));
        toJFrame.dispose();
    }
    
}
