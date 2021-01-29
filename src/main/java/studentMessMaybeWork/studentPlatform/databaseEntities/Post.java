package studentMessMaybeWork.studentPlatform.databaseEntities;

import java.util.List;

public class Post {
    private String postId;
    private int creatorId;
    private String description;
    private String photoPath;
    private String sumForWho;
    private String createdAt;
    private String deletedAt;
    private String creatorFullName;
    private String creatorProfilePhoto;
    private int likesSum;
    private List<String> userrsLikedPost;
    private List<Comment> commentList;

    public Post(String postId, int creatorId, String description, String photoPath, String sumForWho, String createdAt, String deletedAt, String creatorFullName, String creatorProfilePhoto, int likesSum, List<String> userrsLikedPost, List<Comment> commentList) {
        this.postId = postId;
        this.creatorId = creatorId;
        this.description = description;
        this.photoPath = photoPath;
        this.sumForWho = sumForWho;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.creatorFullName = creatorFullName;
        this.creatorProfilePhoto = creatorProfilePhoto;
        this.likesSum = likesSum;
        this.userrsLikedPost = userrsLikedPost;
        this.commentList = commentList;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getCreatorFullName() {
        return creatorFullName;
    }

    public void setCreatorFullName(String creatorFullName) {
        this.creatorFullName = creatorFullName;
    }

    public String getCreatorProfilePhoto() {
        return creatorProfilePhoto;
    }

    public void setCreatorProfilePhoto(String creatorProfilePhoto) {
        this.creatorProfilePhoto = creatorProfilePhoto;
    }

    public int getLikesSum() {
        return likesSum;
    }

    public void setLikesSum(int likesSum) {
        this.likesSum = likesSum;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<String> getUserrsLikedPost() {
        return userrsLikedPost;
    }

    public void setUserrsLikedPost(List<String> userrsLikedPost) {
        this.userrsLikedPost = userrsLikedPost;
    }

    public String getSumForWho() {
        return sumForWho;
    }

    public void setSumForWho(String sumForWho) {
        this.sumForWho = sumForWho;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", creatorId=" + creatorId +
                ", description='" + description + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", createdAt=" + createdAt +
                ", deletedAt=" + deletedAt +
                ", creatorFullName='" + creatorFullName + '\'' +
                '}';
    }
}
