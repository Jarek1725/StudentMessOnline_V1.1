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