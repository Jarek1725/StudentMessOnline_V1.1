<%--
  Created by IntelliJ IDEA.
  User: tomas
  Date: 26/01/2021
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Messages</title>

<%--    STYLES    --%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/messages/messagesCorridorStyle.css" />
<%--    FONTS--%>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700&display=swap" rel="stylesheet">

</head>
<body>
    <div id="container">
        <div id="left_pane"></div>
        <div id="main_pane"></div>
        <div id="right_pane"></div>
    </div>


<script src="scripts/messages/setRightPane.js"></script>


<script>
    let userId = sessionStorage.getItem("UserJWTId")
    let ws = new WebSocket(`ws://localhost:8080/StudentMessWebsiteV1_war_exploded/ws/`+userId);
    console.log(ws)
    ws.onmessage = function (event) {
        console.log(JSON.parse(event.data).senderId);
        if(document.getElementById(JSON.parse(event.data).senderId)!=null){
            document.getElementById(JSON.parse(event.data).senderId).style.fontWeight = '800'
        }
    }
</script>

</body>
</html>
