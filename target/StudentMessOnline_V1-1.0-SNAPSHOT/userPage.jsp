<%--
  Created by IntelliJ IDEA.
  User: tomas
  Date: 04/01/2021
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mainPart/corridorStyle.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mainPart/userProfileStyle.css" />
    <!--    FONT AWESOME    -->
    <script src="https://kit.fontawesome.com/713b4a8b2f.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<div id="user_top_description">
    <div id="user_profile_images_container">
        <div id="user_profile_photo_profile_div">
            <div id="user_profile_photo_profile" style="background-image: url('${pageContext.request.contextPath}/img/userProfileImage/${sessionScope.UserLogged.profilePhoto}.jpg') ">
            </div>
        </div>
    </div>
    <h3 id="user_name">Username</h3>
    <button id="send_friend_request">
        Add friend
    </button>
</div>



<div id="user_bottom">
    <div id="user_details_left">
        <p>Class: 4i (your)</p>
        <p>School: ZSE</p>
        <p>E-mail: tomaszewskijarek1725@gmail.com</p>
        <p>Phone number: 605949457</p>
        <p>Nationality: Poland</p>
        <p>Birth date: 2001-01-27</p>
    </div>
    <div id="user_details_main">

    </div>
</div>



<script src="scripts/userPage/getUserInformations.js"></script>
<script src="scripts/userPage/getUserPostsInformations.js"></script>
<script src="scripts/userPage/indexedDB.js"></script>
<script src="scripts/userPage/getNewPosts.js"></script>
<script src="scripts/userPage/friendsRequests.js"></script>

</body>
</html>
