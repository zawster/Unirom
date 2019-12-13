package mainP;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utilities.sqliteConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Register {
    @FXML
    private TextField fd_fname;
    @FXML
    private TextField fd_lname;
    @FXML
    private TextField fd_user;
    @FXML
    private PasswordField fd_pass;
    @FXML
    private DatePicker fd_date;

    @FXML
    void fd_register(MouseEvent event) throws SQLException, IOException {
        String username, password, fname, lname;
        DatePicker dob= new DatePicker();
        password = fd_pass.getText();
        username = fd_user.getText();
        fname = fd_fname.getText();
        lname = fd_lname.getText();
        LocalDate dobe=dob.getValue();

        Connection connection= sqliteConnection.dbConnector();
        Statement statement = connection.createStatement();

        int status = statement.executeUpdate("INSERT INTO users (FName,LName,username,password,dob,type" +
                ") VALUES ( '"+ fname +"','"+ lname +"','"+ username +"','"+ password +"','"+ dobe +"','STUDENT')");

        if (status==1) {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User Registration");
            alert.setHeaderText(null);
            alert.setContentText("User have been Registered Succesfuly!");
            alert.showAndWait();

        }


    }


    @FXML
    void login(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));

    }
}
