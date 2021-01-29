let copiedLink = document.getElementById('copiedToClipboard')
let shareButtons = document.querySelectorAll('.share_post').forEach(function (item){
    item.addEventListener('click', function (){
        let postId = item.parentNode.parentNode.id.substring(0,32)
        let postLink = "http://localhost:8080/StudentMessWebsiteV1_war_exploded/post.jsp?name="+postId
        // postLink.select()
        // postLink.setSelectionRange(0, 99999)
        // document.execCommand("copy")
        navigator.clipboard.writeText(postLink)

        copiedLink.style.opacity = '1'
        setTimeout(function (){
            copiedLink.style.opacity = '0';
        }, 2000)
    })
})
