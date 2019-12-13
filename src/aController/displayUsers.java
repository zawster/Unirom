package aController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mainP.Course;
import mainP.user;
import utilities.sqliteConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class displayUsers implements Initializable {

    @FXML
    private TableView<user> tableView;
    @FXML
    private TableColumn<user,Integer> idColumn;
    @FXML
    private TableColumn<user,String> FNameColumn;
    @FXML
    private TableColumn<user,String> LNameColumn;
    @FXML
    private TableColumn<user,String> GenderColumn;
    @FXML
    private TableColumn<user,String> addressColumn;
    @FXML
    private TableColumn<user,String> typeColumn;
    @FXML
    private TableColumn<user, String> dobColumn;



    @FXML
    public void loadData() {

        idColumn.setCellValueFactory(new PropertyValueFactory<user,Integer>("id"));
        FNameColumn.setCellValueFactory(new PropertyValueFactory<user,String>("fname"));
        LNameColumn.setCellValueFactory(new PropertyValueFactory<user,String>("lname"));
        GenderColumn.setCellValueFactory(new PropertyValueFactory<user,String>("gender"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<user,String>("address"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<user,String>("typee"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<user,String>("dob"));

        tableView.setItems(getUsers());
    }

    public ObservableList<user> getUsers(){
        ObservableList<user> user= FXCollections.observableArrayList();
        try {
            Connection connection = sqliteConnection.dbConnector();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                user.add(new user(resultSet.getInt("id"), resultSet.getString("FName"), resultSet.getString("LName"),  resultSet.getString("type"), resultSet.getString("gender"), resultSet.getString("address"), resultSet.getString("dob")));
            }


        } catch (SQLException e) {
            System.err.println("Cannot Connect to Database");
        }



        return user;
    }

    @FXML
    public void rmData() throws SQLException {
        user selectedItem = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().remove(selectedItem);


        Connection connection= sqliteConnection.dbConnector();
        Statement statement = connection.createStatement();

        int status = statement.executeUpdate("DELETE FROM users WHERE id= '"+selectedItem.getId()+"'");

        if (status==1) {
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Remove Course");
            alert.setHeaderText(null);
            alert.setContentText("User "+selectedItem.getFname()+" "+selectedItem.getLname()+" have been removed Successfuly!");
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
    void duser(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/aView/displayCourses.fxml"));
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
    void Courses(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/aView/courses.fxml"));
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
    void users(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/aView/users.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
