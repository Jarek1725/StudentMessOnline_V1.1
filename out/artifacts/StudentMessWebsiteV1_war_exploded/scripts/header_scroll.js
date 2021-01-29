//SMOOTH NAV HIDE AT FIRST TIME
window.onload = function (){
    window.scrollTo({top:1});
    window.scrollTo({top:0});
};

let prevScrollPosition = 0;
window.addEventListener("scroll", function (){
    let currentScrollPosition = window.pageYOffset;

    if(prevScrollPosition < currentScrollPosition){
        document.querySelector("nav").style.top = "-100px";
    } else{
        document.querySelector("nav").style.top = "0";
    }
    prevScrollPosition = currentScrollPosition;
});