package aController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utilities.sqliteConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class users implements Initializable {

    @FXML
    private TextField fd_fname;
    @FXML
    private TextField fd_lname;
    @FXML
    private TextField fd_user;
    @FXML
    private PasswordField fd_pass;
    @FXML
    private TextField fd_fname1;
    @FXML
    private DatePicker fd_date;
    @FXML
    private ChoiceBox atype;

    ObservableList<String> typelist= FXCollections.observableArrayList("STUDENT","FACULTY","ADMIN");

    @FXML
    void fd_register(MouseEvent event) throws SQLException, IOException {
        String username, password, fname, lname;
        DatePicker dob= new DatePicker();
        password = fd_pass.getText();
        username = fd_user.getText();
        fname = fd_fname.getText();
        lname = fd_lname.getText();
        String dobe=dob.getValue().toString();
    if(!username.isEmpty() ||!password.isEmpty()||!fname.isEmpty()||!lname.isEmpty()) {
    Connection connection = sqliteConnection.dbConnector();
    Statement statement = connection.createStatement();

    int status = statement.executeUpdate("INSERT INTO users (FName,LName,username,password,dob,type" +
            ") VALUES ( '" + fname + "','" + lname + "','" + username + "','" + password + "','" + dobe + "','" + atype.getValue() + "')");

    if (status == 1) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User Registration");
        alert.setHeaderText(null);
        alert.setContentText("User have been Registered Succesfuly!");
        alert.showAndWait();
    }
}
else{
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("User Registration");
    alert.setHeaderText(null);
    alert.setContentText("Fill All the Fields! :(");
    alert.showAndWait();
}

    }
    @FXML
    void setting(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/aView/setting.fxml"));
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
    void homei(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/aView/homeView.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    @FXML
    void dash(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/aView/homeAdmin.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    @FXML
    void Courses(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/aView/courses.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    @FXML
    void users(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/aView/users.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    @FXML
    void dcourse(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/aView/displayCourses.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));

    }
    @FXML
    void duser(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/aView/displayCourses.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        atype.setValue("STUDENT");
        atype.setItems(typelist);
    }
}
