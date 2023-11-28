
package com.mycompany.tollgatesuperdb;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * transactionTime is saved as transacrtionTime
 * @author Isheanesu Musengi
 */
public class preQuerisTest {
    public static void main (String args[]){
           
           
           System.out.println(searchByVehiclePlate("AAA1204"));
           System.out.println(searchByOwnerId("123456789003"));
           System.out.println(searchByChassisNo("WBA3N5C56EF123456"));
           System.out.println(searchByAreas());
           System.out.println(searchByWarrants());
           System.out.println(sqlWriteTransaction(6, "2023-11-24", "14:00:00", "937A1BA6", "1111", 2, 111101, true, 4, "cash"));
           System.out.println(searchByRfidCode("11228899"));
           
           
    }
    
    public static String searchByVehiclePlate(String vehiclePlate){
        if(vehiclePlate.length() !=7 ){
            throw new IllegalArgumentException(
                    "Number Plate must be 7 characters long");
        }
        String Query;
        Query ="select owner.ownerId,ownerName, ownerSurname, ownercontact, model, areas, vehiclePlate, warrants " +
             "FROM owner " +
            "INNER JOIN vehicle " +
            "ON owner.ownerId = vehicle.ownerId " +
            "where vehiclePlate = '"+vehiclePlate+"'; ";
        
        
        return Query;
    }
    
    public static String searchByOwnerId(String ownerId){
        if(ownerId.length() !=12 ){
            throw new IllegalArgumentException(
                    "OwnerId must be 12 characters long");
        }
        String Query;
        Query ="select owner.ownerId,ownerName, ownerSurname, ownercontact, model, areas, vehiclePlate, warrants " +
             "FROM owner " +
            "INNER JOIN vehicle " +
            "ON owner.ownerId = vehicle.ownerId " +
            "where owner.ownerId = '"+ownerId+"' ;";
        
        
        return Query;
    }
    
    public static String searchByChassisNo(String chassisNo){
        if(chassisNo.length() >20 ){
            throw new IllegalArgumentException(
                    "Chassis No must be 20 characters long or less");
        }
        String Query;
        Query ="select owner.ownerId,ownerName, ownerSurname, chassisNo , model, areas, vehiclePlate, warrants " +
             "FROM owner " +
            "INNER JOIN vehicle " +
            "ON owner.ownerId = vehicle.ownerId " +
            "where chassisNo = '"+chassisNo+"';";       
        
        return Query;
    }
    
    public  static String searchByRfidCode(String rfidCode){
        if(rfidCode.length()!=8 ){
            throw new IllegalArgumentException(
                    "RFID CODE must be 10 characters long");
        }
        String Query;
        Query ="select owner.ownerId,ownerName, ownerSurname, chassisNo , model, areas, vehiclePlate, warrants " +
             "FROM owner " +
            "INNER JOIN vehicle " +
            "ON owner.ownerId = vehicle.ownerId " +
            "where rfidCode = '"+rfidCode+"';";       
        
        return Query;
    };
    
    public static String searchByTransactionDate(String transactionDate){
            
        String Query;
        Query ="select owner.ownerId,ownerName, ownerSurname, chassisNo , model, areas, vehiclePlate, warrants " +
             "FROM owner " +
            "INNER JOIN vehicle " +
            "ON owner.ownerId = vehicle.ownerId " +
            "where chassisNo = '"+transactionDate+"';";       
        
        return Query;
    }
    /*
    public int generateTransactionId(String){
    
    };
    */
    public  static String sqlDateFormat(String Date){
        String sDF;//retrun value
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        sDF = currentDate.format(formatter);
        
        return sDF;
    }
    public  static String searchByAreas(){
        String Query;
        Query ="select owner.ownerId,ownerName, ownerSurname, chassisNo , model, areas, vehiclePlate, warrants " +
             "FROM owner " +
            "INNER JOIN vehicle " +
            "ON owner.ownerId = vehicle.ownerId " +
            "where areas>0;";       
        
        return Query;
    }
    public  static String searchByWarrants(){
        String Query;
        Query ="select owner.ownerId,ownerName, ownerSurname, chassisNo , model, areas, vehiclePlate, warrants " +
             "FROM owner " +
            "INNER JOIN vehicle " +
            "ON owner.ownerId = vehicle.ownerId " +
            "where warrants is not null;";       
        
        return Query;
    }
    
    
    
    
    public static String sqlWriteTransaction(int transactionId, String transactionDate, String transacrtionTime, String rfidCode, String tollgateId,
            double amountPaid, int EmployeeID, boolean passThrough, int vehicleClass, String paymentMethod ){
        String Query;
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd");
        DateTimeFormatter formatterr = DateTimeFormatter.ofPattern("HH:mm:ss");
        Query = "INSERT INTO transaction "+ 
        "(transactionId, transactionDate, transacrtionTime,"+
                " rfidCode, tollgateId,"+
           " amountPaid, EmployeeID, passThrough,"+
                " vehicleClass, paymentMethod)"+ 
        //"VALUES ('"+transactionId+"', '"+transactionDate+"', '"+
        "VALUES ('"+transactionId+"', '"+currentDate.format(formatter)+"', '"+        
                //transacrtionTime+"', '"+rfidCode+
                currentTime.format(formatterr)+"', '"+rfidCode+
                "', '"+tollgateId+"', "+amountPaid+", '"+EmployeeID+"', "
                +passThrough+", "+vehicleClass+", '"+paymentMethod+"');";       
       
        
        return Query;
    }
    //date format is YYYY-MM-DD
    //we get it from java with this code 
}

/*
 * import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateExample {
    public static void main(String[] args) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        
        // Format the date as 'YYYY-MM-DD'
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        
        // Print the formatted date
        System.out.println(formattedDate);
    }
}
 */