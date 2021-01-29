<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Lesson plan</h1>
<div id="lesson_container">

<div class="days">
    <div class="lesson_panel hours">
        <span class="lesson_panel_table_top"> <b><p>Godzina:</p></b> </span>
    </div>
    <div class="lesson_panel hours">
        <p>8:00- 8:45</p>
    </div>
    <div class="lesson_panel hours">
        <p>8:50- 9:35</p>
    </div>
    <div class="lesson_panel hours">
        <p>9:40-10:25</p>
    </div>
    <div class="lesson_panel hours">
        <p>10:45-11:30</p>
    </div>
    <div class="lesson_panel hours">
        <p>11:35-12:20</p>
    </div>
    <div class="lesson_panel hours">
        <p>12:25-13:10</p>
    </div>
    <div class="lesson_panel hours">
        <p>13:15-14:00</p>
    </div>
    <div class="lesson_panel hours">
        <p>14:05-14:50</p>
    </div>
    <div class="lesson_panel hours">
        <p>14:55-15:40</p>
    </div>
    <div class="lesson_panel hours">
        <p>15:45-16:30</p>
    </div>
    <div class="lesson_panel hours">
        <p>16:45-17:30</p>
    </div>
    <div class="lesson_panel hours">
        <p>17:35-18:20</p>
    </div>
    <div class="lesson_panel hours">
        <p>18:25-19:10</p>
    </div>
</div>

        <c:forEach varStatus="loopIndex" begin="1" end="5">
            <div class="days" id="day_${loopIndex.index}">
                <div class="lesson_panel">
                    <c:if test="${loopIndex.index == 1}">
                        <span class="lesson_panel_table_top"><b><p>Monday</p></b></span>
                    </c:if>
                    <c:if test="${loopIndex.index == 2}">
                            <span class="lesson_panel_table_top"><b><p>Tuesday</p></b></span>
                    </c:if>
                    <c:if test="${loopIndex.index == 3}">
                            <span class="lesson_panel_table_top"><b><p>Wednesday</p></b></span>
                    </c:if>
                    <c:if test="${loopIndex.index == 4}">
                            <span class="lesson_panel_table_top"><b><p>Thursday</p></b></span>
                    </c:if>
                    <c:if test="${loopIndex.index == 5}">
                            <span class="lesson_panel_table_top"><b><p>Friday</p></b></span>
                    </c:if>
                </div>
                <c:forEach varStatus="lesson_Number" begin="1" end="13">
                    <div class="lesson_panel">
                        <c:forEach var="lesson" items="${sessionScope.UserLessonPlan}">
                            <c:if test="${lesson.lessonNumber == lesson_Number.index && lesson.day == loopIndex.index}">
                                <p> <span class="lesson_panel_subject_name"> ${lesson.subjectName} </span> <br>
                                <span class="lesson_panel_teacher_name"> ${lesson.teacherName}</span></p>
                            </c:if>
                        </c:forEach>
                        </div>
                </c:forEach>
            </div>
        </c:forEach>



</div>
<div id="lesson_empty_bottom">
</div>