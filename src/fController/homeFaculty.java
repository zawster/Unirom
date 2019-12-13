package fController;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mainP.Student;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class homeFaculty implements Initializable {
    @FXML
    private ComboBox<String> comboBox;
    public String courseId;
//    ObservableList<String> list = FXCollections.observableArrayList("CS101","CS301","CS307");
    public ObservableList<String> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ResultSet rs = modelFaculty.getCources(Student.getName());
            while(rs.next())
            {

                list.add(rs.getString(1));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        comboBox.setItems(list);
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
        Parent root = FXMLLoader.load(getClass().getResource("/fView/homeview.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));

    }
    @FXML
    void attendance(MouseEvent event) throws IOException {
        final FXMLLoader fxmlLoader;
        courseId = comboBox.getValue();
        if(courseId == null) {
            JOptionPane.showMessageDialog(null,"Please select your course id..:/)");
        }
        else{
            fxmlLoader = new FXMLLoader(getClass().getResource("/fView/attendenceFaculty.fxml"));
            fxmlLoader.getNamespace().put("courseText", courseId);
            Parent root = fxmlLoader.load();
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
        }

    }
    @FXML
    void marksPage(MouseEvent event) throws IOException {
        final FXMLLoader fxmlLoader;
        courseId = comboBox.getValue();
        if(courseId == null) {
            JOptionPane.showMessageDialog(null,"Please select your course id..:/)");
        }
        else{
            fxmlLoader = new FXMLLoader(getClass().getResource("/fView/marksFaculty.fxml"));
            fxmlLoader.getNamespace().put("courseText", courseId);
            Parent root = fxmlLoader.load();

            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();

            stage.setScene(new Scene(root));
        }

    }



}
