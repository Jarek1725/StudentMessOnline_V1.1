<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="studentContainer">
    <div id="studentTop">
        <div id="imageContainer">
            <img src="${pageContext.request.contextPath}/img/mojeImg.jpg" class="image">
            <div class="overlay">
                <a href="#" class="icon" title="User Profile">
                    <i class="fas fa-cogs"></i>
                </a>
            </div>
        </div>
        <div id="studentPersonalData">
            <p>${sessionScope.UserLogged.getfName()} ${sessionScope.UserLogged.getlName()}</p>
            <p><span style="font-size: 18px">4i</span></p>
        </div>
    </div>
    <div id="studentBotton">
        <p>Date of birth: </p> <br>
        <p>Gender:</p> <br>
        <p>Phone: ${sessionScope.UserLogged.getPhoneNumber()} </p> <br>
        <p>Email: ${sessionScope.UserLogged.geteMail()} asd </p>
    </div>
</div>