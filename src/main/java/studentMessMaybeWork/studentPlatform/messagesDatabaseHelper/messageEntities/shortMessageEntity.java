package studentMessMaybeWork.studentPlatform.messagesDatabaseHelper.messageEntities;

public class shortMessageEntity {
    private String messageText;
    private String senderId;
    private String toWho;

    public shortMessageEntity(String messageText, String senderId, String toWho) {
        this.messageText = messageText;
        this.senderId = senderId;
        this.toWho = toWho;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getToWho() {
        return toWho;
    }

    public void setToWho(String toWho) {
        this.toWho = toWho;
    }
}
