<%@page import="Business.Dentists"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dentist Info Page</title>
        <link href = "style.css" rel = "stylesheet"/>
    </head>
   <body id="background">
        
        <nav>
            <a href="index.jsp">Home Page</a>
            <a href="patLogin.jsp">Patient Login</a>
        </nav>
        
        <h1 id="searchHeader">Staats Dentistry<br/> Dentist Information</h1>
        
        
        <%
          
            Dentists d1;
            
            d1 = (Dentists)session.getAttribute("d1");
                  
        %>
        
        <form id="searchForm" name = "form1" action="DentistInfo.jsp">
            <label for="acctNo">Dentist ID</label>
            <input type="text" id="dentId" name="dentId" value="<%=d1.getId()%>" readonly/>
            <label for="custID">Dentist Password</label>
            <input type="text" id="dpw" name="dentPw" value="<%=d1.getPassword()%>" readonly/>
            <label for="acctType">First Name (editable)</label>
            <input type="text" id="dfn" name="dentFn" value="<%=d1.getFirstName()%>" placeholder="Enter First Name"/>
            <label for="acctBalance">Last Name (editable)</label>
            <input type="text" id="dln" name="dentLn" value="<%=d1.getLastName()%>" placeholder="Enter Last Name"/>
            <label for="acctBalance">Email (editable)</label>
            <input type="text" id="dEmail" name="dentEmail" value="<%=d1.getEmail()%>" placeholder="Enter Email"/>
            <label for="acctBalance">Office</label>
            <input type="text" id="office" name="Office" value="<%=d1.getOffice()%>" readonly placeholder="Enter Office Number"/>
            <input type="submit" value="View" onclick="view()"/>
            <input type="submit" value ="Edit" onclick="edit()"/>
        </form>
            <script type="text/javascript">
                function view() {
                    document.form1.action = "dentAppointmentServlet";
                    form1.submit();
                    
                 }
                 
                 function edit() {
                     document.form1.action = "dentUpdateServlet";
                     form1.submit();
                 }
                
                document.getElementById("submit").addEventListener("click", submit, false);
        </script> 
    </body>
</html>