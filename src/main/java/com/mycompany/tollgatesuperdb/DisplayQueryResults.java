
package com.mycompany.tollgatesuperdb;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.regex.PatternSyntaxException;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;
/**
 *
 * @author Isheanesu Musengi
 */
public class DisplayQueryResults extends JFrame {
    //DATABASE url,username and apassword
    private static final String DATABASE_URL = 
            "jdbc:mysql://localhost:3306/tollgate_superdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Isheanesu&^@8";
    
    //we have multiple tables so this is just for vehicle
    private static final String DEFAULT_QUERY = "SELECT * FROM vehicle";
    
    private static ResultSetTableModel tableModel;
    
    public static void main(String []args){
        try{
            //lets do some black magic
            //create Table model for results of Query SELECT * FROM vehicles
            tableModel = new ResultSetTableModel(DATABASE_URL,
                USERNAME, PASSWORD, DEFAULT_QUERY);
            //set up JTextArea for results of DEFAULT_QUERY
            final JTextArea queryArea = new JTextArea(DEFAULT_QUERY,
                    3, 100);
            queryArea.setWrapStyleWord(true);
            queryArea.setLineWrap(true);
            
            JScrollPane scrollPane = new JScrollPane(queryArea,
               ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
               ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
            
            //Set upp JButton for submitting queries
            JButton submitButton = new JButton("Submit Query");
            //create Box to manage placement of Query Area and
            //submitButton in GUI
            Box boxNorth = Box.createHorizontalBox();
            boxNorth.add(scrollPane);
            boxNorth.add(submitButton);
            
            JTable resultTable = new JTable(tableModel);
            
            JLabel filterLabel = new JLabel("Filter");
            final JTextField filterText = new JTextField();
            JButton filterButton = new JButton("Apply Filter");
            
            Box boxSouth = Box.createHorizontalBox();
            
            boxSouth.add(filterLabel);
            boxSouth.add(filterText);
            boxSouth.add(filterButton);
            
            //here we place GUI components on Jframe's content pane
            JFrame window = new JFrame("Displaying Query Results");
            window.add(boxNorth, BorderLayout.NORTH);
            window.add(new JScrollPane(resultTable),
                    BorderLayout.CENTER);
            window.add(boxSouth, BorderLayout.SOUTH);
            
            
            
            
            //create event listener for submit button
            submitButton.addActionListener(                    
                new ActionListener()
                {
                    
                    public void actionPerformed(ActionEvent event){
                       // perform a new query
                       try{
                           tableModel.setQuery(queryArea.getText());
                       }catch(SQLException sqlException){

                           JOptionPane .showMessageDialog(null,
                                   sqlException.getMessage(),
                                   "Database Error",
                                   JOptionPane.ERROR_MESSAGE);
                           //try to recover from invlaid user query
                           //by excecuting default query
                           try{
                               tableModel.setQuery(DEFAULT_QUERY);
                               queryArea.setText(DEFAULT_QUERY);
                            }catch(SQLException sqlException2){
                               JOptionPane .showMessageDialog(null,
                                   sqlException2.getMessage(),
                                   "Database Error",
                                   JOptionPane.ERROR_MESSAGE);
                               // ensure database connection is closed
                                tableModel.disconnectFromDatabase();

                                System.exit(1); // terminate application
                           }
                       }
                    }
                }

            );
            
            final TableRowSorter<TableModel> sorter =
            new TableRowSorter<TableModel>(tableModel);
            resultTable.setRowSorter(sorter);
            
            //create listener for filterButton
            filterButton.addActionListener(
                    new ActionListener()
                    {   
                        //pass listener to filter 
                        public void actionPerformed(ActionEvent e){
                            String text = filterText.getText();
                            
                            if(text.length()==0)
                                sorter.setRowFilter(null);
                            else{
                                try{
                                    sorter.setRowFilter(
                                    RowFilter.regexFilter(text));
                                }catch(PatternSyntaxException pse){
                                    JOptionPane.showMessageDialog(null,
                                            "Bad regex pattern", "Bad regex pattern",
                                            JOptionPane.ERROR_MESSAGE);
                                
                                }
                            
                            }
                        }
                    }
            );
            
            //dispose of window when user quits application
            //this overrides hide on close
            window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            window.setSize(500,200);
            window.setVisible(true);
            
            //ensure database is closed when user quits application
            window.addWindowListener(
                    new WindowAdapter(){
                        public void windowClosed(WindowEvent event){
                            tableModel.disconnectFromDatabase();
                            System.exit(0);
                        }
                    }
            );
            
        }catch(SQLException sqlException){
            JOptionPane.showMessageDialog(null, sqlException.getMessage(),
                    "Database error", JOptionPane.ERROR_MESSAGE);
            tableModel.disconnectFromDatabase();
            System.exit(1);//terminate application
        }
    }
    
    
  
    
}
