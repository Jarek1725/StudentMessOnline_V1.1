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