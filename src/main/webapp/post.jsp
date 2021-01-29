<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="studentMessMaybeWork.studentPlatform.databaseEntities.Post" %>
<%@ page import="com.google.gson.Gson" %><%--
  Created by IntelliJ IDEA.
  User: tomas
  Date: 03/01/2021
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<%
    String getPostId = request.getParameter("name");
    pageContext.setAttribute("postId", getPostId);
%>

<c:import var="dataJson" url="http://localhost:8080/StudentMessWebsiteV1_war_exploded/corridor/post?name=${postId}"/>

<%

    String json = (String)pageContext.getAttribute("dataJson");
    Gson gson = new Gson();
    Post post = gson.fromJson(json, Post.class);
    pageContext.setAttribute("post", post);
    session.setAttribute("currentPost", post);

%>


<c:choose>
    <c:when test="${empty post}">
        No available post
    </c:when>
    <c:when test="${empty post.photoPath}">
        <jsp:include page="postDetails/noPhoto.jsp">
            <jsp:param name="postJson" value="${dataJson}"/>
        </jsp:include>
    </c:when>
    <c:otherwise>
        <jsp:include page="postDetails/withPhoto.jsp">
            <jsp:param name="postJson" value="${post}"/>
        </jsp:include>
    </c:otherwise>
</c:choose>
