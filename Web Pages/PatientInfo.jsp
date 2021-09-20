<%@page import="Business.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient Info Page</title>
        <link href = "style.css" rel = "stylesheet" />
    </head>
    <body id="background">
        
        <nav>
            <a href="index.jsp">Home Page</a>
            <a href="patLogin.jsp">Login</a>
        </nav>
        
        <h1 id="searchHeader">Staats Dentistry <br/> Patient Information</h1>
        
        
        <%
          
            Patient p1;
            
            p1 = (Patient)session.getAttribute("p1");
                  
        %>
        <form id="patForm" name = "form2" action="PatientInfo.jsp">
            <label for="acctNo">Patient ID</label>
            <input type="text" id="ppid" name="patId" value="<%=p1.getPatId()%>" readonly placeholder="Patient ID"/>
            <label for="custID">Password</label>
            <input type="text" id="ppw" name="patPw" value="<%=p1.getPassword()%>" readonly placeholder="Password"/>
            <label for="acctType">First Name (editable)</label>
            <input type="text" id="pfn" name="patFn" value="<%=p1.getFirstName()%>" placeholder="First Name"/>
            <label for="acctBalance">Last Name (editable)</label>
            <input type="text" id="pln" name="patLn" value="<%=p1.getLastName()%>" placeholder="Last Name"/>
            <label for="acctBalance">Address (editable)</label>
            <input type="text" id="addr" name="patAddress" value="<%=p1.getAddress()%>" placeholder="Address"/>
            <label for="acctBalance">Email (editable)</label>
            <input type="text" id="patEmail" name="patEmail" value="<%=p1.getEmail()%>" placeholder="Email"/>
            <label for="acctBalance">Insurance (editable)</label>
            <input type="text" id="insCo" name="patInsurance" value="<%=p1.getInsurance()%>" placeholder="Insurance"/>
            <input type="submit" value="Appointment View" onclick="view()"/>
            <input type="submit" value ="Edit" onclick="edit()"/>
        </form>
        <script type="text/javascript">
            function view() {
                document.form2.action = "patAppointmentServlet";
                form2.submit();
                    
            }
                 
            function edit() {
                document.form2.action = "patUpdateServlet";
                form2.submit();
            }
            document.getElementById("submit").addEventListener("click", submit, false);    
        </script> 
    </body>
</html>