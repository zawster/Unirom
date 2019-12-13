package sController;

import mainP.Student;
import utilities.sqliteConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Model_attd
{
    private ResultSet total_lec;                    // these will be in the form of string
    private ResultSet total_pres;
    private ResultSet rs;
    private int total_present;
    private int total_lecture;
    private int percentage;
    private String str_percentage;
    private String percent_sign;


    private Connection cn;
    public String query;
    private Statement st ;

   // private final Connection_db db = new Connection_db();
    private final sqliteConnection db = new sqliteConnection();   // object of connection class....

    ///////////////////////////////////////////////////////////////

    public ResultSet registeredCourse()
    {

        cn = db.dbConnector();
       // cn = db.connect();
        String query = "select code from registration as reg, courses as c where sID LIKE '"+ Student.getSid() +"' AND  reg.cID = c.id";
        try {
            st = cn.createStatement();
            rs = st.executeQuery(query);
            //System.out.println("Execute");

        } catch (SQLException e)
        {
            e.printStackTrace();
            //System.out.println("Execute not");
        }
        return rs;
    }


    public String att_Percentage(String std_id, String course_id )
    {
        cn = db.dbConnector();
        //cn = db.connect();
        String query1 =  "select count(lecture_no) from attendence where st_id = '"+ Student.getSid() +"' AND cs_id LIKE '"+course_id+"'";

        String query2 = "select count(presence) from attendence where st_id = '"+ Student.getSid() +"' AND cs_id LIKE '"+course_id+"'  AND presence = 'P'";
        try
        {
            st = cn.createStatement();
            total_lec = st.executeQuery(query1);        // total lecture from Query

            st = cn.createStatement();
            total_pres = st.executeQuery(query2);     // total present from Query 2
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        try
        {
            while(total_lec.next() && total_pres.next())
            {
                String lec= total_lec.getString(1);
                String pres = total_pres.getString(1);
//                System.out.println("lec value " + lec);
//                System.out.println("present value " + pres);

                total_lecture = Integer.valueOf(lec);             // conversion of string to int......
               // System.out.println("Total_Lectures " + total_lecture);

                total_present = Integer.valueOf(pres);
            //    System.out.println("Total_Presents " + total_present);

            }
            if(total_lecture==0)
            {
                str_percentage = Integer.toString(percentage);
                percentage = 00;

            }
            else
            {
                percentage = total_present*100/total_lecture;            // Total percentage....
            }

            //System.out.println("Percentage: " + percentage);

            str_percentage = Integer.toString(percentage);       // again convert int to string...
            //System.out.println("Percentage: " + str_percentage);
            percent_sign = str_percentage + " %";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return percent_sign;


    }

    public ResultSet fetch_attd_Data(String std_id, String cs_id)
    {
        cn = db.dbConnector();
        //cn = db.connect();
        String query_attd = "select lecture_no, date, duration, presence from attendence where st_id LIKE '"+Student.getSid()+"' AND cs_id LIKE '"+cs_id+"'";
        try
        {
            st = cn.createStatement();
            rs = st.executeQuery(query_attd);        // Attendance data ....

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return rs;
    }

}
