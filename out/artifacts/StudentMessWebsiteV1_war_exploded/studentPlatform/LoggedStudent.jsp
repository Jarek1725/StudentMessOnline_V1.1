<%--
  Created by IntelliJ IDEA.
  User: tomas
  Date: 12/3/2020
  Time: 10:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome ${sessionScope.UserLogged.getfName()}</title>

    <!--    CSS    -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/loggedStudentStyle.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mainPart/studentStyle.css" />

    <!--   FONTS -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,200;0,400;0,500;0,600;0,800;1,400&display=swap" rel="stylesheet">

    <!--    JS scripts      -->
    <script src="${pageContext.request.contextPath}/scripts/loggedStudentMainPartAJAX.js"></script>

    <!--    FONT AWESOME    -->
    <script src="https://kit.fontawesome.com/713b4a8b2f.js" crossorigin="anonymous"></script>

</head>
<body>

<div id="left_side">
    <div id="left_side_container">
        <div class = "links_containers" onclick="getPage('${pageContext.request.contextPath}/loggedStudentMainParts/student.jsp', this)">
            <a href="#" >Student</a> <br>
        </div>
        <div class = "links_containers" onclick="getPage('${pageContext.request.contextPath}/loggedStudentMainParts/student.html', this)">
            <a href="#">Lessons</a> <br>
        </div>
        <div class = "links_containers" onclick="getPage('${pageContext.request.contextPath}/loggedStudentMainParts/student.html', this)">
            <a href="#">Exams</a> <br>
        </div>
        <div class = "links_containers" onclick="getPage('${pageContext.request.contextPath}/loggedStudentMainParts/student.html', this)">
            <a href="#">Grades</a> <br>
        </div>
        <div class = "links_containers" onclick="getPage('${pageContext.request.contextPath}/loggedStudentMainParts/student.html', this)">
            <a href="#">Teachers</a> <br>
        </div>
        <div class = "links_containers" onclick="getPage('${pageContext.request.contextPath}/loggedStudentMainParts/student.html', this)">
            <a href="#">Messages</a> <br>
        </div>
        <div class = "links_containers" onclick="getPage('${pageContext.request.contextPath}/loggedStudentMainParts/student.html', this)">
            <a href="#">Corridor</a> <br>
        </div>

        <div id="user_info">
            <img src="${pageContext.request.contextPath}/img/basic_avatar_man.png" alt="">
            <div id = "userName_info">
                <p>${sessionScope.UserLogged.getfName()}</p>
                <p>${sessionScope.UserLogged.getlName()}</p>
            </div>
        </div>
    </div>

</div>

<div id="main">

</div>
</body>
</html>
