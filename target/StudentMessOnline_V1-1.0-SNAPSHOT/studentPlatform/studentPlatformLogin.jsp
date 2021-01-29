<%--
  Created by IntelliJ IDEA.
  User: tomas
  Date: 11/30/2020
  Time: 8:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/studentPlatformLoginStyle.css" />
    <script src="scripts/studentPlatformValid.js"></script>

    <title>Student Platform - Login</title>
</head>
<body>
    <div id="container">
        <h2>Logowanie</h2>
        <div id="formContainer">
            <form action="" method="post">
                <input type="email" name="userMail" class="studentLoginForm" placeholder="Username"> <br> <br>
                <input type="password" name="userPassword" class="studentLoginForm" placeholder="Password"> <br> <br>
                <input type="submit" value="Log In"> <br> <br>
            </form>
        </div>
    </div>
</body>
</html>
