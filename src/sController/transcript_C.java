package sController;

import com.mysql.cj.protocol.Resultset;
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

import java.io.IOException;
import java.sql.*;


import java.net.URL;
import java.util.ResourceBundle;

import mainP.Student;
import utilities.sqliteConnection;
public class transcript_C<label_data> implements Initializable {

    private ResultSet total_crdt_hrs;      // In integer form...
    private ResultSet trans_data;
    private ResultSet label_data;

    private String total_crdt_str;      // In string form

    private Statement st;
    private ResultSet rs;
    private Connection cn;
    public String smester_id;
    public transcript_M tr = new transcript_M();
    public  sqliteConnection db = new sqliteConnection();

    @FXML
    private Label crh_earned;
    @FXML
    private Label cgpa;
    @FXML
    private Label sgpa;
    @FXML
    private Label roll_no;
    @FXML
    private Label st_name;
    @FXML
    private Label batch;
    @FXML
    private Label crh_attempt;
    
    
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TableView<getSet_trans> table;
    @FXML
    private TableColumn<getSet_trans,String> code;
    @FXML
    private TableColumn<getSet_trans,String> course_name;
    @FXML
    private TableColumn<getSet_trans,String> crdt_hrs;
    @FXML
    private TableColumn<getSet_trans,String> grades;
    @FXML
    private TableColumn<getSet_trans,String> type;
    @FXML
    private TableColumn<getSet_trans,String> remarks;
    @FXML
    private TableColumn<getSet_trans,String> points;

    public String student_id;
    ObservableList<getSet_trans> oblist = FXCollections.observableArrayList();

    ObservableList<String> list = FXCollections.observableArrayList("1","2","3","4","5","6","7","8");              // list for smester no. ...
    @FXML
    void logout(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/mainP/login.fxml"));

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

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {


            comboBox.setItems(list);
        try
        {
            set_Label_name();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        try
        {
            set_Label_id();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }


    }

    public void print_Data_on_table() throws SQLException
    {
        student_id = Student.getSid();
        table.getItems().clear();
        smester_id = comboBox.getValue();
        //System.out.println("Value of get " + smester_id);
        crh_attempt.setText(tr.total_credit_hrs(student_id, smester_id));

        crh_earned.setText(tr.total_credit_hrs(student_id, smester_id));

        //ResultSet rs = cn.createStatement().executeQuery("select * from trans where st_id like '%"+st_id+"%'  AND sm_id like "+semester_id+"";
        trans_data = tr.fetch_trans_Data(student_id, smester_id);                   //table data///
        while(trans_data.next())
        {
            oblist.add(new getSet_trans(trans_data.getString("cs_id"),trans_data.getString("crs_name"),trans_data.getString("crd_hr"), trans_data.getString("type")));
        }

        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        course_name.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        crdt_hrs.setCellValueFactory(new PropertyValueFactory<>("crdHrs"));
        //grades.setCellValueFactory(new PropertyValueFactory<>("grades"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        //remarks.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        //points.setCellValueFactory(new PropertyValueFactory<>("points"));


        table.setItems(oblist);

    }

    public void set_Label_name() throws SQLException                             // name ..
   {
        label_data = tr.fetch_st_name(student_id);
        while (label_data.next())
        {
            String value = label_data.getString("name");
            st_name.setText(value);
        }

    }

    public void set_Label_id() throws SQLException                             // id..
    {
        label_data = tr.fetch_st_id(student_id);
        while (label_data.next())
        {
            String value = label_data.getString("st_id");
            roll_no.setText(value);
        }

    }



}