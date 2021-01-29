let main = document.getElementById('main');

let start = 5;
let limit = 5;
let getItem = true

main.onscroll = function(ev) {

    if ((main.offsetHeight + main.scrollTop) >= main.scrollHeight-1) {
        if(getItem){
            getPostData()
            getItem = false
        }
    }

    function getPostData(){
        $.ajax({
            url : 'http://localhost:8080/StudentMessWebsiteV1_war_exploded/corridor/posts',
            method: 'POST',
            dataType: 'text',
            cache:false,
            data : {getData:1,start:start,limit:limit},
            success:function(response){
                if(response.length==2) {

                }else{
                    start += limit;
                    getItem = true
                    console.log(response)
                    setPosts(response)
                }
            }
        });
    }}

