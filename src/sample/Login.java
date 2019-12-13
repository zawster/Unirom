package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
public class Login {

    @FXML
    private TextField fd_user;

    @FXML
    private PasswordField fd_pass;

    @FXML
    void fd_login(MouseEvent event) throws SQLException, IOException {
        user u1=new user();
        u1.pass = fd_pass.getText();
        u1.user = fd_user.getText();

        Connection connection=sqliteConnection.dbConnector();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from users where username" +
                " = '" + u1.user + "' and password = '" + u1.pass + "'");

        if (resultSet.next()) {
            Parent root = FXMLLoader.load(getClass().getResource("homeStudent.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));

        }


    }

    double x = 0, y =0;
    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void dragged(MouseEvent event) {

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }


    @FXML
    void signup(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));

    }


}
