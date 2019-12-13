package fController;
import utilities.sqliteConnection;
import java.sql.*;

public class modelFaculty {
    String std_id, FName, LName, course_id;
    private static sqliteConnection db = new sqliteConnection();

    public modelFaculty(String std_id, String fName, String lName, String course_id){
        this.std_id = std_id;
        this.FName = fName;
        this.LName = lName;
        this.course_id = course_id;
    }

    public String getStd_id() {
        return std_id;
    }

    public String getFName(){ return  FName;};

    public String getLName(){ return LName; }
    public String getCourse_id() {
        return course_id;
    }
    public  static ResultSet getCources(String fName) throws SQLException {
        Connection cn = db.dbConnector();
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("Select code from courses where aTeacher LIKE '" + fName + "';");
        return rs;
    }
}
