import Business.Patient;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/patUpdateServlet"})
public class patUpdateServlet extends HttpServlet {


/********************************************************************
 * patUpdateServlet: this servlet is used to update the patient record that's logged in
 * then forward to the successful Patient update jsp
 ********************************************************************/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session;
        session = request.getSession(true);
        Patient p2 = (Patient) session.getAttribute("p1");
        
        String id = p2.getPatId();
        p2.selectDB(id);
        String fn = request.getParameter("patFn");
        String ln = request.getParameter("patLn");
        String addr = request.getParameter("patAddress");
        String email = request.getParameter("patEmail");
        String insur = request.getParameter("patInsurance");
        p2.setEmail(email);
        p2.setFirstName(fn);
        p2.setLastName(ln);
        p2.setInsurance(insur);
        p2.setAddress(addr);
        p2.updateDB();
        
        RequestDispatcher rd = request.getRequestDispatcher("/patientUpdate.jsp");
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