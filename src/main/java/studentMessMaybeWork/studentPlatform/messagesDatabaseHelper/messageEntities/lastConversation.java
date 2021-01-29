package studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageEntities;

import studentMessMaybeWork.studentPlatform.databaseEntities.userToReturn;

public class lastConversation {
    private String conversationId;
    private userToReturn user;
    private MessageEntity lastMsg;

    public lastConversation(String conversationId, userToReturn user, MessageEntity lastMsg) {
        this.conversationId = conversationId;
        this.user = user;
        this.lastMsg = lastMsg;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public userToReturn getUser() {
        return user;
    }

    public void setUser(userToReturn user) {
        this.user = user;
    }

    public MessageEntity getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(MessageEntity lastMsg) {
        this.lastMsg = lastMsg;
    }
}
