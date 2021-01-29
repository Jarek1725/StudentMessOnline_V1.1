function getPage(page, elem){

    let xHttpReq = new XMLHttpRequest();

    xHttpReq.onreadystatechange = function (){

        if(this.readyState == 4 && this.status == 200){
            document.getElementById('main').innerHTML = this.responseText;
        }
    }

    xHttpReq.open('GET', page, true);

    xHttpReq.send();

    let activeClass = document.querySelectorAll(".links_containers.activeLeftSide");
    [].forEach.call(activeClass, function (el){
        el.classList.remove('activeLeftSide');
    })

    elem.classList.add('activeLeftSide');

}