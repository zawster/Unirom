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
import mainP.Student;
import utilities.sqliteConnection;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class marksFaculty implements Initializable {
    public String std_id,course_id,cour_id;
    @FXML
    private ComboBox comboType;
    @FXML
    private Label courseLabel;
    @FXML
    private TableView<modelFaculty> table;
    @FXML
    private TableColumn<modelFaculty,String> col_sId;
    @FXML
    private TableColumn<modelFaculty,String> col_fName;
    @FXML
    private TableColumn<modelFaculty,String> col_lName;
    @FXML
    private TableColumn<modelFaculty,String> col_cId;
    @FXML
    private TextField marksField;
    @FXML
    private TextField totalMarks;
    @FXML
    private TextField Id;

    public Connection con;
    ObservableList<modelFaculty> obList = FXCollections.observableArrayList();
    ObservableList<String> comboList = FXCollections.observableArrayList("quiz","assignment","sessional","final","project");
    @Override
    public void initialize(URL location, ResourceBundle resources){

        cour_id = courseLabel.getText();
        comboType.setItems(comboList);
        try {
//            JOptionPane.showMessageDialog(null,"SELECT usr.id , FName, LName,cID FROM users as usr, registration as c WHERE c.sID = usr.id AND cID LIKE '"+cour_id+"';");
            con  = sqliteConnection.dbConnector();
            ResultSet rs = con.createStatement().executeQuery("SELECT usr.id , FName, LName,cID FROM users as usr, registration as c, courses as c2 WHERE  c.sID = usr.id AND c2.code LIKE '"+cour_id+"' AND c.id = c2.id;");

            while (rs.next()){
                obList.add(new modelFaculty(rs.getString("id"),rs.getString("FName"),rs.getString("LName"),rs.getString("cID")));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        col_sId.setCellValueFactory(new PropertyValueFactory<>("std_id"));
        col_fName.setCellValueFactory(new PropertyValueFactory<>("FName"));
        col_lName.setCellValueFactory(new PropertyValueFactory<>("LName"));
        col_cId.setCellValueFactory(new PropertyValueFactory<>("course_id"));

        table.setItems(obList);
        table.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() >= 1) {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    modelFaculty selectedPerson = table.getSelectionModel().getSelectedItem();
                    std_id = selectedPerson.getStd_id();
                    course_id = selectedPerson.getCourse_id();
//                    std_Id_text.setText(stId);
//                    cour_Id_text.setText(cId);
                }
            }
        });
    }


    @FXML
    void attendence(MouseEvent event) throws  IOException{
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
    @FXML
    void logout(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/mainP/login.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));

    }
    @FXML
    void FacultyDashboard(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fView/homeFaculty.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));

    }
    @FXML
    void saveMarks(){
        String t_marks,marks, id, option;
        option = (String) comboType.getValue();
        id = Id.getText();
        marks =marksField.getText();
        t_marks = totalMarks.getText();

        String query = "INSERT INTO "+option+" VALUES('"+id+"','"+std_id+"','"+course_id+"','"+marks+"','"+t_marks+"');";
//        String query = "INSERT INTO marks VALUES('"+std_id+"','"+course_id+"','"+marks+"');";
        try {
            con  = sqliteConnection.dbConnector();
            con.createStatement().executeUpdate(query);
            marksField.setText("");
            JOptionPane.showMessageDialog(null, "Marks Saved Successfully!!");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Marks Saved Failed");
            e.printStackTrace();
        }
    }
   

}
