let allPhotos = document.getElementsByClassName('post_photo');

for(let i=0; i<allPhotos.length;i++){
    allPhotos[i].addEventListener('click', function (){
        let postId = allPhotos[i].parentNode.parentNode.id.substring(0,32);
        sessionStorage.setItem('lastScrollPosition', postId)
        location.href = "http://localhost:8080/StudentMessWebsiteV1_war_exploded/post.jsp?name="+postId;
    })
}