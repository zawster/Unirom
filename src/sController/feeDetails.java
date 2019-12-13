package sController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mainP.Course;
import mainP.Student;
import utilities.sqliteConnection;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.jar.JarOutputStream;

public class feeDetails implements Initializable {

    @FXML
    private TableView<Course> tableView;
    @FXML
    private TableColumn<Course,Integer> idColumn;
    @FXML
    private TableColumn<Course,String> CNameColumn;
    @FXML
    private TableColumn<Course,Integer> CHrsColumn;
    @FXML
    private Label totalfees;

    public Integer totalcredit;
    public Integer feetotal;
    public Integer percredit;


    @FXML
    void logout(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/mainP/login.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    @FXML
    void dash(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sView/homeStudent.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    @FXML
    void attendance(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sView/Attendance.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    @FXML
    void courseReg(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sView/courseRegistration.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    @FXML
    void studyPlan(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sView/semester1.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    @FXML
    void feeDe(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sView/feeDetails.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    @FXML
    void homei(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sView/homeview.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Course,Integer>("id"));
        CNameColumn.setCellValueFactory(new PropertyValueFactory<Course,String>("cName"));
        CHrsColumn.setCellValueFactory(new PropertyValueFactory<Course,Integer>("cHrs"));

        tableView.setItems(getCourses());
        try {
            getTotal();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null,feetotal);
        String ab= Integer.toString(feetotal);
        String ac=" Rs: "+ab;
        totalfees.setText(ac);

    }
    public ObservableList<Course> getCourses(){
        totalcredit=0;
        feetotal=0;
        ObservableList<Course> Course= FXCollections.observableArrayList();
        try {
            Connection connection = sqliteConnection.dbConnector();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM registration INNER JOIN courses ON courses.id = registration.cID where sID="+Student.id+";");
            while (resultSet.next()) {
                Course.add(new Course(resultSet.getInt("id"), resultSet.getString("cName"), resultSet.getString("code"), resultSet.getInt("cHrs"), resultSet.getString("aTeacher"), resultSet.getString("preReq"), resultSet.getString("type"), resultSet.getInt("semester")));
                totalcredit+=resultSet.getInt("cHrs");
            }


        } catch (SQLException e) {
            System.err.println("Cannot Connect to Database");
        }



        return Course;
    }
    public void getTotal() throws SQLException {


        Connection connection = sqliteConnection.dbConnector();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select MAX(amount) from fee");
        resultSet.next();
        percredit=resultSet.getInt(1);
        feetotal=totalcredit*percredit;


        }
}
