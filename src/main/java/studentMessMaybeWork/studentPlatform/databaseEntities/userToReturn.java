package studentMessMaybeWork.studentPlatform.databaseEntities;

public class userToReturn extends User{

    int isFriend;
    int isSameClass;

    public userToReturn(String userId, String classId, UserClass userClass, String phoneNumber, String eMail, String fName, String lName, String birthDate, String natiolanity, String profilePhoto, int isFriend, int isSameClass) {
        super(userId, classId, userClass, phoneNumber, eMail, fName, lName, birthDate, natiolanity, profilePhoto);
        this.isFriend = isFriend;
        this.isSameClass = isSameClass;
    }

    public int getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(int isFriend) {
        this.isFriend = isFriend;
    }

    public int getIsSameClass() {
        return isSameClass;
    }

    public void setIsSameClass(int isSameClass) {
        this.isSameClass = isSameClass;
    }
}
