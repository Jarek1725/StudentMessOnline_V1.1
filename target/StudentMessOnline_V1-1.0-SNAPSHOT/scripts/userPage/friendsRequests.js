let requestBtn = document.getElementById("send_friend_request");

xhttpForUser = new XMLHttpRequest();
// xhttpForUser.onreadystatechange = function() {
//     if (this.readyState == 4 && this.status == 200) {
//         (this.responseText)
//     }
// };


 function sendFriendRequest(){
    alert('csa')
    xhttpForUser.open("GET", "http://localhost:8080/StudentMessWebsiteV1_war_exploded/corridor/addFriend", true);
    xhttpForUser.send();
}