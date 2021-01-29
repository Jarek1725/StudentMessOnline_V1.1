package studentMessMaybeWork.studentPlatform.databaseEntities;

public class School {
    private String schoolId;
    private String schoolName;
    private String schoolShortName;
    private String schoolAddress;

    public School(String schoolId, String schoolName, String schoolShortName, String schoolAddress) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.schoolShortName = schoolShortName;
        this.schoolAddress = schoolAddress;
    }


    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolShortName() {
        return schoolShortName;
    }

    public void setSchoolShortName(String schoolShortName) {
        this.schoolShortName = schoolShortName;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolId='" + schoolId + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", schoolShortName='" + schoolShortName + '\'' +
                ", schoolAddress='" + schoolAddress + '\'' +
                '}';
    }
}
