package studentMessMaybeWork.studentPlatform.databaseEntities;

public class Comment {
    private int commentId;
    private String commentText;
    private int parentId;
    private int commentAutorId;
    private String commentCreatedAt;
    private String commentDeleted_at;
    private String commentAutorName;
    private String commentAutorPhotoPath;

    public Comment(int commentId, String commentText, int parentId, int commentAutorId, String commentCreatedAt, String commentDeleted_at, String commentAutorName, String commentAutorPhotoPath) {
        this.commentId = commentId;
        this.commentText = commentText;
        this.parentId = parentId;
        this.commentAutorId = commentAutorId;
        this.commentCreatedAt = commentCreatedAt;
        this.commentDeleted_at = commentDeleted_at;
        this.commentAutorName = commentAutorName;
        this.commentAutorPhotoPath = commentAutorPhotoPath;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getCommentAutorId() {
        return commentAutorId;
    }

    public void setCommentAutorId(int commentAutorId) {
        this.commentAutorId = commentAutorId;
    }

    public String getCommentCreatedAt() {
        return commentCreatedAt;
    }

    public void setCommentCreatedAt(String commentCreatedAt) {
        this.commentCreatedAt = commentCreatedAt;
    }

    public String getCommentDeleted_at() {
        return commentDeleted_at;
    }

    public void setCommentDeleted_at(String commentDeleted_at) {
        this.commentDeleted_at = commentDeleted_at;
    }

    public String getCommentAutorName() {
        return commentAutorName;
    }

    public void setCommentAutorName(String commentAutorName) {
        this.commentAutorName = commentAutorName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getCommentAutorPhotoPath() {
        return commentAutorPhotoPath;
    }

    public void setCommentAutorPhotoPath(String commentAutorPhotoPath) {
        this.commentAutorPhotoPath = commentAutorPhotoPath;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
}
