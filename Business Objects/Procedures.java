import java.sql.*;


public class Procedures {
    
    private String code;
    private String name;
    private String descrip;
    private double cost;
    
/********************************************************************
 * Default empty Procedure Constructor
 ********************************************************************/
    public Procedures() {
        code = "";
        name = "";
        descrip = "";
        cost = 0.0;
    }
    
/********************************************************************
 * Procedure Constructor passing in all values
 ********************************************************************/
    public Procedures(String code, String name, String descrip, double cost) {
        this.code = code;
        this.name = name;
        this.descrip = descrip;
        this.cost = cost;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescrip() {
        return descrip;
    }
    
    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
    
    public double getCost() {
        return cost;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }
    
/********************************************************************
 * selectDB() used to select the right Procedure record from the DataBase
 * @param Code used to know which Procedure record to select
 ********************************************************************/
    public void selectDB(String Code) {
        code = "";
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            
            try (Connection connect = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/matth/Downloads/DentistOfficeMDB.mdb")) {
                
                Statement statement = connect.createStatement();
                
                String sql;
                sql = "SELECT * FROM Procedures WHERE procCode = '"+Code+"'";
                System.out.println(sql);
                
                ResultSet result = statement.executeQuery(sql);
               
                result.next();
                code = result.getString(1);
                name = result.getString(2);
                descrip = result.getString(3);
                cost = result.getDouble(4);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
    
/********************************************************************
 * deleteDB() used to delete the selected Procedure record in the DataBase
 ********************************************************************/
    public void deleteDB() {
        
        try {
            
            Connection connect = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/matth/Downloads/DentistOfficeMDB.mdb");
            Statement statement = connect.createStatement(); 
            String sql;
            sql = "DELETE FROM Procedures WHERE procCode = '"+code+"'";
            statement.execute(sql);
            
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
    }
    
/********************************************************************
 * insertDB() used to create and insert a new Procedure record into the DataBase
 * passing in these fields for the new Procedure record field values
 * @param code Procedure Code
 * @param name Procedure Name
 * @param descrip Procedure Description
 * @param cost Procedure Cost
 ********************************************************************/
    public void insertDB(String code, String name, String descrip, double cost) {
        
        try {
            
            Connection connect = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/matth/Downloads/DentistOfficeMDB.mdb");
            String sql;
            sql = "insert into Procedures values('"+code+"', '"+name+"', '"+descrip+"', '"+cost+"')";
            PreparedStatement prepstate = connect.prepareStatement(sql);
            prepstate.execute();
            
        } catch(SQLException ex) {
            System.out.println("Error " + ex);
        }
    }
    
/********************************************************************
 * updateDB() used to update the selected Procedure record in the DataBase
 ********************************************************************/  
    public void updateDB() {
       
        try {
            Connection connect = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/matth/Downloads/DentistOfficeMDB.mdb");
            String sql;
            sql = "UPDATE Procedures SET procCode = '"+code+"', procName = '"+name+"', procDesc = '"+descrip+"', cost = '"+cost+"' WHERE procCode = '"+code+"'";
            PreparedStatement pstmt = connect.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Error: " + e);
        }
    }
    
/********************************************************************
 * display() used to test and make sure the right Procedure record is being pulled correctly
 ********************************************************************/
    public void display() {
        System.out.println("Procedure Code: " + code);
        System.out.println("Procedure Name: " + name);
        System.out.println("Procedure Description: " + descrip);
        System.out.println("Procedure Cost: " + cost);
    }
    
    public static void main(String args []) {
        
        Procedures p1 = new Procedures();
        p1.selectDB("P660");
        p1.display();
        
    }
}