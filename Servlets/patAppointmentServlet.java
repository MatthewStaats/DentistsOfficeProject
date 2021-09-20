import Business.Appointment;
import Business.Patient;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/patAppointmentServlet"})
public class patAppointmentServlet extends HttpServlet {

/********************************************************************
 * patAppointmentServlet: this servlet is used to direct the Patient to the Appointment view/edit or add appointment pages
 * depending if boolean available is true or false will show edit or add pages
 ********************************************************************/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session;
        session = request.getSession(true);
        Patient p2 = (Patient) session.getAttribute("p1");
        
        String id = p2.getPatId();
        System.out.println(id);
        boolean ava = p2.getAvailable();
        if(ava == false) {
            Appointment a2 = new Appointment();
            a2.selectDB2(id);
            session.setAttribute("a2", a2);
            System.out.println("Appointment a1 object has been added for Patient");
            RequestDispatcher rd = request.getRequestDispatcher("/patApptInfo.jsp");
            rd.forward(request, response);
        } else{
            RequestDispatcher rd = request.getRequestDispatcher("/addAppt.jsp");
            rd.forward(request, response); 
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}