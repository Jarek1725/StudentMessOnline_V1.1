<%@ page import="studentMessMaybeWork.studentPlatform.databaseQueries.GetAllPosts" %>
<%@ page import="java.util.List" %>
<%@ page import="studentMessMaybeWork.studentPlatform.databaseEntities.Post" %>
<%@ page import="studentMessMaybeWork.studentPlatform.databaseEntities.SchoolAlert" %>
<%@ page import="studentMessMaybeWork.studentPlatform.databaseQueries.GetSchoolAlerts" %>
<%@ page import="studentMessMaybeWork.studentPlatform.databaseQueries.HowManyCommInPost" %>
<%@ page import="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<%--
  Created by IntelliJ IDEA.
  User: tomas
  Date: 12/16/2020
  Time: 12:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    if(session.getAttribute("UserLogged") == null){
        response.sendRedirect("/StudentMessWebsiteV1_war_exploded/");
    }
//
//    List<Post> allPosts = GetAllPosts.getAllPosts();
    List<SchoolAlert> allSchoolAlerts = GetSchoolAlerts.getAllSchoolAlerts();
//    Map<String, Integer> howManyCommInPost = HowManyCommInPost.howManyComm();
//
//    pageContext.setAttribute("howManyComs", howManyCommInPost);
//    pageContext.setAttribute("allPosts", allPosts);
    pageContext.setAttribute("allAlerts", allSchoolAlerts);

%>

<html>
<head>

    <script src="scripts/corridor/isUserLogged.js"></script>
    <script src="scripts/userPage/indexedDB.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="scripts/notifyJS/js/notify.js"></script>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.png"/>

    <title>Corridor</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mainPart/corridorStyle.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/notifyJS/css/notify.css" />

    <!--   FONTS -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,200;0,300;0,400;0,500;0,600;0,700;0,700;1,400&display=swap" rel="stylesheet">

    <!--    FONT AWESOME    -->
    <script src="https://kit.fontawesome.com/713b4a8b2f.js" crossorigin="anonymous"></script>


</head>
<body>

<div id="wrapper">
    <div id="left_side">

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
    <div id="main">
        <div id="add_post">
            <form action="addPost" method="post" enctype="multipart/form-data" id="add_post_form">
                <div id="form_top">
                    <img src="${pageContext.request.contextPath}/img/userProfileImage/${sessionScope.UserLogged.profilePhoto}.jpg" alt="" class="user_profile_photo on_error_photo">
<%--                     onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/img/basic_avatar_man.png'"--%>
                    <input type="text" placeholder="What's up?" name="post_desrciption" id="post_desrciption">
                </div>
                <div id="form_bottom">
                    <div id="files_upload_panel">
                        <i class="fas fa-smile-beam"></i>
                        <i class="fas fa-camera" style="margin: 0 5px"></i>
                        <label for="file"><i class="far fa-images"></i>
                            <input type="file" name="photo" id="file" accept="image/jpeg,image/gif">
                        </label>
                    </div>
                    <div id="for_who_post">
                        <input type="checkbox" name="for_who" value="1" id="post_for_friends" class="for_who_class"> For friends
                        <input type="checkbox" name="for_who" value="2" id="post_for_class" class="for_who_class"> For class
                        <input type="checkbox" name="for_who" value="4" id="post_for_school" class="for_who_class"> For school
                    </div>
                    <input type="submit" id="addPostSubmit" value="Upload" >
                </div>
            </form>
        </div>
        <div id="corridor_main_posts">

        </div>
<%--        <div id="all_posts_panel">--%>





<%--            <c:forEach var="post" items="${allPosts}">--%>
<%--                <div class="post_panel" id="${post.postId }">--%>
<%--                    <div class="post_panel_top">--%>
<%--                        <a href="${pageContext.request.contextPath}/userPage.jsp?name=${post.creatorId}" class="user_profile_link">--%>
<%--                            <div class="post_panel_top_left">--%>
<%--                                <img src="${pageContext.request.contextPath}/img/userProfileImage/${post.creatorProfilePhoto}.jpg" alt="" class="user_profile_photo on_error_photo">--%>
<%--                                    &lt;%&ndash;                             onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/img/basic_avatar_man.png'"&ndash;%&gt;--%>
<%--                                <p>${fn:escapeXml(post.creatorFullName)}</p>--%>
<%--                            </div>--%>
<%--                        </a>--%>

