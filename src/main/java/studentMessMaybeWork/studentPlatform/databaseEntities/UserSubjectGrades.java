package studentMessMaybeWork.studentPlatform.databaseEntities;

import java.util.List;

public class UserSubjectGrades {
    private int subjestId;
    private String subjectName;
    private List<Grade> gradeList;

    public UserSubjectGrades(int subjestId, String subjectName, List<Grade> gradeList) {
        this.subjestId = subjestId;
        this.subjectName = subjectName;
        this.gradeList = gradeList;
    }

    public int getSubjestId() {
        return subjestId;
    }

    public void setSubjestId(int subjestId) {
        this.subjestId = subjestId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }
}
