<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<h1>Next Exams</h1>
<div id="exam_container">
    <c:forEach var="exam" items="${sessionScope.UserExams}">
        <div class="exam_panel">
            <h3>${fn:escapeXml(exam.subjectName)}</h3>
            <p>Nauczyciel: ${fn:escapeXml(exam.teacherName)}</p>
            <p>Description: ${fn:escapeXml(exam.desc)}</p>
            <p><i class="far fa-clock"></i> ${fn:escapeXml(exam.date)} ${fn:escapeXml(exam.time)}</p>
        </div>
    </c:forEach>
</div>