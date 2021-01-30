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
        <div id="left_pane">
            <div id="search_panel">
                <form>
                    <input type="text" placeholder="Search">
                </form>
            </div>
            <div class="left_button">
                <a href="http://localhost:8080/StudentMessWebsiteV1_war_exploded/userPage.jsp?name=${sessionScope.UserLogged.userId}">Profile</a>
            </div>
            <div class="left_button">
                <a href="http://localhost:8080/StudentMessWebsiteV1_war_exploded/studentLoggedIn" class="gradient-text">Back To School</a>
            </div>
            <div class="left_button">
                <a href="#">Class Friends</a>
            </div>
            <div class="left_button">
                <a href="#">Class Forum</a>
            </div>
            <div class="left_button">
                <a href="#">School Friends</a>
            </div>
            <div class="left_button">
                <a href="#">School Forum</a>
            </div>
            <div class="left_button">
                <a href="#">Your groups</a>
            </div>
            <div class="left_button">
                <a href="#">Create group</a>
            </div>
        </div>
        <div id="main_pane">
            <div id="old_message_container">

            </div>
            <input title="Chat Input" id="input" class="send_message_input" type="text" style="display: block;" placeholder="Type a message..." autofocus/>
        </div>
        <div id="right_pane"></div>
    </div>


<script src="scripts/messages/setRightPane.js"></script>


<script>
    let userId = sessionStorage.getItem("UserJWTId")
    let ws = new WebSocket(`ws://localhost:8080/StudentMessWebsiteV1_war_exploded/ws/`+userId);
    // console.log(ws)
    ws.onmessage = function (event) {
        let commMess = JSON.parse(event.data)
        console.log("right_conversation_container_"+commMess.senderId)
        if(document.getElementById("right_conversation_container_"+commMess.senderId)!=null){
            document.getElementById("right_conversation_container_"+commMess.senderId).style.fontWeight = 'bold'
        }
    }

    document.getElementById("input").addEventListener("keyup", function (event) {
        if (event.keyCode === 13) {
            let msg = event.target.value
            let text = '{"messageText":"'+msg+'", "senderId":"'+sessionStorage.getItem("UserJWTId")+'", "toWho":"'+localStorage.getItem("write_with_user_id")+'"}'
            ws.send(text)
        }
    });
</script>

</body>
</html>
