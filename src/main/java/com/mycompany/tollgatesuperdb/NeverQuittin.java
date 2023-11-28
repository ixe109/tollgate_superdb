
package com.mycompany.tollgatesuperdb;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.Dimension;
import java.util.regex.PatternSyntaxException;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
/**
 *
 * @author Isheanesu Musengi
 */
public class NeverQuittin extends JFrame{
    private static final String DATABASE_URL = 
            "jdbc:mysql://localhost:3306/tollgate_superdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Isheanesu&^@8";
    
    //we have multiple tables so this is just for vehicle
    private static final String DEFAULT_QUERY = "SELECT * FROM vehicle";
    
    private static ResultSetTableModel tableModel;
    public static void main(String args[]){
        try{
            
            //lets do some black magic
            //create Table model for results of Query SELECT * FROM vehicles
            tableModel = new ResultSetTableModel(DATABASE_URL,
                USERNAME, PASSWORD, DEFAULT_QUERY);
            //set up JTextArea for results of DEFAULT_QUERY
            final JTextArea queryArea = new JTextArea(DEFAULT_QUERY,
                    1, 50);
            queryArea.setWrapStyleWord(false);
            queryArea.setLineWrap(false);
            
            JScrollPane scrollPane = new JScrollPane(queryArea,
               ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
               ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
            JButton submitButton = new JButton("Search");
           JTable resultTable = new JTable(tableModel);
        // Create a DefaultListModel and add data to it
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Vehicle Plate");
        listModel.addElement("RFID");
        listModel.addElement("OwnerId");
        listModel.addElement("Warrants");

        // Create a JList with the DefaultListModel
        JList<String> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create a JScrollPane and add the JList to it
        JScrollPane scrollPanek = new JScrollPane(list);

        // Add the JScrollPane to a panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPanek, BorderLayout.CENTER);
         JTextField searchText = new JTextField();
         searchText.setMaximumSize(new Dimension(200, 30));
         Box boxNorth = Box.createHorizontalBox();
            boxNorth.add(scrollPane);
            boxNorth.add(submitButton);
            boxNorth.add(scrollPanek);
           // boxNorth.add(searchText);
            //boxNorth.add();
            //boxNorth.add();
        JFrame window = new JFrame("Displaying Query Results");
            window.add(boxNorth, BorderLayout.NORTH);
            window.add(new JScrollPane(resultTable),
                    BorderLayout.CENTER);
            

        final TableRowSorter<TableModel> sorter =
            new TableRowSorter<TableModel>(tableModel);
            resultTable.setRowSorter(sorter);
            
        window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            window.setSize(1000,600);
            window.setVisible(true);
            
            
        }catch(SQLException sqlException){
            JOptionPane.showMessageDialog(null, sqlException.getMessage(),
                    "Database error", JOptionPane.ERROR_MESSAGE);
            tableModel.disconnectFromDatabase();
            System.exit(1);//terminate application
        }
    
    }
}
