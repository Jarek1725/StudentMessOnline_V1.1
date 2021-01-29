package studentMessMaybeWork.studentPlatform.databaseEntities;

public class Grade {
    private int gradeId;
    private int teacherId;
    private int studentId;
    private int subjectId;
    private int gradeValue;
    private String gradeDesc;

    public Grade(int gradeId, int teacherId, int studentId, int subjectId, int gradeValue, String gradeDesc) {
        this.gradeId = gradeId;
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.gradeValue = gradeValue;
        this.gradeDesc = gradeDesc;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(int gradeValue) {
        this.gradeValue = gradeValue;
    }

    public String getGradeDesc() {
        return gradeDesc;
    }

    public void setGradeDesc(String gradeDesc) {
        this.gradeDesc = gradeDesc;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
