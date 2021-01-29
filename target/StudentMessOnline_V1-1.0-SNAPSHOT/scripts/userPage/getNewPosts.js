    let start = 5;
    let limit = 5;
    let getItem = true
    window.onscroll = function(ev) {
        if ((window.innerHeight + window.scrollY) >= document.body.scrollHeight-1) {
            if(getItem){
                // you're at the bottom of the page
                getPostData()
                getItem = false
            }
        }

    function getPostData(){
        $.ajax({
            url : 'http://localhost:8080/StudentMessWebsiteV1_war_exploded/corridor/user/posts?name='+namePosts,
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
                    setUserPosts(response)
                }
            }
        });
}}

