package studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageEntities;

public class MessageEntity {
    private String conversation_id;
    private String sender_id;
    private String message_text;
    private String attachment;
    private String created_at;

    public MessageEntity(String conversation_id, String sender_id, String message_text, String attachment, String created_at) {
        this.conversation_id = conversation_id;
        this.sender_id = sender_id;
        this.message_text = message_text;
        this.attachment = attachment;
        this.created_at = created_at;
    }

    public String getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(String conversation_id) {
        this.conversation_id = conversation_id;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
