package studentMessMaybeWork.studentPlatform.databaseEntities;

public class SchoolAlert {
    private int alertId;
    private String alertHeader;
    private String headerDescription;
    private int teacherAdded;

    public SchoolAlert(int alertId, String alertHeader, String headerDescription, int teacherAdded) {
        this.alertId = alertId;
        this.alertHeader = alertHeader;
        this.headerDescription = headerDescription;
        this.teacherAdded = teacherAdded;
    }

    public int getAlertId() {
        return alertId;
    }

    public void setAlertId(int alertId) {
        this.alertId = alertId;
    }

    public String getAlertHeader() {
        return alertHeader;
    }

    public void setAlertHeader(String alertHeader) {
        this.alertHeader = alertHeader;
    }

    public String getHeaderDescription() {
        return headerDescription;
    }

    public void setHeaderDescription(String headerDescription) {
        this.headerDescription = headerDescription;
    }

    public int getTeacherAdded() {
        return teacherAdded;
    }

    public void setTeacherAdded(int teacherAdded) {
        this.teacherAdded = teacherAdded;
    }
}
