package studentMessMaybeWork.studentPlatform.databaseEntities;


public class Exam {
    private int teacherId;
    private String classId;
    private int subjectId;
    private String desc;
    private String date;
    private String time;
    private String subjectName;
    private String teacherName;

    public Exam(int teacherId, String classId, int subjectId, String desc, String date, String time) {
        this.teacherId = teacherId;
        this.classId = classId;
        this.subjectId = subjectId;
        this.desc = desc;
        this.date = date;
        this.time = time;
    }

    public Exam(int teacherId, String classId, int subjectId, String desc, String date, String time, String subjectName, String teacherName) {
        this.teacherId = teacherId;
        this.classId = classId;
        this.subjectId = subjectId;
        this.desc = desc;
        this.date = date;
        this.time = time;
        this.subjectName = subjectName;
        this.teacherName = teacherName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
