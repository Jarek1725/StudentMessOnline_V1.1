<%@ page import="studentMessMaybeWork.studentPlatform.databaseEntities.Post" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>


<%
    Post post = (Post) session.getAttribute("currentPost");
    pageContext.setAttribute("post", post);
%>

${post.description}