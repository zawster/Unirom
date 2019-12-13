package sController;

public class getSet_attd
{
    private String date;
    private String lect_no;
    private String duration_lec;
    private String presence;
    public getSet_attd(String Lec_no, String Date, String Duration, String Presence )
    {
        date = Date;
        lect_no = Lec_no;
        duration_lec = Duration;
        presence = Presence;
    }

    public String getDate() {
        return date;
    }

    public String getLect_no() {
        return lect_no;
    }

    public String getDuration_lec() {
        return duration_lec;
    }

    public String getPresence() {
        return presence;
    }

}
