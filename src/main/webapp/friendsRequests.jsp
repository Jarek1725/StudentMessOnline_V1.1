<%--
  Created by IntelliJ IDEA.
  User: tomas
  Date: 03/02/2021
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/searchContainer.css" />
    <%--    FONTS--%>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>
<div id="list_container">
    <table id="search_results">
        <tr>
            <th>Name</th>
            <th>School</th>
            <th>Class</th>
            <th>Add to friends</th>
            <th>Send a message</th>
        </tr>
    </table>
</div>

<script>
    let tableCont = document.getElementById('search_results')

    xhttpRequests = new XMLHttpRequest();
    xhttpRequests.onreadystatechange = function (){
        if (this.readyState == 4 && this.status == 200) {
            let foundResults = JSON.parse(this.responseText)
            console.log(foundResults)
            foundResults.forEach(obj=>{
                let row = "<tr>";
                row+="<td>"+obj.fName+" "+obj.lName+"</td>"
                row+="<td>"+obj.userClass.classSchool.schoolName+"</td>"
                row+="<td>"+obj.userClass.className+"</td>"
                row+="<td><a href='http://localhost:8080/StudentMessWebsiteV1_war_exploded/userPage.jsp?name="+obj.UserId+"'>Check profile</td>"
                row+="<td> <button class='send_message' onclick='send_message(this.id)' id='send_message_"+obj.UserId+"'> Send message</button></td>"
                row+="</tr>"
                tableCont.innerHTML+=row
                row=""
            })
        }
    }

    xhttpRequests.open("GET", "http://localhost:8080/StudentMessWebsiteV1_war_exploded/checkFriendsRequest", true);
    xhttpRequests.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttpRequests.send();

</script>
</body>
</html>
