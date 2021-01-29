let main = document.getElementById("main");

let addCom = document.getElementsByClassName('post_add_comment_form_text');

for(let i=0; i<addCom.length;i++){
    addCom[i].addEventListener('keyup', function (event){
        if (event.keyCode === 13){
            addComFunction(addCom[i])
        }
    })
    // let elements = addCom[i].children;
    //
    //
    //
    // addCom[i].addEventListener('keyup', function (event){
    //     if (event.keyCode === 13){
    //         for(let x=0; x<elements.length;x++){
    //             // alert(this)
    //             addComFunction(addCom[i], elements[i])
    //
    //         }
    //     }
    // })
}


function addComFunction(obj) {

    let n = obj.value;
    let parentId = obj.parentNode.parentNode.parentNode.id.substring(0,32)
    let addCommentToThisDiv = obj.parentNode.parentNode;


    let http = new XMLHttpRequest();
    http.open("POST", "http://localhost:8080/StudentMessWebsiteV1_war_exploded/addCommentToPost", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    let params = "Comment_details="+ parentId + n;
    http.send(params);

    // let div = document.createElement("div");
    // div.classList = "comment_panel";
    // let post_check_all_comments_panel_top = document.createElement("div");
    // div.classList = "post_check_all_comments_panel_top"
    //
    // div.appendChild(post_check_all_comments_panel_top)
    // addCommentToThisDiv.appendChild(div)

    let test1 = "<div class='comment_panel'>" +
                    "<div class='post_check_all_comments_panel_top'>" +
                       "<div class=\"post_check_all_comments_panel_top_left\" style='margin-bottom: 0px'>\n" +
                            "<p>Your reply</p>\n" +
                        "</div>"+
                        "<div class=\"post_check_all_comments_panel_top_right\">\n" +
                            "<i class=\"far fa-edit\" aria-hidden=\"true\"></i>\n" +
                        "</div>"+
                    "</div>"+






"<div class=\"post_check_all_comments_panel_bottom\">"+
        n +
        "                                    </div>"















                "</div>";




    addCommentToThisDiv.innerHTML += test1;
}