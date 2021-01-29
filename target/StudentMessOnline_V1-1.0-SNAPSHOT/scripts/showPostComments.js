let allShowCommentButtons = document.getElementsByClassName('post_check_all_comments');
let allComments = document.getElementsByClassName('post_check_all_comments_panel');


for(let i=0; i<allComments.length;i++){
    allComments[i].id = "post_comment_section_id_"+i;
}


for(let i=0; i<allShowCommentButtons.length;i++){
    allShowCommentButtons[i].id = "post_check_all_comments_id_"+i;
    allShowCommentButtons[i].addEventListener('click', function (){

        // let post_comment_section = 'post_comment_section_id_' + allShowCommentButtons[i].id.charAt(allShowCommentButtons[i].id.length-1)
        let post_comment_section = 'post_comment_section_id' + allShowCommentButtons[i].id.substring(allShowCommentButtons[i].id.lastIndexOf("_"))
        if(document.getElementById(post_comment_section).style.display != 'block'){
            document.getElementById(post_comment_section).style.display = 'block';
        }else{
            document.getElementById(post_comment_section).style.display = 'none';
        }

    })
}
