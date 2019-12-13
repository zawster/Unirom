package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class user {
    String user,pass,fname,lname,dob,address;

    void user(){
        user=" ";
        pass=" ";
        fname=" ";
        lname=" ";
        address=" ";

    }

    @FXML
    void fd_login(MouseEvent event) throws SQLException, IOException {

        Connection connection=sqliteConnection.dbConnector();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from users where username" +
                " = '" + user + "'");

        if (resultSet.next()) {
            Parent root = FXMLLoader.load(getClass().getResource("homeStudent.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));

        }


    }

}
