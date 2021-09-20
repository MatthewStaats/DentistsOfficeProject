import java.sql.*;

public class Dentists {
    private String id;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String office;
    
    public AppointmentList aList = new AppointmentList();
    
        
/********************************************************************
 * Default empty Dentist Constructor
 ********************************************************************/
    public Dentists() {
        id = "";
        password = "";
        firstName = "";
        lastName = "";
        email = "";
        office = "";
    }
    
/********************************************************************
 * Dentist Constructor passing in all values
 ********************************************************************/
    public Dentists(String id, String password, String firstName, String lastName,
                    String email, String office) {
        
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.office = office;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getOffice() {
        return office;
    }
    
    public void setOffice(String office) {
        this.office = office;
    }
    
/********************************************************************
 * selectDB() used to select the right Dentist record from the DataBase
 * @param ID used to know which record to select
 ********************************************************************/

    public void selectDB(String ID) {
        id = "";
        
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            
            try (Connection connect = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/matth/Downloads/DentistOfficeMDB.mdb")) {
                
                Statement statement = connect.createStatement();
                
                String sql;
                sql = "SELECT * FROM Dentists WHERE id = '"+ID+"'";
                System.out.println(sql);
                
                ResultSet result = statement.executeQuery(sql);
               
                result.next();
                id = result.getString(1);
                password = result.getString(2);
                firstName = result.getString(3);
                lastName = result.getString(4);
                email = result.getString(5);
                office = result.getString(6);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        
        getAppointments();
    }
    
/********************************************************************
 * insertDB() used to create and insert a new Dentist record into the DataBase
 * passing in these fields for the new Dentist record field values
 * @param id Dentist Id
 * @param password Dentist Password
 * @param firstName Dentist First Name
 * @param lastName Dentist Last Name
 * @param email Dentist Email
 * @param office Dentist Office number
 ********************************************************************/
    public void insertDB(String id, String password, String firstName, 
                     String lastName, String email, String office) throws ClassNotFoundException {
        
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connect = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/matth/Downloads/DentistOfficeMDB.mdb");
            String sql;
            sql = "insert into Dentists values('"+id+"', '"+password+"', '"+firstName+"', '"+lastName+"', '"+email+"', '"+office+"')";
            PreparedStatement prepstate = connect.prepareStatement(sql);
            prepstate.execute();
            
        } catch(SQLException ex) {
            System.out.println("Error " + ex);
        }
    }
/********************************************************************
 * updateDB() used to update the right selected Dentist record in the DataBase
 ********************************************************************/
    public void updateDB() {
        try {
            Connection connect = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/matth/Downloads/DentistOfficeMDB.mdb");
            String sql;
            sql = "UPDATE Dentists SET id = '"+id+"', passwd = '"+password+"', firstName = '"+firstName+"', lastName = '"+lastName+"', email = '"+email+"', office = '"+office+"' WHERE id = '"+id+"'";
            PreparedStatement pstmt = connect.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
    }
    
/********************************************************************
 * deleteDB() used to delete the selected Dentist record in the DataBase
 ********************************************************************/
    public void deleteDB() {
        try {
            
            Connection connect = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/matth/Downloads/DentistOfficeMDB.mdb");
            Statement statement = connect.createStatement(); 
            String sql;
            sql = "DELETE FROM Dentists WHERE id = '"+id+"'";
            statement.execute(sql);
            
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
    }
    
/********************************************************************
 * display() used to test and make sure the right Dentist record is being pulled correctly
 ********************************************************************/
    public void display() {
        System.out.println("Dentists ID: " + id);
        System.out.println("Password: " + password);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Office: " + office + "\n");
        
        aList.displayList();
    }
    
/********************************************************************
 * getAppointments() used to search the DataBase to select all Dentist Appointments
 * then display array list in Dentist Appointment list jsp
 ********************************************************************/
    public void getAppointments() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            
            try (Connection connect = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/matth/Downloads/DentistOfficeMDB.mdb")) {
                
                Statement statement = connect.createStatement();
                
                String sql;
                sql = "SELECT * FROM Appointments WHERE dentId = '"+id+"'";
                System.out.println(sql);
                
                ResultSet result = statement.executeQuery(sql);
               
                String appt; 
                Appointment a1;
             
                while (result.next()) {
                    appt = result.getString(2);
                    a1 = new Appointment();
                    a1.selectDB2(appt);
                    aList.addAppointment(a1);
                    
                  //list.displayList();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
        
    public static void main(String args []) {
        
        Dentists d1 = new Dentists();
        d1.selectDB("D201");
        d1.display();
    }
}