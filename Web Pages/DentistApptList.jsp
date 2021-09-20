<%@page import="Business.AppointmentList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Business.Appointment"%>
<%@page import="Business.Dentists"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
       <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet"/>
        <title>Appointment List</title>
        
        <Style>
           table {
            margin-left: 600px;
            margin-right: 0px;
            border-left: 1px solid black;
            border-right: 1px solid black;
            border-top: 1px solid black;
            text-align: center;
        }
        
        td {
            border-left: 1px solid black;
            border-bottom: 1px solid black;
        }
        
        th {
            border-bottom: 1px solid black;
        }
        
        </Style>
    </head>
    <body id="background">
        <nav>
            <a href="index.jsp">Home</a>
            <a href="DentistInfo.jsp">Your Information</a>
        </nav>
                
        
        <%
            Dentists d2;           
            d2 = (Dentists)session.getAttribute("d1");
        %>
        
        <h1 id="searchHeader">Staats Dentistry <br/> <br/></h1>
        <h2>Dentist <%= d2.getFirstName()%> <%= d2.getLastName()%> Appointment Schedule Display</h2>
        <br/>
        <br/>
        
        <%    
           Appointment appt;
           
           
           for (int x = 0; x < d2.aList.aList.size(); x++) {
               appt = d2.aList.aList.get(x);
           
        %>  
            <table>
                <tr>
                    <th>Appointment Date</th>
                    <td><%=appt.getDate()%></td>
                </tr>
                <tr>
                    <th>Patient ID</th>
                    <td><%=appt.getPatId()%></td>
                </tr>
                <tr>
                    <th>Dentist ID</th>
                    <td><%=appt.getDentId()%></td>
                </tr>
                <tr>
                    <th>Procedure Code</th>
                    <td><%=appt.getCode()%></td>
                </tr>
            </table>
               
            <br/>
            <br/>
        <% 
            }
        %>
           
    </body>
</html>
