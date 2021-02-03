let searchPane = document.getElementById("search_input")

searchPane.addEventListener("keyup", function (event) {
    if (event.keyCode === 13) {
        let msg = event.target.value
        sessionStorage.setItem("search_value", msg)
        window.location.href = 'http://localhost:8080/StudentMessWebsiteV1_war_exploded/searchPage.jsp'
    }
});