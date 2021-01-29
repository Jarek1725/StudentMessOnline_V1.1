xhttpForUser = new XMLHttpRequest();
xhttpForUser.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        setPosts(this.responseText)
    }
};

xhttpForUser.open("GET", "http://localhost:8080/StudentMessWebsiteV1_war_exploded/corridor/posts", true);
xhttpForUser.send();

let mainDiv = document.getElementById('corridor_main_posts')
let postNmb = 0;

function setPosts(posts){

    postNmb+=5

    let postObj = JSON.parse(posts)

    postObj.forEach(obj=>{
        let toHtml = "<div class='post_panel' id='"+obj.postId+"'>"+
            "<div class = 'post_panel_top' >"+
            "<a href='http://localhost:8080/StudentMessWebsiteV1_war_exploded/userPage.jsp?name="+obj.creatorId+"' class='user_profile_link'>" +
            "<div class='post_panel_top_left'>"+
            "<img src='http://localhost:8080/StudentMessWebsiteV1_war_exploded/img/userProfileImage/"+obj.creatorId+".jpg' alt='' class='user_profile_photo on_error_photo'>"+
            "<p>"+escapeHtml(obj.creatorFullName) + "<span style='color: #928e93'>"
                if(obj.sumForWho == 1){
                    toHtml+=" - for friends"
                }
                if(obj.sumForWho == 2){
                    toHtml+=" - for class"
                }
                if(obj.sumForWho == 3){

                    toHtml+=" - for friends and class"
                }
                if(obj.sumForWho == 4){
                    toHtml+=" - for school"
                }
                if(obj.sumForWho == 5){
                    toHtml+=" - for for friends and school"
                }
                if(obj.sumForWho == 6){
                    toHtml+=" - for for class and school"
                }
                if(obj.sumForWho == 7){
                    toHtml+=" - for everyone"
                }

        toHtml+="</span></p>" +
            "</div>"+
            "</a>"

        if(JSON.parse(sessionStorage.getItem('user')).UserId == obj.creatorId){
            toHtml +=   "<div className='post_panel_top_right'>"+
                "<i class='far fa-edit  edit_post' aria-hidden='true'></i>"+
                    "<ul class='edit_post_list'><li class='edit_post_description "+obj.postId+"'>Edit description</li><li class='edit_post_delete "+obj.postId+"'>Delete post</li></ul>"+
                "</div>"
        }
        toHtml+="</div>"+
            "<div class='post_panel_main'>"+
            "<div class='post_description "+obj.postId+"'> <p>"+escapeHtml(obj.description)+"</p></div>"+
            "<div class='short_post_description "+obj.postId+"'></div>"
        if(obj.photoPath!==undefined){
            toHtml+="<img src='http://localhost:8080/StudentMessWebsiteV1_war_exploded/img/userUploadedImages/"+obj.photoPath+"' alt='' class='post_photo'>"
        }
        toHtml+="</div>"+
            "<div class='post_bottom_panel'>"+
            "<div class='post_likes add_like_to_post_class' id='"+obj.postId+"'>"
        if(obj.userrsLikedPost.includes(JSON.parse(sessionStorage.getItem('user')).UserId.toString())){
            toHtml+="<i class='fas fa-heart' ></i> <span class='how_many_post_likes'>"+obj.likesSum+" </span>  people like it"
        }else{
            toHtml+="<i class='far fa-heart' ></i> <span class='how_many_post_likes'>"+obj.likesSum+" </span>  people like it"
        }
        toHtml+="</div>"+
            "<div class='post_check_all_comments'>"+
            "<i class='fas fa-comments'></i> All comments"
        toHtml+="3 (pop)"+
            "</div>"+
            "<div class='share_post'>"+
            "<i class='fas fa-share-alt'></i> Share post"+
            "</div>"+
            "</div>"+
            "<div class='post_add_comment_panel'>"+
            "<div class='post_add_comment_panel_display_flex'>"+
            "<img src='http://localhost:8080/StudentMessWebsiteV1_war_exploded/img/userProfileImage/"+JSON.parse(sessionStorage.getItem('user')).profilePhoto+".jpg' alt='' style='width: 27px; height: 27px; margin: 19px 0 0 0;' class='user_profile_photo on_error_photo'>"+
            "<input type='text' class='post_add_comment_form_text' placeholder='Add comment'>"+
            "</div>"+
            "</div>"+
            "<div class='post_check_all_comments_panel'>"
        obj.commentList.forEach(com=>{
            if(com.parentId == 0){
                toHtml+= "<div class='comment_panel'>"+
                    "<div class='post_check_all_comments_panel_top'>"+
                    "<div class='post_check_all_comments_panel_top_left'>"+
                    "<img src='http://localhost:8080/StudentMessWebsiteV1_war_exploded/img/userProfileImage/"+com.commentAutorPhotoPath+".jpg' alt='' class='user_profile_photo_comment on_error_photo'>"+
                    "<p>"+escapeHtml(com.commentAutorName)+"</p>"+
                    "</div>"+
                    "<div class='post_check_all_comments_panel_top_right'>"
                if(JSON.parse(sessionStorage.getItem('user')).UserId == com.commentAutorId){
                    toHtml+="<i class='far fa-edit'></i>"
                }
                toHtml+="</div>"+
                    "</div>"+
                    "<div class='post_check_all_comments_panel_bottom'>"+
                    escapeHtml(com.commentText)
                    // "<p class='comment_reply "+com.commentId+"'>Reply</p>"
                obj.commentList.forEach(comReply=>{
                    if(comReply.parentId === com.commentId){
                        toHtml+= "<div class='comment_all_replies'>"+
                            "<div class='comment_all_replies_top'>"+
                            "<img src='http://localhost:8080/StudentMessWebsiteV1_war_exploded/img/userProfileImage/"+comReply.commentAutorPhotoPath+".jpg' alt='' class='user_profile_photo_comment_replies on_error_photo'>"+
                            "<p>"+escapeHtml(comReply.commentAutorName)+"</p>"+
                            "</div>"+
                            "<div class='comment_all_replies_bottom'>"+
                            escapeHtml(comReply.commentText)+
                            "</div>"+
                            "</div>"
                    }
                })
                toHtml+="</div>"+
                    "</div>"



            }
        })

        mainDiv.innerHTML+=toHtml;
        toHtml="";
    })

//ADD LIKE TO POST

    let postToLike = document.getElementsByClassName('add_like_to_post_class');

    for(let i=0; i<postToLike.length;i++){
        postToLike[i].addEventListener('click', function (){
            addLike(postToLike[i])
        })
    }

    function addLike(obj){

        let n = obj.id;
        let http = new XMLHttpRequest();
        http.open("POST", "http://localhost:8080/StudentMessWebsiteV1_war_exploded/addPostLike", true);
        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        let params = "post_id=" + n;
        http.send(params);

        const icon = obj.querySelector('i');
        let likes = obj.getElementsByClassName('how_many_post_likes')[0];
        // let likes = parseInt(obj.getElementsByClassName('how_many_post_likes')[0].innerHTML);
        if(icon.classList == 'far fa-heart') {
            icon.setAttribute("class", "fas fa-heart");
            likes.innerHTML = parseInt(likes.innerHTML)+1;
        } else{
            icon.setAttribute("class", 'far fa-heart');
            likes.innerHTML = parseInt(likes.innerHTML)-1;
        }
    }


//SHOW COMMENTS
    let allShowCommentButtons = document.getElementsByClassName('post_check_all_comments');
    let allComments = document.getElementsByClassName('post_check_all_comments_panel');


    for(let i=0; i<allComments.length;i++){
        allComments[i].id = "post_comment_section_id_"+i;
    }


    for(let i=0; i<allShowCommentButtons.length;i++){
        allShowCommentButtons[i].id = "post_check_all_comments_id_"+i;
        allShowCommentButtons[i].addEventListener('click', function (){

            let post_comment_section = 'post_comment_section_id' + allShowCommentButtons[i].id.substring(allShowCommentButtons[i].id.lastIndexOf("_"))
            if(document.getElementById(post_comment_section).style.display != 'block'){
                document.getElementById(post_comment_section).style.display = 'block';
            }else{
                document.getElementById(post_comment_section).style.display = 'none';
            }

        })
    }


//ADD COMMENT TO POST
    let main = document.getElementById("main");

    let addCom = document.getElementsByClassName('post_add_comment_form_text');

    for(let i=0; i<addCom.length;i++){
        addCom[i].addEventListener('keyup', function (event){
            if (event.keyCode === 13){
                addComFunction(addCom[i])
            }
        })
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
            escapeHtml(n) +
            "                                    </div>"+
            "</div>";
        addCommentToThisDiv.innerHTML += test1;
    }


//ALL POST GIVE ID
    let allPosts = document.getElementsByClassName('post_panel');

    for(let i =0; i<allPosts.length; i++){{
        if(i>=(postNmb-5)){
            allPosts[i].id += " post_id_"+i;
        }
    }
    }


//SET COM NUMBER UNDER POST
    let post_panel_class = document.getElementsByClassName('post_panel')
    for(let i=0; i<post_panel_class.length; i++){
        let post_panel_id = post_panel_class[i].id.substring(41)
        let comment_section_document = document.getElementById('post_check_all_comments_id_'+post_panel_id)
        let post_comment_section = document.getElementById('post_comment_section_id_'+post_panel_id).childElementCount
        comment_section_document.innerHTML="<i class=\"fas fa-comments\" aria-hidden=\"true\"></i>"+
            " All comments ("+post_comment_section+")"
    }


//COPY TO CLIPBOARD SHARE POST
    let copiedLink = document.getElementById('copiedToClipboard')
    let shareButtons = document.querySelectorAll('.share_post').forEach(function (item){
        item.addEventListener('click', function (){
            let postId = item.parentNode.parentNode.id.substring(0,32)
            let postLink = "http://localhost:8080/StudentMessWebsiteV1_war_exploded/post.jsp?name="+postId
            navigator.clipboard.writeText(postLink)

            $.notify("Copied to clipboard", { class:"copiedToClipboardNotify",align:"center", type:"info", animationType:"fade", verticalAlign:"bottom"});


        })
    })


//MAX LENGTH DESCRIPTION
    let allDescriptions = document.getElementsByClassName('school_alert_panel_description_full_text');
    let allShortDescriptions = document.getElementsByClassName('school_alert_panel_description_short_text');

    for(let i = 0; i<allDescriptions.length; i++){
        if(allDescriptions[i].innerHTML.length>300){
            allDescriptions[i].id = "long_description_"+i;
            allShortDescriptions[i].id = "short_description_"+i;
            allShortDescriptions[i].innerHTML = allDescriptions[i].innerHTML.slice(0,300)+' <span class="read_more_button"> ...(more) </span>';
            allDescriptions[i].style.display = 'none';
        }
    }

    let allReadMoreButtons = document.getElementsByClassName('read_more_button');

    for(let i = 0; i<allReadMoreButtons.length;i++){
        allReadMoreButtons[i].addEventListener('click', function (){

            let shortDescId = allReadMoreButtons[i].parentElement.id.charAt(allReadMoreButtons[i].parentElement.id.length-1);
            let longDestId = "long_description_"+shortDescId;

            allReadMoreButtons[i].parentElement.style.display = 'none';
            document.getElementById(longDestId).style.display = 'block';

        })
    }


    let tooLongDescription = document.querySelectorAll('.post_description').forEach(function (item){
        if(item.innerHTML.length>330){
            let fullText = item.innerHTML;
            let itemParent = item.parentNode;
            let shortDescription = itemParent.children[1];

            let buttonShowMore = document.createElement('button');
            buttonShowMore.innerHTML = "Read more";
            buttonShowMore.style.border = 'none';
            buttonShowMore.style.outline = 'none';
            buttonShowMore.style.fontSize = '14px';
            buttonShowMore.style.padding = '5px 0 0 0';
            buttonShowMore.style.cursor = 'pointer';
            buttonShowMore.classList.add("gradient-text")


            buttonShowMore.addEventListener('click', function (){
                item.style.display = 'block'
                shortDescription.style.display = 'none'
            })

            shortDescription.innerHTML = fullText.substring(0,330)+'...';
            shortDescription.appendChild(buttonShowMore);

            item.style.display = 'none';
        }
    })


//EMPTY PHOTOS
    let profilePhotos = document.getElementsByClassName('on_error_photo');

    for(let i=0; i<profilePhotos.length;i++){
        profilePhotos[i].addEventListener('error', function (){
            profilePhotos[i].setAttribute('src', "/StudentMessWebsiteV1_war_exploded/img/basic_avatar_man.png");
        })
    }


//GO TO POST SITE // EDIT POST
    let allPhotos = document.getElementsByClassName('post_photo');
    let editPost = document.getElementsByClassName('edit_post')

    for(let i=0; i<allPhotos.length;i++){
        allPhotos[i].addEventListener('click', function (){
            let postId = allPhotos[i].parentNode.parentNode.id.substring(0,32);
            sessionStorage.setItem('lastScrollPosition', postId)
            location.href = "http://localhost:8080/StudentMessWebsiteV1_war_exploded/post.jsp?name="+postId;
        })
    }

    for(let i=0; i<editPost.length;i++){
        editPost[i].addEventListener('click', function (e){
            let edit_post_list = document.getElementsByClassName("edit_post_list")
            let postId = edit_post_list[i].parentNode.parentNode.parentNode.id.substring(0,32)
            if(edit_post_list[i].style.display === '' || edit_post_list[i].style.display === 'none'){
                edit_post_list[i].style.display = 'block'
                edit_post_list[i].querySelectorAll('ul li').forEach(function (e1){

                    e1.addEventListener('click', function (){
                        if(e1.classList.contains("edit_post_description")){
                            let post_description_class = document.getElementsByClassName("post_description "+postId)[0]
                            let post_description = post_description_class.textContent.substring(1)
                            document.getElementsByClassName("short_post_description "+postId)[0].style.display = 'none'
                            post_description_class.style.display = 'block'
                            post_description_class.innerHTML="<form action='editPost' method='post'> <textarea name='updatedText' class='edit_post_text_area'>"+post_description+"</textarea> " +
                                "<input type='text' style='display: none' value='"+postId+"' name='postId'>"+
                                "<input class='edit_post_submit' value='Update'  type='submit'> " +
                                "</form>"
                        } else if (e1.classList.contains("edit_post_delete")){
                            location.href = "deletePost?postId="+postId
                        }
                        e1.parentNode.style.display = 'none'

                        document.querySelectorAll(".edit_post_text_area").forEach(function (element){
                            element.addEventListener('input', function (){
                                element.style.height = "6px";
                                element.style.height = (element.scrollHeight)+"px";
                            })
                        })
                    })
                })

                return
            }
            if(edit_post_list[i].style.display === 'block'){
                edit_post_list[i].style.display = 'none'
            }

            // location.href = "http://localhost:8080/StudentMessWebsiteV1_war_exploded/post.jsp?name="+postId;
        })
    }



//SCROLL TO LAST POST
    window.onload = function (){

        let lastPosition = sessionStorage.getItem('lastScrollPosition');
        let lastReadedPost = document.getElementById(lastPosition)
        let parent = lastReadedPost.parentNode.parentNode

        parent.scrollIntoView()
        sessionStorage.removeItem('lastScrollPosition');
    }

}function escapeHtml(unsafe) {
    return unsafe
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;")
        .replace(/'/g, "&#039;");
}

//EDIT POST
