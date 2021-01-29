package studentMessMaybeWork.studentPlatform.databaseEntities;

import java.util.Date;

public class Homework implements Comparable<Homework> {
    private int homewordId;
    private int homeworkTeacherId;
    private String homeworkClassId;
    private String homeworkDescription;
    private Date homeworkDate;
    private int subjectId;
    private String subjectName;

    public Homework(int homewordId, int homeworkTeacherId, String homeworkClassId, String homeworkDescription, Date homeworkDate, int subjectId, String subjectName) {
        this.homewordId = homewordId;
        this.homeworkTeacherId = homeworkTeacherId;
        this.homeworkClassId = homeworkClassId;
        this.homeworkDescription = homeworkDescription;
        this.homeworkDate = homeworkDate;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public int getHomewordId() {
        return homewordId;
    }

    public void setHomewordId(int homewordId) {
        this.homewordId = homewordId;
    }

    public int getHomeworkTeacherId() {
        return homeworkTeacherId;
    }

    public void setHomeworkTeacherId(int homeworkTeacherId) {
        this.homeworkTeacherId = homeworkTeacherId;
    }

    public String getHomeworkClassId() {
        return homeworkClassId;
    }

    public void setHomeworkClassId(String homeworkClassId) {
        this.homeworkClassId = homeworkClassId;
    }

    public String getHomeworkDescription() {
        return homeworkDescription;
    }

    public void setHomeworkDescription(String homeworkDescription) {
        this.homeworkDescription = homeworkDescription;
    }

    public Date getHomeworkDate() {
        return homeworkDate;
    }

    public void setHomeworkDate(Date homeworkDate) {
        this.homeworkDate = homeworkDate;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public int compareTo(Homework o) {
        return o.getHomeworkDate().compareTo(getHomeworkDate());
    }
}
