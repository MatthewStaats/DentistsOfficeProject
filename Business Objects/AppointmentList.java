import java.util.ArrayList;


public class AppointmentList {
    public int count;

    
    public ArrayList<Appointment> aList = new ArrayList<Appointment>();
    
/********************************************************************
 * addAppointment(Appointment a1) used to add Appointment to Dentist Appointment List
 * @param a1 used to add the new Appointment to the List and increase increment
 ********************************************************************/
    public void addAppointment(Appointment a1) {
        aList.add(count,a1);
        count++;
    }
    
/********************************************************************
 * displayList() used to display the Appointment List for Dentist's
 ********************************************************************/
    public void displayList() {
        for (int x = 0; x < aList.size(); x++) {
            aList.get(x).display();
        }
    }
    
    public static void main(String args[]) {
        AppointmentList list = new AppointmentList();
        Appointment a1 = new Appointment("April 16, 1991, 1am", "A901", "D203","P910");
        Appointment a2 = new Appointment("November 15, 1984, 9am", "A904", "D201", "P650");
        list.addAppointment(a1);
        list.addAppointment(a2);
        list.displayList();
    }
    
}