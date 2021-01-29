package studentMessMaybeWork.studentPlatform.databaseEntities;

public class LessonPlan {
    private int subjectId;
    private String classId;
    private int day;
    private int lessonNumber;
    private int teacherId;
    private String teacherName;
    private String subjectName;

    public LessonPlan(int subjectId, String classId, int day, int lessonNumber, int teacherId, String teacherName, String subjectName) {
        this.subjectId = subjectId;
        this.classId = classId;
        this.day = day;
        this.lessonNumber = lessonNumber;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.subjectName = subjectName;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
