package sController;

import utilities.sqliteConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class transcript_M
{

    private Connection cn;
    private Statement st ;
    private ResultSet rs;
    private ResultSet crd_hr;
    private String crd_hr_str;
    private String name;

    private final sqliteConnection db = new sqliteConnection();   // object of connection class....


    public String cal_Grades(float obtain_marks)           // calculate grade on the bases of total obtain marks....
    {
        if(obtain_marks>=86)
        {
            return "A";
        }
        else if(obtain_marks>=82 && obtain_marks<86)
        {
            return "A-";
        }
        else if(obtain_marks>=78 && obtain_marks<82)
        {
            return "B+";
        }
        else if(obtain_marks>=74 && obtain_marks<78)
        {
            return "B";
        }

        else if(obtain_marks>=70 && obtain_marks<74)
        {
            return "B-";
        }
        else if(obtain_marks>=66 && obtain_marks<70)
        {
            return "C+";
        }
        else if(obtain_marks>=62 && obtain_marks<66)
        {
            return "C";
        }
        else if(obtain_marks>=58 && obtain_marks<62)
        {
            return "C-";
        }
        else if(obtain_marks>=54 && obtain_marks<58)
        {
            return "D+";
        }
        else if(obtain_marks>=50 && obtain_marks<54)
        {
            return "D";
        }

        else
        {
            return "F";
        }

    }
   // .....................................................................
    public float points(String grade)
    {
        if(grade.equals("A"))
        {
            return (float) 4.00;
        }

        if(grade.equals("A-"))
        {
            return (float) 3.67;
        }
        if(grade.equals("B+"))
        {
            return (float) 3.33;
        }
        if(grade.equals("B"))
        {
            return (float) 3.00;
        }
        if(grade.equals("B-"))
        {
            return (float) 2.67;
        }
        if(grade.equals("C+"))
        {
            return (float) 2.33;
        }
        if(grade.equals("C"))
        {
            return (float) 2.00;
        }
        if(grade.equals("C-"))
        {
            return (float) 1.67;
        }
        if(grade.equals("D+"))
        {
            return (float) 1.33;
        }
        if(grade.equals("D"))
        {
            return (float) 1.00;
        }
       else
        {
            return (float) 0.00;
        }
    }
    //...........................................................

    public String total_credit_hrs(String st_id, String semester_id)
    {
        cn = db.dbConnector();

        String query = "select sum(crd_hr) from trans where st_id like '%"+st_id+"%'  AND sm_id like "+semester_id+"";

        try
        {
            st = cn.createStatement();
            crd_hr = st.executeQuery(query);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        try
        {
            while(crd_hr.next())
            {
                crd_hr_str = crd_hr.getString(1);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return crd_hr_str;
    }

//    public float Sgpa(String st_id, int credit_hr, float points, int smester_ID)
//    {
//
//
//    }

    public ResultSet fetch_trans_Data(String st_id, String semester_id)
    {
        cn = db.dbConnector();
        String query = "select cs_id, crs_name, crd_hr, type from trans where st_id like '%"+st_id+"%'  AND sm_id like "+semester_id+"";
        try
        {
            st = cn.createStatement();
            rs = st.executeQuery(query);
            //System.out.println("data fetch");

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("No Transcript Data found regarding this student id");
        }

        return rs;
    }

    public ResultSet fetch_st_id(String std_id)
    {
        cn = db.dbConnector();
        String query = "select distinct st_id from trans where st_id like '%"+std_id+"%'";
        try
        {
            st = cn.createStatement();
            rs = st.executeQuery(query);
            //System.out.println("data fetch");

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return rs;

    }

    public ResultSet fetch_st_name(String std_id) {
        cn = db.dbConnector();
        String query = "select distinct name from trans where st_id like '%"+std_id+"%'";
        try
        {
            st = cn.createStatement();
            rs = st.executeQuery(query);
            //System.out.println("data fetch");

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }


        return rs;

    }

}
