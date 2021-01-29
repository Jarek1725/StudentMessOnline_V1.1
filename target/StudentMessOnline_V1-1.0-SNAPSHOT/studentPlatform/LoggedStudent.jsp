<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome ${sessionScope.UserLogged.getfName()}</title>

    <!--    CSS    -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/loggedStudentStyle.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mainPart/studentStyle.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mainPart/examStyle.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mainPart/gradesStyle.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mainPart/lessonsStyle.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mainPart/homeworksStyle.css" />

    <!--   FONTS -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,200;0,300;0,400;0,500;0,600;0,800;1,400&display=swap" rel="stylesheet">

    <!--    JS scripts      -->
    <script src="${pageContext.request.contextPath}/scripts/loggedStudentMainPartAJAX.js"></script>

    <!--    FONT AWESOME    -->
    <script src="https://kit.fontawesome.com/713b4a8b2f.js" crossorigin="anonymous"></script>

    <script src="scripts/userPage/indexedDB.js"></script>

</head>
<body>

<div id="left_side">
    <div id="left_side_container">
        <div class = "links_containers" onclick="getPage('${pageContext.request.contextPath}/loggedStudentMainParts/lessons.jsp', this)">
            <a href="#">Lessons</a> <br>
        </div>
        <div class = "links_containers" onclick="getPage('${pageContext.request.contextPath}/loggedStudentMainParts/exam.jsp', this)">
            <a href="#">Exams</a> <br>
        </div>
        <div class = "links_containers" onclick="getPage('${pageContext.request.contextPath}/loggedStudentMainParts/grades.jsp', this)">
            <a href="#">Grades</a> <br>
        </div>
        <div class = "links_containers" onclick="getPage('${pageContext.request.contextPath}/loggedStudentMainParts/homeworks.jsp', this)">
            <a href="#">Homework</a> <br>
        </div>
        <div class = "links_containers" onclick="getPage('${pageContext.request.contextPath}/loggedStudentMainParts/student.jsp', this)">
            <a href="#" >Student</a> <br>
        </div>
        <div class = "links_containers go_to_another">
            <a href="${pageContext.request.contextPath}/messagesCorridor">Messages</a> <br>
        </div>
        <div class = "links_containers go_to_another">
            <a href="${pageContext.request.contextPath}/corridor">Corridor</a> <br>
        </div>

        <div id="user_info">
            <img src="${pageContext.request.contextPath}/img/userProfileImage/${sessionScope.UserLogged.profilePhoto}.jpg" alt="" onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/img/basic_avatar_man.png'">
            <div id = "userName_info">
                <p><c:out value="${sessionScope.UserLogged.getfName()}" escapeXml="true" /></p>
                <p><c:out value="${sessionScope.UserLogged.getlName()}" escapeXml="true" /></p>
            </div>
        </div>
    </div>

</div>

<div id="main">

</div>


<script src="scripts/setUserJWTId.js"></script>


</body>
</html>
