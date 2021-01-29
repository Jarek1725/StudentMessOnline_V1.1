window.onload = function (){

    let lastPosition = sessionStorage.getItem('lastScrollPosition');
    let lastReadedPost = document.getElementById(lastPosition)
    let parent = lastReadedPost.parentNode.parentNode

    parent.scrollIntoView()
    sessionStorage.removeItem('lastScrollPosition');
} 