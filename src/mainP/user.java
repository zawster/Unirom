package mainP;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utilities.sqliteConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class user {
    Integer id;
    String user,pass,fname,lname,address,typee,gender;
    String dob;

    public user(){
        user=" ";
        pass=" ";
        fname=" ";
        lname=" ";
        address=" ";

    }
    public user(Integer id,String Fname,String Lname,String typee,String gender,String address, String dobb){
        this.id=id;
        this.fname=Fname;
        this.lname=Lname;
        this.address=address;
        this.gender=gender;
        this.dob=dobb;
        this.typee=typee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getTypee() {
        return typee;
    }

    public void setTypee(String typee) {
        this.typee = typee;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @FXML
    void fd_login(MouseEvent event) throws SQLException, IOException {

        Connection connection= sqliteConnection.dbConnector();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from users where username" +
                " = '" + user + "'");

        if (resultSet.next()) {
            Parent root = FXMLLoader.load(getClass().getResource("../sView/homeStudent.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));

        }


    }

}
