package studentMessMaybeWork.studentPlatform.databaseEntities;

public class User {
    private String UserId;
    private String classId;
    private UserClass userClass;
    private String phoneNumber;
    private String eMail;
    private String fName;
    private String lName;
    private String birthDate;
    private String natiolanity;
    private String profilePhoto;

    public User(String userId, String classId, UserClass userClass, String phoneNumber, String eMail, String fName, String lName, String birthDate, String natiolanity, String profilePhoto) {
        UserId = userId;
        this.classId = classId;
        this.userClass = userClass;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.fName = fName;
        this.lName = lName;
        this.birthDate = birthDate;
        this.natiolanity = natiolanity;
        this.profilePhoto = profilePhoto;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getNatiolanity() {
        return natiolanity;
    }

    public void setNatiolanity(String natiolanity) {
        this.natiolanity = natiolanity;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String photo) {
        this.profilePhoto = photo;
    }

    public String getFullName(){
        return fName + " " + lName;
    }

    public UserClass getUserClass() {
        return userClass;
    }

    public void setUserClass(UserClass userClass) {
        this.userClass = userClass;
    }
}
