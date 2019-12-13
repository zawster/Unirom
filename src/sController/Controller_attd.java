package sController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mainP.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller_attd implements Initializable {
    private ResultSet r;
    private ResultSet r_attd;
    String res;
    private Model_attd md = new Model_attd();


    @FXML
    private TableView<getSet_attd> table;
    @FXML
    private TableColumn<getSet_attd, String> lec_no;              // tabel columns ...
    @FXML
    private TableColumn<getSet_attd, String> date;
    @FXML
    private TableColumn<getSet_attd, String> duration;
    @FXML
    private TableColumn<getSet_attd, String> presence;
    @FXML
    private Label show_percentage;
    public String student_id ;
    @FXML
    public ComboBox<String> comboBox;
    public String value;
    ObservableList<String> list = FXCollections.observableArrayList();

    ObservableList<getSet_attd> print_table = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {
        catch_Courses();
        student_id = Student.getSid();
       /// Connection_db cn = new Connection_db();

    }


    public void print_Data_on_table() {
        table.getItems().clear();
        value = comboBox.getValue();
        show_percentage.setText(md.att_Percentage(student_id, value));
        r_attd = md.fetch_attd_Data(student_id, value);
        try {
            while (r_attd.next()) {
                print_table.add(new getSet_attd(r_attd.getString("lecture_no"), r_attd.getString("date"), r_attd.getString("duration"), r_attd.getString("presence")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        lec_no.setCellValueFactory(new PropertyValueFactory<>("lect_no"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration_lec"));
        presence.setCellValueFactory(new PropertyValueFactory<>("presence"));


        table.setItems(print_table);

    }

    @FXML
    public void catch_Courses()                                 // Catch registered courses for combobox....
    {
        ResultSet rs = md.registeredCourse();
        try {
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException e) {
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
    void dash(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sView/homeStudent.fxml"));

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
    @FXML
    void courseReg(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sView/courseRegistration.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));

    }

}

