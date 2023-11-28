
package com.mycompany.tollgatesuperdb;
import java.sql.*;
/**
 *
 * @author Isheanesu Musengi
 */
public class Tollgatesuperdb {

    public static void main(String[] args) {
        final String DATABASE_URL =
                "jdbc:mysql://localhost:3306/tollgate_superdb";
        final String SELECT_QUERY ="SELECT * from vehicle;";
        final String ALL_TRANSACTIONS ="SELECT * from transaction;";
        final String ALL_PAYMENTS = "SELECT * from payment;";
        final String ALL_VEHICLES ="SELECT * from vehicle;";
        final String ALL_TOLLOPERATORS="SELECT * from tolloperator;";
        final String ALL_OWNERS ="SELECT * from owner;";
                //owner payment tollgate vehicle tolloperator transaction
        final String DB_USERNAME = "root";
        final String DB_PASSWORD = "Isheanesu&^@8";
        
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DB_USERNAME, DB_PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(ALL_VEHICLES)){
            //get RusultSet's mETA data
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            
           for (int i = 1; i <= numberOfColumns; i++){
              System.out.printf("%-8s\t",metaData.getColumnName(i)); 
           
           }                     
            System.out.println(); 
            
            while(resultSet.next()){
                for(int i = 1; i <= numberOfColumns; i++)
                    System.out.printf("%-8s\t", resultSet.getObject(i));
                System.out.println();
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        System.out.println("Hello World!");
    }
}
