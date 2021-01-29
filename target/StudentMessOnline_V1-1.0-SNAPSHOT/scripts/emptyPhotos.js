let profilePhotos = document.getElementsByClassName('on_error_photo');

for(let i=0; i<profilePhotos.length;i++){
    profilePhotos[i].addEventListener('error', function (){
        profilePhotos[i].setAttribute('src', "/StudentMessWebsiteV1_war_exploded/img/basic_avatar_man.png");
    })
}
