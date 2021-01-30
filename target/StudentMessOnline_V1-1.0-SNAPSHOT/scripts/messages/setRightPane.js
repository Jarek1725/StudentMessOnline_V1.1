let rightPane = document.getElementById("right_pane")

xhttpForUser = new XMLHttpRequest();
xhttpForUser.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        rightPaneSetFromJSON(this.responseText)
    }
};

xhttpForUser.open("POST", "http://localhost:8080/StudentMessWebsiteV1_war_exploded/getLastConversations", true);
xhttpForUser.send();

function rightPaneSetFromJSON(obj){
    let conversations = JSON.parse(obj)

    console.log(conversations)

    conversations.forEach(conv=>{
        let conversationDiv = document.createElement("div")
        conversationDiv.classList.add('right_conversation_container')
        conversationDiv.classList.add(conv.user.UserId)
        conversationDiv.id = 'right_conversation_container_'+conv.user.UserId

        let right_conversation_container_user = document.createElement('div')

        let user_conversation_profile_img = document.createElement('img')
        user_conversation_profile_img.classList.add('conversation_user_profile_photo')
        user_conversation_profile_img.id = "userIdImg_"+conv.user.UserId
        user_conversation_profile_img.src = `http://localhost:8080/StudentMessWebsiteV1_war_exploded/img/userProfileImage/${conv.user.UserId}.jpg`

        let right_conversation_container_right = document.createElement('div')
        let user_name_div = document.createElement('div')
        user_name_div.classList.add('user_name_right_pane_class')

        let userName = document.createTextNode(`${conv.user.fName} ${conv.user.lName}`)

        let last_msg_div = document.createElement('div')
        let last_msg_text = document.createTextNode(`${conv.lastMsg.message_text.substring(0,18)}`)

        last_msg_div.appendChild(last_msg_text)

        user_name_div.appendChild(userName)

        right_conversation_container_right.appendChild(user_name_div)
        right_conversation_container_right.appendChild(last_msg_div)

        right_conversation_container_user.appendChild(user_conversation_profile_img)
        conversationDiv.appendChild(right_conversation_container_user)
        conversationDiv.appendChild(right_conversation_container_right)
        rightPane.appendChild(conversationDiv)
    })
    currentWriterColor()
    setErrorImage()
    setActiveUsers()
    setInterval( setActiveUsers
    , 60 * 1000);
}

function currentWriterColor(){
    let all_right_pane_conversation_container = document.querySelectorAll('.right_conversation_container')
    all_right_pane_conversation_container.forEach(e=>{
        e.addEventListener('click', function (){
            all_right_pane_conversation_container.forEach(e1=>{
                e1.style.backgroundColor = 'white'
            })
            e.style.backgroundColor = 'e9e9e9'
            e.style.fontWeight = 'normal'
            localStorage.setItem('write_with_user_id', e.classList.value.substring(29))
        })
    })
}

function setErrorImage(){
    document.querySelectorAll(".conversation_user_profile_photo").forEach(e=>{
        e.addEventListener('error', function (){
            e.src = 'http://localhost:8080/StudentMessWebsiteV1_war_exploded/img/basic_avatar_man.png'
        })
    })
}

xhttpActiveForUser = new XMLHttpRequest();
xhttpActiveForUser.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        let activeFriends = JSON.parse(this.responseText)
        document.querySelectorAll(".conversation_user_profile_photo").forEach(element=>{
            element.style.border = "none"
        })
        activeFriends.forEach(user=>{
            if(document.getElementById("userIdImg_"+user)!=null){
                document.getElementById("userIdImg_"+user).style.border = "3px solid green"
            }
        })

    }
};

function setActiveUsers(){
    xhttpActiveForUser.open("POST", "http://localhost:8080/StudentMessWebsiteV1_war_exploded/activeUsers", true);
    xhttpActiveForUser.send();
}