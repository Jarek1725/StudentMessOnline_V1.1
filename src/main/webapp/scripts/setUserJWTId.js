xhttp1 = new XMLHttpRequest();
xhttp1.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        sessionStorage.setItem("UserJWTId", this.responseText)
    }
};


xhttp1.open("GET", "http://localhost:8080/StudentMessWebsiteV1_war_exploded/getCurrentUserId", true);
xhttp1.send();