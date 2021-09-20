<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "style.css" rel = "stylesheet"/>
        <title>Patient Login Page</title>
    </head>
    <body id="background">
        
        <nav>
            <a href="index.jsp">Home</a>
            <a href= "dentLogin.jsp">Dentist Login</a>
        </nav>
        
        <h1 id="loginHeader">Staats Dentistry <br/> Patient Login</h1>
        
        <form id="login" action ="PatLoginServlet" method ="post">
            
            <label for="custID">Patient Id</label><br/>
            <input type="text" id="patID" name="patId" placeholder="Enter you Patient ID here.."/>
            
            <label for="acctPw">Password</label><br/>
            <input type="text" id="patPw" name="patPw" placeholder="Enter your Password here.."/>
            
            <input type="submit" id="submit" value="Login">
            <input type="reset" id="reset" value="Clear">
            
        </form>
    </body>
</html>