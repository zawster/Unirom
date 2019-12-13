package sController;

public class getSet_trans
{
    private String code;
    private String courseName;
    private String crdHrs;
    private String grades;
    private float points;
    private String type;

    public getSet_trans(String cs_id, String name, String crd_hr, String type) {
        this.code = cs_id;
        this.courseName = name;
        this.crdHrs = crd_hr;
        this.type = type;
    }

    public String getCode()
    {
        return code;
    }


    public String getCourseName() {
        return courseName;
    }


    public String getCrdHrs() {
        return crdHrs;
    }

    public String getGrades() {
        return grades;
    }

    public float getPoints() {
        return points;
    }

    public String getType() {
        return type;
    }
}
