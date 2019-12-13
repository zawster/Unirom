package fController;
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
import utilities.sqliteConnection;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
// Faculty Attendence
public class attendenceFaculty implements Initializable {


    @FXML
    private Label courseLabel;
    @FXML
    private TableView<modelAttdFaculty> table;
    @FXML
    private TableColumn<modelAttdFaculty,String> col_stdId;
    @FXML
    private TableColumn<modelAttdFaculty,String> col_cId;
    @FXML
    private TextField textLecture;
    @FXML
    private ComboBox<String> duration;
    @FXML
    private ComboBox<String> presence;

    @FXML
    private DatePicker datePicker;

    public Connection con;
    ObservableList<modelAttdFaculty> obList = FXCollections.observableArrayList();

    public String std_id,course_id,cour_id;

    ObservableList<String> durationList = FXCollections.observableArrayList("1:30","2:00","3:00");
    ObservableList<String> presenceList = FXCollections.observableArrayList("P","L","A");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Setting combobox values
        duration.setItems(durationList);
        presence.setItems(presenceList);

        cour_id = courseLabel.getText();


//        comboType.setItems(comboList);
        try {
            con  = sqliteConnection.dbConnector();
            ResultSet rs = con.createStatement().executeQuery("SELECT usr.id ,cID FROM users as usr, registration as c WHERE c.sID = usr.id AND cID = (SELECT id from courses WHERE code LIKE '"+cour_id+"');");

            while (rs.next()){
                obList.add(new modelAttdFaculty(rs.getString("id"),rs.getString("cID")));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        col_stdId.setCellValueFactory(new PropertyValueFactory<>("std_id"));
        col_cId.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        table.setItems(obList);

        table.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() >= 1) {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    modelAttdFaculty selectedStudent = table.getSelectionModel().getSelectedItem();
                    std_id = selectedStudent.getStd_id();
                    course_id = selectedStudent.getCourse_id();
                    JOptionPane.showMessageDialog(null,std_id+"\n"+cour_id);
                }
            }
        });

    }
    @FXML
    void saveAttendence(){
        DatePicker dob= new DatePicker();
        String dura, lecNo, pres;
        LocalDate dobe = dob.getValue();

        dura=  duration.getValue();
        lecNo = textLecture.getText();
        pres = presence.getValue();
//
        String query = "INSERT INTO attendence VALUES('"+std_id+"','"+cour_id+"',"+lecNo+",'"+dobe+"','"+dura+"','"+pres+"');";

        try {
            con  = sqliteConnection.dbConnector();
            con.createStatement().executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Attendence Updated Successfully!!");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Attendence Updated Failed");
            e.printStackTrace();
        }
    }
    @FXML
    void FacultyDashboard(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fView/homeFaculty.fxml"));

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
    void marks(MouseEvent event) throws  IOException{
        String courseId = cour_id;
        final FXMLLoader fxmlLoader;
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

}
