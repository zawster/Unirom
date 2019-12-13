package fController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class modelAttdFaculty {
    String std_id, course_id;

    public modelAttdFaculty(String std_id,  String course_id){
        this.std_id = std_id;
        this.course_id = course_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getStd_id() {
        return std_id;
    }
    @FXML
    void logout(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/mainP/login.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));

    }
}
