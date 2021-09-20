import java.sql.*;

public class Appointment {
    
    private String date;
    private String patId;
    private String dentId;
    private String code;
    
/********************************************************************
 * Default empty Appointment Constructor
 ********************************************************************/
    public Appointment() {
        date = "";
        patId = "";
        dentId = "";
        code = "";
    }
    
/********************************************************************
 * Appointment Constructor passing in all values
 ********************************************************************/
    public Appointment(String date, String patId, String dentId, String code) {
        this.date = date;
        this.patId = patId;
        this.dentId = dentId;
        this.code = code;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getPatId() {
        return patId;
    }
    
    public void setPatId(String patId) {
        this.patId = patId;
    }
    
    public String getDentId() {
        return dentId;
    }
    
    public void setDentId(String dentId) {
        this.dentId = dentId;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
/********************************************************************
 * selectDB() used to select the right Dentist Appointment record from the DataBase
 * @param id used to know which record to select
 ********************************************************************/
    public void selectDB(String id) {
        dentId = "";
         
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            
            try (Connection connect = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/matth/Downloads/DentistOfficeMDB.mdb")) {
                
                Statement statement = connect.createStatement();
                
                String sql;
                sql = "SELECT * FROM Appointments WHERE dentId = '"+id+"'";
                //System.out.println(sql);
                
                ResultSet result = statement.executeQuery(sql);
               
                result.next();
                date = result.getString(1);
                patId = result.getString(2);
                dentId = result.getString(3);
                code = result.getString(4);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
    
/********************************************************************
 * selectDB() used to select the right Patient Appointment record from the DataBase
 * this method is also used to go through the Dentist Appointment List and get all Patients on their calendar
 * @param id used to know which record to select
 ********************************************************************/
    public void selectDB2(String id) {
        patId = "";
         
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            
            try (Connection connect = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/matth/Downloads/DentistOfficeMDB.mdb")) {
                
                Statement statement = connect.createStatement();
                
                String sql;
                sql = "SELECT * FROM Appointments WHERE patId = '"+id+"'";
                System.out.println(sql);
                
                ResultSet result = statement.executeQuery(sql);
               
                result.next();
                date = result.getString(1);
                patId = result.getString(2);
                dentId = result.getString(3);
                code = result.getString(4);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
    
/********************************************************************
 * insertDB() used to create and insert a new Appointment record into the DataBase
 * passing in these fields for the new Appointment record field values
 * @param date Appointment Date
 * @param patId Patient Id
 * @param dentId Dentist Id
 * @param code Appointment Procedure Code
 ********************************************************************/
    public void insertDB(String date, String patId, String dentId, String code) {
        
        try {
            
            Connection connect = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/matth/Downloads/DentistOfficeMDB.mdb");
            String sql;
            sql = "insert into Appointments values('"+date+"', '"+patId+"', '"+dentId+"', '"+code+"')";
            PreparedStatement prepstate = connect.prepareStatement(sql);
            prepstate.execute();
            
        } catch(SQLException ex) {
            System.out.println("Error " + ex);
        }
    }
    
/********************************************************************
 * updateDB() used to update the right selected Appointment record in the DataBase
 ********************************************************************/
    public void updateDB() {
        try {
            Connection connect = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/matth/Downloads/DentistOfficeMDB.mdb");
            String sql;
            sql = "UPDATE Appointments SET apptDateTime = '"+date+"', patId = '"+patId+"', dentId = '"+dentId+"', procCode = '"+code+"' WHERE patId = '"+patId+"'";
            PreparedStatement pstmt = connect.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
    }
    
/********************************************************************
 * deleteDB() used to delete the selected Appointment record in the DataBase
 ********************************************************************/
    public void deleteDB() {
        try {
            
            Connection connect = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/matth/Downloads/DentistOfficeMDB.mdb");
            Statement statement = connect.createStatement(); 
            String sql;
            sql = "DELETE FROM Appointments WHERE patId = '"+patId+"'";
            statement.execute(sql);
            
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
    }
    
/********************************************************************
 * display() used to test and make sure the right Appointment record is being pulled correctly
 ********************************************************************/
    public void display() {
        System.out.println("Appointment Date: " + date);
        System.out.println("Patient Id: " + patId);
        System.out.println("Dentist Id: " + dentId);
        System.out.println("Procedure Code: " + code);
    }
    
    public static void main(String args[]) {
        Appointment a1 = new Appointment();
        a1.selectDB("D202");
        a1.display();
        
        Appointment a2 = new Appointment();
        a1.selectDB2("A902");
        a1.display();
    
    }

}