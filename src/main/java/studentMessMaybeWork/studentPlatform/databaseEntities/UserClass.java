package studentMessMaybeWork.studentPlatform.databaseEntities;

public class UserClass {
    private String classId;
    private School classSchool;
    private String className;
    private Teacher classTeacher;


    public UserClass(String classId, School classSchool, String className, Teacher classTeacher) {
        this.classId = classId;
        this.classSchool = classSchool;
        this.className = className;
        this.classTeacher = classTeacher;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public School getClassSchool() {
        return classSchool;
    }

    public void setClassSchool(School classSchool) {
        this.classSchool = classSchool;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Teacher getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(Teacher classTeacher) {
        this.classTeacher = classTeacher;
    }

    @Override
    public String toString() {
        return "UserClass{" +
                "classId='" + classId + '\'' +
                ", classSchool=" + classSchool +
                ", className='" + className + '\'' +
                ", classTeacher=" + classTeacher +
                '}';
    }
}
