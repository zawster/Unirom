package sController;

public class Student_marks_GetSet {

    private String no;
    private String obtn;
    private String total;

    public Student_marks_GetSet(String no, String obtn, String total)
    {
        this.no = no;
        this.obtn = obtn;
        this.total = total;
    }
    public String getNo()
    {
        return no;
    }
    public String getObtn()
    {
        return obtn;
    }
    public String getTotal()
    {
        return total;
    }


}
