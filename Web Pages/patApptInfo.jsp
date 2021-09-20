<%@page import="Business.Patient"%>
<%@page import="Business.Appointment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "style.css" rel = "stylesheet"/>
        <title>Patient Appointment Page Information Page</title>
    </head>
    <body>
         <nav>
            <a href="index.jsp">Home</a>
            <a href="PatientInfo.jsp">Your Information</a>
        </nav>
         <%
            Patient p2;           
            p2 = (Patient)session.getAttribute("p1");
            
            Appointment a2;
            a2 = (Appointment)session.getAttribute("a2");
        %>
        
        <h1 id="searchHeader">Staats Dentistry <br/> <br/></h1>
        <h2>Patient <%= p2.getFirstName()%> <%= p2.getLastName()%> Appointment Schedule Display</h2>
        <br/>
        <br/>
        <form id="patAppoint" name = "form3" action="patApptInfo.jsp">
            <label for="acctNo">Appointment Date</label>
            <input type="text" id="apptd" name="apptDate" value="<%=a2.getDate()%>" placeholder="No Appointment Date Scheduled"/>
            <label for="custID">Your Patient ID</label>
            <input type="text" id="pid" name="patId" value="<%=p2.getPatId()%>" readonly placeholder="Patient Id"/>
            <label for="acctType">Dentist ID</label>
            <input type="text" id="patFn" name="dentId" value="<%=a2.getDentId()%>" readonly placeholder="Dentist Id"/>
            <label for="acctBalance">Procedure Code</label>
            <input type="text" id="procCode" name="procCode" value="<%=a2.getCode()%>" readonly placeholder="Procedure Code"/>
            <input type="submit" value="Change Date" onclick="edit()"/>
            <input type="submit" value ="Back" onclick="view()"/>
        </form>
        <script type="text/javascript">
            function view() {
                document.form3.action = "PatientInfo.jsp";
                form3.submit();
                    
            }
                 
            function edit() {
                document.form3.action = "updateApptServlet";
                form3.submit();
            }
            document.getElementById("submit").addEventListener("click", submit, false);    
        </script>
    </body>
</html>