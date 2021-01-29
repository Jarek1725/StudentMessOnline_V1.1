let post_for_friends = document.getElementById("post_for_friends")
let post_for_class = document.getElementById("post_for_class")
let post_for_school = document.getElementById("post_for_school")
let add_post_form = document.getElementById("add_post_form")
let post_desrciption = document.getElementById("post_desrciption")

if(sessionStorage.getItem("postAdded")){
    sessionStorage.removeItem("postAdded")
    $.notify("Post added successfully", { align:"center", type:"success"});
}

add_post_form.addEventListener("submit", function (e){
    if(!post_for_friends.checked && !post_for_class.checked && !post_for_school.checked || post_desrciption.value.length<1){
        e.preventDefault()
        $.notify("Cannot add post for nobody or without description", { align:"center", type:"default"});
    } else{
        sessionStorage.setItem("postAdded", "true")
    }
})