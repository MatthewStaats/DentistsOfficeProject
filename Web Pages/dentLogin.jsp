<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dentist Login Page</title>
        <link href = "style.css" rel = "stylesheet"/>
        
    </head>
       <body id="background">
        
        <nav>
            <a href="index.jsp">Home</a>
            <a href= "patLogin.jsp">Patient Login</a>
        </nav>
        
        <h1 id="loginHeader">Staats Dentistry <br/> Dentist Login</h1>
        
        <form id="login" action ="DentLoginServlet" method ="post">
            <label for="custID">Dentist Id</label><br/>
            <input type="text" id="DentID" name="ID" placeholder="Enter you Dentist ID here.."/>
            <label for="acctPw">Password</label><br/>
            <input type="text" id="dentPw" name="password" placeholder="Enter your Password here.."/>
            <input type="submit" id="submit" value="Login">
            <input type="reset" id="reset" value="Clear">
        </form>
    </body>
</html>
