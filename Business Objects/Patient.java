import java.sql.*;

public class Patient {
    private String patId;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String insurance;
    
    public boolean available = false;
    
    public AppointmentList aList = new AppointmentList();
    
/********************************************************************
 * Default empty Patient Constructor
 ********************************************************************/
    public Patient() {
        patId = "";
        password = "";
        firstName = "";
        lastName = "";
        address = "";
        email = "";
        insurance = "";
    }
    
/********************************************************************
 * Patient Constructor passing in all values
 ********************************************************************/
    public Patient(String patId, String password, String firstName,
               String lastName, String address, String email, String insurance) {
        
        this.patId = patId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.insurance = insurance;
    }
    
    public boolean getAvailable() {
        return available;
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    public String getPatId() {
        return patId;
    }
    
    public void setPatId(String patId) {
        this.patId = patId;
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
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getInsurance() {
        return insurance;
    }
    
    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }
    
/********************************************************************
 * selectDB() used to select the right Patient record from the DataBase
 * @param id used to know which record to select
********************************************************************/
    public void selectDB(String id) {
        patId = "";
        
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection connect = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/matth/Downloads/DentistOfficeMDB.mdb")) {
                Statement statement = connect.createStatement();
                
                String sql;
                sql = "SELECT * FROM Patients WHERE patId = '"+id+"'";
                //System.out.println(sql);
                
                ResultSet result = statement.executeQuery(sql);
                
                result.next();
                patId = result.getString(1);
                password = result.getString(2);
                firstName = result.getString(3);
                lastName = result.getString(4);
                address = result.getString(5);
                email = result.getString(6);
                insurance = result.getString(7);
               
            }
            getAppointment();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
    
/********************************************************************
 * insertDB() used to create and insert a new Patient record into the DataBase
 * passing in these fields for the new Patient record field values
 * @param patId Patient Id
 * @param password Patient Password
 * @param firstName Patient First Name
 * @param lastName Patient Last Name
 * @param address Patient Home Address
 * @param email Patient Email
 * @param insurance Patient Insurance Company
 ********************************************************************/
    public void insertDB(String patId, String password, String firstName,
                 String lastName, String address, String email, String insurance) {
        
        try {
            
            Connection connect = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/matth/Downloads/DentistOfficeMDB.mdb");
            String sql;
            sql = "insert into Patients values('"+patId+"', '"+password+"', '"+firstName+"', '"+lastName+"', '"+address+"', '"+email+"', '"+insurance+"')";
            PreparedStatement prepstate = connect.prepareStatement(sql);
            prepstate.execute();
            
        } catch(SQLException ex) {
            System.out.println("Error " + ex);
        }
        
    }
    
/********************************************************************
 * updateDB() used to update the selected Patient record in the DataBase
 ********************************************************************/
    public void updateDB() {
        
        try {
            Connection connect = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/matth/Downloads/DentistOfficeMDB.mdb");
            String sql;
            sql = "UPDATE Patients SET patId = '"+patId+"', passwd = '"+password+"', firstName = '"+firstName+"', lastName = '"+lastName+"', addr = '"+address+"', email = '"+email+"', insCo = '"+insurance+"' WHERE patId = '"+patId+"'";
            PreparedStatement pstmt = connect.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
    }
    
/********************************************************************
 * deleteDB() used to delete the selected Patient record in the DataBase
 ********************************************************************/
    public void deleteDB() {
        
        try {
            
            Connection connect = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/matth/Downloads/DentistOfficeMDB.mdb");
            Statement statement = connect.createStatement(); 
            String sql;
            sql = "DELETE FROM Patients WHERE patId = '"+patId+"'";
            statement.execute(sql);
            
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
    }
    
/********************************************************************
* display() used to test and make sure the right Procedure record is being pulled correctly
 ********************************************************************/
    public void display() {
        
        System.out.println("Patients ID: " + patId);
        System.out.println("Password: " + password);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Address: " + address);
        System.out.println("Email: " + email);
        System.out.println("Insurance: " + insurance + "\n");
        System.out.println("Appointment Available: " + available);
        
    }
    
/********************************************************************
 * getAppointment() used to search the DataBase to select Patients Appointment Record
 * If Patient has Appointment display it in PatientApptInfo.jsp, keep available false
 * If Patient has no Appointment record mark available boolean true, so they can schedule appointment
 ********************************************************************/
    public void getAppointment() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            
            try (Connection connect = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/matth/Downloads/DentistOfficeMDB.mdb")) {
                
                Statement statement = connect.createStatement();
                
                String sql;
                sql = "SELECT * FROM Appointments WHERE patId = '"+patId+"'";
               // System.out.println(sql);
                
                ResultSet result = statement.executeQuery(sql);
               
               
                String appt; 
                Appointment a1;
                a1 = new Appointment();
                if (!result.next()) {
                    available = true;
                } else {
                    do {
                        appt = result.getString(3);
                        a1.selectDB(appt);
                    } while (result.next());
                }
             
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
    
    public static void main(String args []) {
        
        Patient p1 = new Patient();
        p1.selectDB("A901");
        p1.display();
        
    }
}