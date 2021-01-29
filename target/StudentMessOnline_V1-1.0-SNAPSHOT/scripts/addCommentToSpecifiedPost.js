let getComForm = document.getElementById('add_comment')
let parentId = getComForm.parentNode.id
let commentSection = document.getElementById('comment_section')

getComForm.addEventListener('keyup', function (event){
    if (event.keyCode === 13){
        let commentText = getComForm.value;

        let http = new XMLHttpRequest();
        http.open("POST", "http://localhost:8080/StudentMessWebsiteV1_war_exploded/addCommentToPost", true);
        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        let params = "Comment_details="+ parentId + commentText;
        http.send(params);



        let test1 = "<div class='comment_panel'>" +
            "<div class='post_check_all_comments_panel_top'>" +
            "<div class=\"post_check_all_comments_panel_top_left\">\n" +
            "<p style=\" margin-bottom: 0; margin-top: 17px;\"> <span style=\"font-size: 13px;\">Your reply</span></p>"+

            "</div>"+
            "</div>"+
            "<div class=\"post_check_all_comments_panel_bottom\">"+
            commentText +
            "                                    </div>"+

        "</div>";

        commentSection.innerHTML = test1 + commentSection.innerHTML
        getComForm.value = ""

    }
})