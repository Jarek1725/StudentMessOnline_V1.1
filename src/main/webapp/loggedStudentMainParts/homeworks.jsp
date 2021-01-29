<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="headerDateFormat" value="'EEE, yyyy-MM-dddd'"/>
<h1>Homeworks</h1>
<div id="homeworks_container">
    <c:forEach var="homeworkDates" items="${sessionScope.UserHomeworksDates}">
        <div class="homework_day">
            <h3>${homeworkDates.key}</h3>
            <c:forEach var="homework" items="${sessionScope.UserHomeworks}">
                <c:if test="${homework.homeworkDate == homeworkDates.key}">
                    <p>Subject: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ${homework.subjectName}</p>
                    <p class="homework_day_border_none" style="padding-bottom: 20px">Description: &nbsp; &nbsp; &nbsp; &nbsp; ${fn:escapeXml(homework.homeworkDescription)}</p>
                </c:if>
            </c:forEach>
        </div>
    </c:forEach>
</div>


