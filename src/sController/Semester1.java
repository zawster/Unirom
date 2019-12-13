package sController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mainP.Course;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import utilities.sqliteConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ResourceBundle;

public class Semester1 implements Initializable {


    @FXML
    private TableView<Course> tableView;

    @FXML
    private TableColumn<Course,String> namecolumn;

    @FXML
    private TableColumn<Course, Integer> crdcolumn;

    @FXML
    private TableColumn<Course, String> typecolumn;

    @FXML
    private TableColumn<Course,String> codecolumn;

    @FXML
    private ChoiceBox semesterid;

    ObservableList<Integer> semesteridlist=FXCollections.observableArrayList(1,2,3,4,5,6,7,8);
    @FXML
    public void loadData() {


        namecolumn.setCellValueFactory(new PropertyValueFactory<Course,String>("cName"));
        codecolumn.setCellValueFactory(new PropertyValueFactory<Course,String>("CCode"));
        crdcolumn.setCellValueFactory(new PropertyValueFactory<Course,Integer>("cHrs"));
        typecolumn.setCellValueFactory(new PropertyValueFactory<Course,String>("type"));


        tableView.setItems(getCourses());
    }

    public ObservableList<Course> getCourses(){
        ObservableList<Course> Course= FXCollections.observableArrayList();
        try {
            Connection connection = sqliteConnection.dbConnector();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from courses where semester = "+semesterid.getValue()+";");
            while (resultSet.next()) {
                Course.add(new Course(resultSet.getInt("id"), resultSet.getString("cName"), resultSet.getString("code"), resultSet.getInt("cHrs"), resultSet.getString("aTeacher"), resultSet.getString("preReq"), resultSet.getString("type"), resultSet.getInt("semester")));
            }


        } catch (SQLException e) {
            System.err.println("Cannot Connect to Database");
        }

        return Course;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        semesterid.setValue(1);
        semesterid.setItems(semesteridlist);
    }

    //Event Handlers
    @FXML
    void feeDe(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sView/feeDetails.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
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
    void semester1(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sView/semester1.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

}
