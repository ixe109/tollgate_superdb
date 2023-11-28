
package com.mycompany.tollgatesuperdb;

/**
 *
 * @author Isheanesu Musengi
 */
import java.sql.*;
import javax.swing.table.AbstractTableModel;
public class ResultSetTableModel extends AbstractTableModel {
    //dear devs from Ishe
    // ResultSet rows and columns are counted from 1 and JTable
    // rows and columns are counted from 0. When processing
    // ResultSet rows or columns for use in a JTable, it is
    // necessary to add 1 to the row or column number to manipulate
    // the appropriate ResultSet column (i.e., JTable column 0 is
    // ResultSet column 1 and JTable row 0 is ResultSet row 1).
    private final Connection connection;
    private final Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int numberOfRows;
    
    //keep track of db connection
    private boolean connectedToDatabase = false;
    //boa consctructorinintialises and obtains its meta data object;
    //determines number of rows
    
    public ResultSetTableModel(String url, String username,
            String password, String query) throws SQLException{
        
        //connect to database
        connection = DriverManager.getConnection(url,username,password);
        //create Statement to Query DB
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY                
        );
        //update database connection status
        connectedToDatabase = true;
        //set query and execute it
        setQuery(query);
    }
    //get class to represent Column type
    public Class getColumnClass(int column)
            throws IllegalStateException{
        //ensure database is available
        if(!connectedToDatabase)
            throw new IllegalStateException("Not connected to database");
        //determine java class of column
        try{
            String className = metaData.getColumnClassName(column +1);
            //return Class object that Represents Class name
            return Class.forName(className);
            
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return Object.class;  
    
    }
    
    @Override
    public int getColumnCount()
            throws IllegalStateException
    {
        if(!connectedToDatabase)
                throw new IllegalStateException("Not Connected to Database");
        //determine number of collumns
        try{
            return metaData.getColumnCount();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return 0;
    }
    
    //get name of a particular column in ResultSet
    public String getColumnName(int column) throws IllegalStateException
        {
            if(!connectedToDatabase)
                throw new IllegalStateException("Not Connected to Database");
            
            try{
                return metaData.getColumnName(column + 1);
            }catch(SQLException sqlException){
                sqlException.printStackTrace();
            }
            return "";
        }
    public int getRowCount() throws IllegalStateException
    {
        if(!connectedToDatabase)
                throw new IllegalStateException("Not Connected to Database");
        
        return numberOfRows;
    }
    
    //obtain value in a particular row and column
    public Object getValueAt(int row, int column) throws IllegalStateException
    {
        if(!connectedToDatabase)
                throw new IllegalStateException("Not Connected to Database");
        
        try{
            resultSet.absolute(row + 1);
            return resultSet.getObject(column + 1);
        }catch(SQLException sqlException){
           sqlException.printStackTrace();
        }
        return "";
    }
    
    //public Object getValueAt(int row,int )
    
    public void setQuery(String query)
            throws SQLException, IllegalStateException
    {
        //ensure db conexion is available
        if(!connectedToDatabase)
            throw new IllegalStateException("Not Connected to Database");
        
        //specify query and execute it 
        resultSet = statement.executeQuery(query);
        
        //obtain metadata for ReusltSet
        metaData = resultSet.getMetaData();
        
        //determine number of rows in ResultSet
        resultSet.last();//move to last row
        numberOfRows = resultSet.getRow();//get row number
        
       // notify JTABLE that model has Changed
        fireTableStructureChanged();
    }
    
    // close Statement and Connection
    public void disconnectFromDatabase()
    {
        if (connectedToDatabase){
            // close Statement and Connection
            try{
                resultSet.close();
                statement.close();
                connection.close();
            }
            catch (SQLException sqlException){
                sqlException.printStackTrace();
                }
            finally{// update database connection status
                
                connectedToDatabase = false;
             }
        }
    }
    
    
}
