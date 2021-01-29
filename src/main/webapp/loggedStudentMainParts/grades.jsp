<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<h1>Student's grades</h1>
<div id="grades_container">
    <div id="grades_panels">
        <c:forEach var="subject" items="${sessionScope.UserGrades}">
                <div class="grade_panel">
                    <div class="grade_panel_left">
                            ${fn:escapeXml(subject.subjectName)}
                    </div>
                    <div class="grade_panel_right">
                        <c:if test="${empty subject.gradeList}">
                            N/A
                        </c:if>
                        <c:forEach var="grade" items="${subject.gradeList}">
                            <div title="Description: ${fn:escapeXml(grade.gradeDesc)}">
                                    ${fn:escapeXml(grade.gradeValue)}, &nbsp;
                            </div>
                        </c:forEach>
                    </div>
                <br>
            </div>
        </c:forEach>
    </div>
</div>