<%--                        <c:if test="${post.creatorId == sessionScope.UserLogged.userId}">--%>
<%--                            <div class="post_panel_top_right">--%>
<%--                                <i class="far fa-edit"></i>--%>
<%--                            </div>--%>
<%--                        </c:if>--%>
<%--                    </div>--%>
<%--                    <div class="post_panel_main">--%>
<%--                        <div class="post_description"> <p>${fn:escapeXml(post.description)}</p></div>--%>
<%--                        <div class="short_post_description"></div>--%>
<%--                        <c:if test="${not empty post.photoPath }">--%>
<%--                            <img src="${pageContext.request.contextPath}/img/userUploadedImages/${post.photoPath}" alt="" class="post_photo">--%>
<%--                        </c:if>--%>
<%--                    </div>--%>
<%--                    <div class="post_bottom_panel">--%>
<%--                        <div class="post_likes add_like_to_post_class" id="${post.postId}">  <!--onclick="addLike(this)"-->--%>
<%--                            <c:choose>--%>
<%--                                <c:when test="${fn:contains(post.userrsLikedPost, sessionScope.UserLogged.userId)}">--%>
<%--                                    <i class="fas fa-heart" ></i> <span class="how_many_post_likes">${post.likesSum} </span>  people like it--%>
<%--                                </c:when>--%>
<%--                                <c:otherwise>--%>
<%--                                    <i class="far fa-heart" ></i> <span class="how_many_post_likes">${post.likesSum} </span>  people like it--%>
<%--                                </c:otherwise>--%>
<%--                            </c:choose>--%>
<%--                        </div>--%>
<%--                        <div class="post_check_all_comments">--%>
<%--                            <i class="fas fa-comments"></i> All comments--%>
<%--                            <c:forEach var="comNumber" items="${howManyComs}">--%>
<%--                                <c:if test="${comNumber.key.equals(post.postId)}">--%>
<%--                                    (${comNumber.value})--%>
<%--                                </c:if>--%>
<%--                            </c:forEach>--%>
<%--                        </div>--%>
<%--                        <div class="share_post">--%>
<%--                            <i class="fas fa-share-alt"></i> Share post--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="post_add_comment_panel">--%>
<%--                        <div class="post_add_comment_panel_display_flex">--%>
<%--                            <img src="${pageContext.request.contextPath}/img/userProfileImage/${sessionScope.UserLogged.profilePhoto}.jpg" alt="" style="width: 27px; height: 27px; margin: 19px 0 0 0;" class="user_profile_photo on_error_photo">--%>
<%--                            <input type="text" class="post_add_comment_form_text" placeholder="Add comment">--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="post_check_all_comments_panel">--%>

<%--                            <c:forEach var="comment" items="${post.commentList}">--%>
<%--                            <c:if test="${comment.parentId == 0}">--%>
<%--                                <div class="comment_panel">--%>
<%--                                    <div class="post_check_all_comments_panel_top">--%>
<%--                                        <div class="post_check_all_comments_panel_top_left">--%>
<%--                                            <img src="${pageContext.request.contextPath}/img/userProfileImage/${comment.commentAutorPhotoPath}.jpg" alt="" class="user_profile_photo_comment on_error_photo">--%>
<%--&lt;%&ndash;                                             onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/img/basic_avatar_man.png'"&ndash;%&gt;--%>
<%--                                            <p>${fn:escapeXml(comment.commentAutorName)}</p>--%>
<%--                                        </div>--%>
<%--                                        <div class="post_check_all_comments_panel_top_right">--%>
<%--                                            <c:if test="${comment.commentAutorId == sessionScope.UserLogged.userId}">--%>
<%--                                                <i class="far fa-edit"></i>--%>
<%--                                            </c:if>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                    <div class="post_check_all_comments_panel_bottom">--%>
<%--                                        ${fn:escapeXml(comment.commentText)}--%>
<%--                                        <p class="comment_reply">Reply</p>--%>
<%--                                        <c:forEach var="comment_replies" items="${post.commentList}">--%>
<%--                                                <c:if test="${comment_replies.parentId == comment.commentId}">--%>
<%--                                                    <div class="comment_all_replies">--%>
<%--                                                        <div class="comment_all_replies_top">--%>
<%--                                                            <img src="${pageContext.request.contextPath}/img/userProfileImage/${comment_replies.commentAutorPhotoPath}.jpg" alt="" class="user_profile_photo_comment_replies on_error_photo">--%>
<%--&lt;%&ndash;                                                             onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/img/basic_avatar_man.png'"&ndash;%&gt;--%>
<%--                                                            <p>${fn:escapeXml(comment_replies.commentAutorName)}</p>--%>
<%--                                                        </div>--%>
<%--                                                        <div class="comment_all_replies_bottom">--%>
<%--                                                                ${fn:escapeXml(comment_replies.commentText)}--%>
<%--                                                        </div>--%>
<%--                                                    </div>--%>
<%--                                                </c:if>--%>
<%--                                        </c:forEach>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </c:if>--%>
<%--                        </c:forEach>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </c:forEach>--%>
        </div>
   </div>
    <div id="right_side">
        <h2>School alerts</h2>
        <c:forEach var="alert" items="${allAlerts}">
            <div class="school_alert_panel">
                <h3>${fn:escapeXml(alert.alertHeader)}</h3>
<%--                <p class="school_alert_panel_description_full_text">${alert.headerDescription}</p>--%>
                <div class="school_alert_panel_description_full_text">
                    ${fn:escapeXml(alert.headerDescription)}
                </div>
                <div class="school_alert_panel_description_short_text">

                </div>
<%--                <p class="school_alert_panel_description_short_text"></p>--%>
            </div>
        </c:forEach>
    </div>
<%--</div>--%>

<div id="copiedToClipboard">
    <p>Copied to clipboard</p>
</div>

<!--    JS SCRIPTS  -->
<script src="scripts/corridor/corridorMainGetPosts.js"></script>
<script src="scripts/corridor/corridorGetNewPosts.js"></script>
<script src="scripts/corridor/blockPostsForNobody.js"></script>
<%--<script src="scripts/corridorMaxDescriptionLength.js"></script>--%>
<%--<script src="scripts/all_post_give_id.js"></script>--%>
<%--<script src="scripts/showPostComments.js"></script>--%>
<%--<script src="scripts/addLike.js"></script>--%>
<script src="scripts/emptyPhotos.js"></script>
<%--<script src="scripts/add_comment_to_post.js"></script>--%>
<%--<script src="scripts/scrollToLastPost.js"></script>--%>
<%--<script src="scripts/goToPostSite.js"></script>--%>
<%--<script src="scripts/copyToClipboardSharePost.js"></script>--%>


</body>
</html>
