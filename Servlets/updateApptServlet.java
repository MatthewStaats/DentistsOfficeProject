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

@WebServlet(urlPatterns = {"/updateApptServlet"})
public class updateApptServlet extends HttpServlet {

/********************************************************************
 *updateApptServlet: this servlet is used to update the Patient's current Appointment Date
 * then forward to successful appointment update page
 ********************************************************************/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session;
        session = request.getSession(true);
        Patient p2 = (Patient) session.getAttribute("p1");
        
        String date = request.getParameter("apptDate");
        
        String id = p2.getPatId();
        System.out.println(id);
        Appointment a2 = new Appointment();
        a2.selectDB2(id);
        a2.setDate(date);
        a2.updateDB();
        
        a2.display();
        
        session.setAttribute("a2", a2);
        System.out.println("Appointment a1 object has been added for Patient");
        
        RequestDispatcher rd = request.getRequestDispatcher("/patApptUpdate.jsp");
        rd.forward(request, response);
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