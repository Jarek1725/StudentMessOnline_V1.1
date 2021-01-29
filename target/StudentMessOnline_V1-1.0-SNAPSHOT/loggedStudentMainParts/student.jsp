<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<h1>Student Details</h1>
<div id="studentContainer">
    <div id="studentTop">
        <div id="imageContainer">
            <img src="${pageContext.request.contextPath}/img/userProfileImage/${sessionScope.UserLogged.profilePhoto}.jpg" class="image" />
            <div class="overlay">
                <a href="#" class="icon" title="User Profile">
                    <i class="fas fa-cogs"></i>
                </a>
            </div>
        </div>
        <div id="studentPersonalData">
            <p>${fn:escapeXml(sessionScope.UserLogged.getfName())} ${fn:escapeXml(sessionScope.UserLogged.getlName())}</p>
            <p><span style="font-size: 18px">4i</span></p>
        </div>
    </div>
    <div id="studentBotton">
        <p>Date of birth:
            <c:if test="${empty sessionScope.UserLogged.getBirthDate()}">
                empty
            </c:if>
            <c:if test="${not empty sessionScope.UserLogged.getBirthDate()}">
                ${fn:escapeXml(sessionScope.UserLogged.getBirthDate())}
            </c:if>
        </p> <br>
        <p>Gender:
            <c:if test="${empty sessionScope.UserLogged.getNatiolanity()}">
                empty
            </c:if>
            <c:if test="${fn:escapeXml(not empty sessionScope.UserLogged.getNatiolanity())}">
                ${sessionScope.UserLogged.getNatiolanity()}
            </c:if>
        </p> <br>
        <p>Phone: ${fn:escapeXml(sessionScope.UserLogged.getPhoneNumber())} </p> <br>
        <p>Email: ${fn:escapeXml(sessionScope.UserLogged.geteMail())}</p>
    </div>
</div>