let queryStringPosts = window.location.search
let urlParamsPosts = new URLSearchParams(queryStringPosts)
let namePosts = urlParamsPosts.get("name")
let requestBtn = document.getElementById("send_friend_request")


let mainDiv = document.getElementById("user_details_main")

xhttpForUser = new XMLHttpRequest()
xhttpForUser.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        setUserInfo(this.responseText)
    }
}

xhttpForUser.open("GET", "http://localhost:8080/StudentMessWebsiteV1_war_exploded/corridor/user?name="+namePosts, true)
xhttpForUser.send()

function setUserInfo(user){

    xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            setUserPosts(this.responseText)
        }
    }

    xhttp.open("GET", "http://localhost:8080/StudentMessWebsiteV1_war_exploded/corridor/user/posts?name="+namePosts, true)
    xhttp.send()

    let userJsonObj = JSON.parse(user)
    console.log(userJsonObj)

    if(userJsonObj.isFriend === 0){
        requestBtn.innerHTML = '<i class="fas fa-question"></i> Accept request'
        requestBtn.title = 'Accept friend request'
    } else if (userJsonObj.isFriend === 1){
        requestBtn.innerHTML = '<i class="fas fa-user-check"></i> Friends'
        requestBtn.title = 'Delete friend'
    } else if (userJsonObj.isFriend === 2){
        requestBtn.innerHTML = '<i class="fas fa-user-plus"></i> Send request'
        requestBtn.title = 'Send request'
    } else if(userJsonObj.isFriend === 3){
        requestBtn.style.display = 'none';
    } else if(userJsonObj.isFriend === 4){
        requestBtn.innerHTML = '<i class="fas fa-share"></i> Request sent'
        requestBtn.title = 'Undo request'
    }

    let user_profile_photo_profile = document.getElementById('user_profile_photo_profile')
    let user_name = document.getElementById('user_name')

    user_profile_photo_profile.style.backgroundImage = "url('http://localhost:8080/StudentMessWebsiteV1_war_exploded/img/userProfileImage/"+userJsonObj.profilePhoto+".jpg')"
    user_name.textContent = userJsonObj.fName + " " + userJsonObj.lName

    document.title = userJsonObj.fName + " " + userJsonObj.lName +" - Profile"
}

requestBtn.addEventListener('click', function (){
        xhttpForUser.open("GET", "http://localhost:8080/StudentMessWebsiteV1_war_exploded/corridor/addFriend", true)
        xhttpForUser.send()

        window.location.reload()
})


let postNmb = 0;

function setUserPosts (posts){
    getData()
    postNmb+=5

    let postObjPosts = JSON.parse(posts)


    postObjPosts.forEach(obj => {
        let toHtml = "<div class='post_panel' id='"+obj.postId+"'>"+
                                "<div class = 'post_panel_top' >"+
                                     "<a href='http://localhost:8080/StudentMessWebsiteV1_war_exploded/userPage.jsp?name="+obj.creatorId+"' class='user_profile_link'>" +
                                        "<div class='post_panel_top_left'>"+
                                            "<img src='http://localhost:8080/StudentMessWebsiteV1_war_exploded/img/userProfileImage/"+obj.creatorId+".jpg' alt='' class='user_profile_photo on_error_photo'>"+
                                            "<p>"+escapeHtml(obj.creatorFullName)+"</p>" +
                                        "</div>"+
                                     "</a>"

                                if(JSON.parse(sessionStorage.getItem('user')).UserId == obj.creatorId){
                                    toHtml +=   "<div className='post_panel_top_right'>"+
                                                    "<i className='far fa-edit'></i>"+
                                                "</div>"
                                }
                                toHtml+="</div>"+
                                        "<div class='post_panel_main'>"+
                                            "<div class='post_description'> <p>"+escapeHtml(obj.description)+"</p></div>"+
                                            "<div class='short_post_description'></div>"
                                        if(obj.photoPath!=undefined){
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
                                                                    escapeHtml(com.commentText)+
                                                                    "<p class='comment_reply'>Reply</p>"
                                                                    obj.commentList.forEach(comReply=>{
                                                                        if(comReply.parentId == com.commentId){
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


    });





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
            "<div class=\"post_check_all_comments_panel_top_left\" style='margin-bottom: 0s'>\n" +
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



//COPY TO CLIPBOARD SHARE POST
    let copiedLink = document.getElementById('copiedToClipboard')
    let shareButtons = document.querySelectorAll('.share_post').forEach(function (item){
        item.addEventListener('click', function (){
            let postId = item.parentNode.parentNode.id.substring(0,32)
            let postLink = "http://localhost:8080/StudentMessWebsiteV1_war_exploded/post.jsp?name="+postId
            navigator.clipboard.writeText(postLink)

            copiedLink.style.opacity = '1'
            setTimeout(function (){
                copiedLink.style.opacity = '0';
            }, 2000)
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


//GO TO POST SITE
    let allPhotos = document.getElementsByClassName('post_photo');

    for(let i=0; i<allPhotos.length;i++){
        allPhotos[i].addEventListener('click', function (){
            let postId = allPhotos[i].parentNode.parentNode.id.substring(0,32);
            sessionStorage.setItem('lastScrollPosition', postId)
            location.href = "http://localhost:8080/StudentMessWebsiteV1_war_exploded/post.jsp?name="+postId;
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

//SET COM NUMBER UNDER POST
    let post_panel_class = document.getElementsByClassName('post_panel')
    for(let i=0; i<post_panel_class.length; i++){
        let post_panel_id = post_panel_class[i].id.substring(41)
        let comment_section_document = document.getElementById('post_check_all_comments_id_'+post_panel_id)
        let post_comment_section = document.getElementById('post_comment_section_id_'+post_panel_id).childElementCount
        comment_section_document.innerHTML="<i class=\"fas fa-comments\" aria-hidden=\"true\"></i>"+
            " All comments ("+post_comment_section+")"
    }

}


function escapeHtml(unsafe) {
    return unsafe
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;")
        .replace(/'/g, "&#039;");
}