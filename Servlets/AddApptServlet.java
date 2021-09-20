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

@WebServlet(urlPatterns = {"/AddApptServlet"})
public class AddApptServlet extends HttpServlet {

/********************************************************************
 * AddApptServlet: this servlet is used add an Appointment for a Patient that has no appointment already
 * then forward successful login to the patApptInfo jsp
 ********************************************************************/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String date;
        String dentId;
        String code;
        
        HttpSession session;
        session = request.getSession(true);
        Patient p2 = (Patient) session.getAttribute("p1");
        
        date = request.getParameter("apptDate");
        dentId = request.getParameter("dentId");
        code = request.getParameter("procCode");
        
        String id = p2.getPatId();
        System.out.println(id);
        Appointment a2 = new Appointment();
        
        a2.setDate(date);
        a2.setPatId(id);
        a2.setDentId(dentId);
        a2.setCode(code);
        
        a2.insertDB(date, id, dentId, code);
        a2.display();
        session.setAttribute("a2", a2);
        System.out.println("Appointment a1 object has been added for Patient");
        
        RequestDispatcher rd = request.getRequestDispatcher("/patApptInfo.jsp");
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