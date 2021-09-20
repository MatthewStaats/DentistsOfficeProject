import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Business.Dentists;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/DentLoginServlet"})
public class DentLoginServlet extends HttpServlet {

/********************************************************************
 * DentLoginServlet: this servlet is used for Dentist login credentials confirmation
 * then forward successful login to the DentistInfo jsp
 ********************************************************************/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
            response.setContentType("text/html;charset=UTF-8");
            String dentId = "";
            String dentPw = "";
            
            try {
                dentId = request.getParameter("ID");
                dentPw = request.getParameter("password");
                
                HttpSession session;
                session = request.getSession(true);
                          
                System.out.println("dentId retrieved = " + dentId);
                Dentists d1 = new Dentists();
                d1.selectDB(dentId);
                System.out.println("First Name = " + d1.getFirstName());
                    
                if(dentPw.equals(d1.getPassword())) {
                   
                    session.setAttribute("d1", d1);
                    System.out.println("Dentist d1 object has been added");
                    RequestDispatcher rd = request.getRequestDispatcher("/DentistInfo.jsp");
                    rd.forward(request, response);
                    
                } else {
                        RequestDispatcher rd = request.getRequestDispatcher("/dentLoginError.jsp");
                        rd.forward(request, response);
                    }
            } catch (NullPointerException e) {
                System.out.println(e);
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