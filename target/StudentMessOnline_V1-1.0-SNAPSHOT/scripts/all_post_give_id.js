let allPosts = document.getElementsByClassName('post_panel');

for(let i =0; i<allPosts.length; i++){
    allPosts[i].id += " post_id_"+i;
}