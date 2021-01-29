<%@ page import="studentMessMaybeWork.studentPlatform.databaseEntities.Post" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<%
    Post post = (Post) session.getAttribute("currentPost");
    pageContext.setAttribute("post", post);
%>

<html>
<head>


    <meta http-equiv="Content-Security-Policy" content="default-src 'self' data: gap: https://*.googleapis.com/ https://*.fontawesome.com/ https://*.gstatic.com ; style-src 'self' 'unsafe-inline' https://fonts.googleapis.com/ https://*.fontawesome.com/; media-src *; img-src 'self' data: content:; script-src: 'self' ">
    <title>Corridor</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/postDetails/withPhoto.css" />

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.png"/>


    <!--   FONTS -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,200;0,300;0,400;0,500;0,600;0,700;0,700;1,400&display=swap" rel="stylesheet">

    <!--    FONT AWESOME    -->
    <script src="https://kit.fontawesome.com/713b4a8b2f.js" crossorigin="anonymous"></script>

</head>
<body>
<div id="logo_back">
    <a href="http://localhost:8080/StudentMessWebsiteV1_war_exploded/corridor">
        <img src="${pageContext.request.contextPath}/img/logo.png" alt="">
    </a>
</div>

    <div id="container">
        <div id="post_details">
            <div id="left_side">
                <img src="${pageContext.request.contextPath}/img/userUploadedImages/${post.photoPath}" alt="">
            </div>
            <div id="right_side">
                <div id="autor_details">
                    <img src="${pageContext.request.contextPath}/img/userProfileImage/${post.creatorId}.jpg" alt="" class="user_profile_photo on_error_photo">
                    <p>${post.creatorFullName} <br>
                        <span style="font-size: 11px; color:#424242">${post.createdAt}</span></p>
                </div>
                ${post.description}

                <div id="${post.postId}">
                    <input type="text" class="post_add_comment_form_text" placeholder="Add comment" id="add_comment">
                </div>

                <div id="comment_section">

                    <c:forEach var="comment" items="${post.commentList}">
                        <c:if test="${comment.parentId == 0}">
                            <div class="comment_panel">
                                <div class="post_check_all_comments_panel_top">
                                    <div class="post_check_all_comments_panel_top_left">
                                        <img src="${pageContext.request.contextPath}/img/userProfileImage/${comment.commentAutorPhotoPath}.jpg" alt="" class="user_profile_photo_comment on_error_photo">
                                            <%--                                             onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/img/basic_avatar_man.png'"--%>
                                        <p style=" margin-bottom: 9px; margin-top: 17px;"> <span style="font-size: 13px;">${fn:escapeXml(comment.commentAutorName)}</span></p>
                                    </div>
<%--                                    <div class="post_check_all_comments_panel_top_right">--%>
<%--                                        <c:if test="${comment.commentAutorId == sessionScope.UserLogged.userId}">--%>
<%--                                            <i class="far fa-edit"></i>--%>
<%--                                        </c:if>--%>
<%--                                    </div>--%>
                                </div>
                                <div class="post_check_all_comments_panel_bottom">
                                        ${fn:escapeXml(comment.commentText)}
                                    <p class="comment_reply">Reply</p>
                                    <c:forEach var="comment_replies" items="${post.commentList}">
                                        <c:if test="${comment_replies.parentId == comment.commentId}">
                                            <div class="comment_all_replies">
                                                <div class="comment_all_replies_top">
                                                    <img src="${pageContext.request.contextPath}/img/userProfileImage/${comment_replies.commentAutorPhotoPath}.jpg" alt="" class="user_profile_photo_comment_replies on_error_photo">
                                                        <%--                                                             onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/img/basic_avatar_man.png'"--%>
                                                    <p>${fn:escapeXml(comment_replies.commentAutorName)}</p>
                                                </div>
                                                <div class="comment_all_replies_bottom">
                                                        ${fn:escapeXml(comment_replies.commentText)}
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

<script src="${pageContext.request.contextPath}/scripts/addCommentToSpecifiedPost.js"></script>

</body>
</html>