package sController;

import javafx.beans.property.SimpleStringProperty;
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
import javafx.util.Callback;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Student_Marks_C implements Initializable{


    private Student_marks_M m ;

    @FXML
    private ComboBox<String> combo;
    @FXML
    private TableView <Student_marks_GetSet> table;
    @FXML
    private TableColumn<Student_marks_GetSet, String> No;
    @FXML
    private TableColumn <Student_marks_GetSet, String> obtn_col;
    @FXML
    private TableColumn <Student_marks_GetSet, SimpleStringProperty> total_col;
    @FXML
    private Button Quizes;
    @FXML
    private Label info_table;
    @FXML
    private Label course_name;
    private String no;
    private String course;
    public ObservableList<String> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        m = new Student_marks_M();

        fun_combox();
        combo.setItems(list);

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
    @FXML
    void dash(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sView/homeStudent.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));

    }

    @FXML
    void attendance(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sView/Attendance.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));

    }

    ///
    public void setlabel()
    {
        course_name.setText(combo.getValue());
    }
    @FXML
    public void fun_combox()
    {
        ResultSet rs = m.registeredCourses();
        try {
            while(rs.next())
            {
                list.add(rs.getString(1));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void for_table_quizes()
    {
        course = combo.getValue();
        info_table.setText("Quiz");
        ResultSet rs = m.calculate_nos(course, "Quiz_Marks");
        ResultSet rs1 = m.calculate_obtn_marks(course, "Quiz_Marks");
        ResultSet rs2 = m.calculate_total_marks(course, "Quiz_Marks");
        ObservableList<Student_marks_GetSet> data = FXCollections.observableArrayList();
        try{
            while(rs.next() && rs1.next() && rs2.next())
            {
                data.add(new Student_marks_GetSet(rs.getString(1), rs1.getString(1), rs2.getString(1)));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        No.setCellValueFactory(new PropertyValueFactory<>("no"));
        obtn_col.setCellValueFactory(new PropertyValueFactory<>("obtn"));
        total_col.setCellValueFactory(new PropertyValueFactory<>("total"));

        // step: 4
        try{
            //System.out.println("Trying to send data to table");
            table.setItems(data);
        }
        catch(Exception e)
        {
            //System.out.println("Something went wrong.");
            e.printStackTrace();
        }

        table.setOnMouseClicked((MouseEvent event)->{
            if(event.getClickCount() >= 1){
                if(table.getSelectionModel().getSelectedItem() != null)
                {
                    Student_marks_GetSet selectedPerson = table.getSelectionModel().getSelectedItem();
                    no = selectedPerson.getNo();

                    ResultSet r1 = m.working_Min_Quiz(course, no);
                    ResultSet r2 = m.working_Max_Quiz(course, no);
                    ResultSet r3 = m.workingAvgQuiz(course, no);
                    ResultSet r4 = m.workingStdQuiz(course, no);

                    try{
                        while(r1.next() && r2.next() && r3.next() && r4.next())
                        {
                            JOptionPane.showMessageDialog(null, "MIN Marks: " + r1.getString(1) + "\n MAX marks: " + r2.getString(1)
                                    + "\n Average Marks: " + r3.getString(1) + "\n Standard deviation: " + r4.getString(1));
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }

                    // end here
                }
            }
        });


    }

    @FXML
    public void for_table_assignments()
    {
        course = combo.getValue();
        info_table.setText("Assignments");
        ResultSet rs = m.calculate_nos(course, "Assignments_Marks");
        ResultSet rs1 = m.calculate_obtn_marks(course, "Assignments_Marks");
        ResultSet rs2 = m.calculate_total_marks(course, "Assignments_Marks");
        ObservableList<Student_marks_GetSet> data = FXCollections.observableArrayList();
        try{
            while(rs.next() && rs1.next() && rs2.next())
            {
                data.add(new Student_marks_GetSet(rs.getString(1), rs1.getString(1), rs2.getString(1)));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        No.setCellValueFactory(new PropertyValueFactory<>("no"));
        obtn_col.setCellValueFactory(new PropertyValueFactory<>("obtn"));
        total_col.setCellValueFactory(new PropertyValueFactory<>("total"));

        // step: 4
        try{
            //System.out.println("Trying to send data to table");
            table.setItems(data);
        }
        catch(Exception e)
        {
            //System.out.println("Something went wrong.");
            e.printStackTrace();
        }
        table.setOnMouseClicked((MouseEvent event)->{
            if(event.getClickCount() >= 1){
                if(table.getSelectionModel().getSelectedItem() != null)
                {
                    Student_marks_GetSet selectedPerson = table.getSelectionModel().getSelectedItem();
                    no = selectedPerson.getNo();

                    ResultSet r1 = m.workingMinAss(course, no);
                    ResultSet r2 = m.workingMaxAss(course, no);
                    ResultSet r3 = m.workingAvgAss(course, no);
                    ResultSet r4 = m.workingStdAss(course, no);

                    try{
                        while(r1.next() && r2.next() && r3.next() && r4.next())
                        {
                            JOptionPane.showMessageDialog(null, "MIN Marks: " + r1.getString(1) + "\n MAX marks: " + r2.getString(1)
                                    + "\n Average Marks: " + r3.getString(1) + "\n Standard deviation: " + r4.getString(1));
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }

                    // end here
                }
            }
        });
    }

    @FXML
    public void for_table_sessional()
    {
        course = combo.getValue();
        info_table.setText("Sessional");
        ResultSet rs = m.calculate_nos(course, "sessional");
        ResultSet rs1 = m.calculate_obtn_marks(course, "sessional");
        ResultSet rs2 = m.calculate_total_marks(course, "sessional");
        ObservableList<Student_marks_GetSet> data = FXCollections.observableArrayList();
        try{
            while(rs.next() && rs1.next() && rs2.next())
            {
                data.add(new Student_marks_GetSet(rs.getString(1), rs1.getString(1), rs2.getString(1)));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        No.setCellValueFactory(new PropertyValueFactory<>("no"));
        obtn_col.setCellValueFactory(new PropertyValueFactory<>("obtn"));
        total_col.setCellValueFactory(new PropertyValueFactory<>("total"));

        // step: 4
        try{
            //System.out.println("Trying to send data to table");
            table.setItems(data);
        }
        catch(Exception e)
        {
            //System.out.println("Something went wrong.");
            e.printStackTrace();
        }
        table.setOnMouseClicked((MouseEvent event)->{
            if(event.getClickCount() >= 1){
                if(table.getSelectionModel().getSelectedItem() != null)
                {
                    Student_marks_GetSet selectedPerson = table.getSelectionModel().getSelectedItem();
                    no = selectedPerson.getNo();

                    ResultSet r1 = m.workingMinSessional(course , no);
                    ResultSet r2 = m.workingMaxSessional(course, no);
                    ResultSet r3 = m.workingAvgSessional(course, no);
                    ResultSet r4 = m.workingStdSessional(course, no);

                    try{
                        while(r1.next() && r2.next() && r3.next() && r4.next())
                        {
                            JOptionPane.showMessageDialog(null, "MIN Marks: " + r1.getString(1) + "\n MAX marks: " + r2.getString(1)
                                    + "\n Average Marks: " + r3.getString(1) + "\n Standard deviation: " + r4.getString(1));
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }

                    // end here
                }
            }
        });


    }

    @FXML
    public void for_table_final()
    {
        course = combo.getValue();
        info_table.setText("Final");
        ResultSet rs = m.calculate_nos(course, "final");
        ResultSet rs1 = m.calculate_obtn_marks(course, "final");
        ResultSet rs2 = m.calculate_total_marks(course, "final");
        ObservableList<Student_marks_GetSet> data = FXCollections.observableArrayList();
        try{
            while(rs.next() && rs1.next() && rs2.next())
            {
                data.add(new Student_marks_GetSet(rs.getString(1), rs1.getString(1), rs2.getString(1)));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        No.setCellValueFactory(new PropertyValueFactory<>("no"));
        obtn_col.setCellValueFactory(new PropertyValueFactory<>("obtn"));
        total_col.setCellValueFactory(new PropertyValueFactory<>("total"));

        // step: 4
        try{
            System.out.println("Trying to send data to table");
            table.setItems(data);
        }
        catch(Exception e)
        {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }
        table.setOnMouseClicked((MouseEvent event)->{
            if(event.getClickCount() >= 1){
                if(table.getSelectionModel().getSelectedItem() != null)
                {
                    Student_marks_GetSet selectedPerson = table.getSelectionModel().getSelectedItem();
                    no = selectedPerson.getNo();

                    ResultSet r1 = m.workingMinFinal(course, no);
                    ResultSet r2 = m.workingMaxFinal(course, no);
                    ResultSet r3 = m.workingAvgFinal(course, no);
                    ResultSet r4 = m.workingStdFinal(course, no);

                    try{
                        while(r1.next() && r2.next() && r3.next() && r4.next())
                        {
                            JOptionPane.showMessageDialog(null, "MIN Marks: " + r1.getString(1) + "\n MAX marks: " + r2.getString(1)
                                    + "\n Average Marks: " + r3.getString(1) + "\n Standard deviation: " + r4.getString(1));
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }

                    // end here
                }
            }
        });
    }

    @FXML
    public void for_table_project()
    {
        course = combo.getValue();
        info_table.setText("Project");
        ResultSet rs = m.calculate_nos(course, "project");
        ResultSet rs1 = m.calculate_obtn_marks(course, "project");
        ResultSet rs2 = m.calculate_total_marks(course, "project");
        ObservableList<Student_marks_GetSet> data = FXCollections.observableArrayList();
        try{
            while(rs.next() && rs1.next() && rs2.next())
            {
                data.add(new Student_marks_GetSet(rs.getString(1), rs1.getString(1), rs2.getString(1)));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        No.setCellValueFactory(new PropertyValueFactory<>("no"));
        obtn_col.setCellValueFactory(new PropertyValueFactory<>("obtn"));
        total_col.setCellValueFactory(new PropertyValueFactory<>("total"));

        // step: 4
        try{
            System.out.println("Trying to send data to table");
            table.setItems(data);
        }
        catch(Exception e)
        {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }
        table.setOnMouseClicked((MouseEvent event)->{
            if(event.getClickCount() >= 1){
                if(table.getSelectionModel().getSelectedItem() != null)
                {
                    Student_marks_GetSet selectedPerson = table.getSelectionModel().getSelectedItem();
                    no = selectedPerson.getNo();

                    ResultSet r1 = m.workingMinProjects(course, no);
                    ResultSet r2 = m.workingMaxProjects(course, no);
                    ResultSet r3 = m.workingAvgProjects(course, no);
                    ResultSet r4 = m.workingStdProjects(course, no);

                    try{
                        while(r1.next() && r2.next() && r3.next() && r4.next())
                        {

                            JOptionPane.showMessageDialog(null, "MIN Marks: " + r1.getString(1) + "\n MAX marks: " + r2.getString(1)
                                    + "\n Average Marks: " + r3.getString(1) + "\n Standard deviation: " + r4.getString(1));
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }

                    // end here
                }
            }
        });
    }





    // for extra
    public void for_Grand_total()
    {
        info_table.setText("Grand Total");
        course = combo.getValue();
        ResultSet rs1 = m.Q_OB_marks(course);
        ResultSet rs2 = m.Q_Total_Marks(course);
        ResultSet rs3 = m.A_OB_marks(course);
        ResultSet rs4 = m.A_Total_Marks(course);
        ResultSet rs5 = m.S_OB_marks(course);
        ResultSet rs6 = m.S_Total_Marks(course);
        ResultSet rs7 = m.F_OB_Marks(course);
        ResultSet rs8 = m.F_Total_Marks(course);
        ResultSet rs9 = m.P_OB_Marks(course);
        ResultSet rs10 = m.P_Total_Marks(course);

        String r1, r2, r3, r4, r5, r6, r7, r8, r9, r10;
        Double v1, v2, v3, v4, v5, obtn_marks, v6, v7, v8, v9,v10, total_marks;

        try{
            while(rs1.next() && rs2.next() && rs3.next() && rs4.next() && rs5.next() && rs6.next() && rs7.next() && rs8.next() && rs9.next() && rs10.next())
            {
                try {
                    r1 = rs1.getString(1);
                    r6 = rs2.getString(1);
                    r2 = rs3.getString(1);
                    r7 = rs4.getString(1);
                    r3 = rs5.getString(1);
                    r8 = rs6.getString(1);
                    r4 = rs7.getString(1);
                    r9 = rs8.getString(1);
                    r5 = rs9.getString(1);
                    r10 = rs10.getString(1);

                    v1 = Double.parseDouble(r1);
                    v6 = Double.parseDouble(r6);
                    v2 = Double.parseDouble(r2);
                    v7 = Double.parseDouble(r7);
                    v3 = Double.parseDouble(r3);
                    v8 = Double.parseDouble(r8);
                    v4 = Double.parseDouble(r4);
                    v9 = Double.parseDouble(r9);
                    v5 = Double.parseDouble(r5);
                    v10 = Double.parseDouble(r10);
                    obtn_marks = v1 + v2 + v3 + v4 + v5;
                    total_marks = v6 + v7 + v8 + v9 + v10;


                    JOptionPane.showMessageDialog(null, "Here are the Grand Total  marks\n\n\n "
                            + "Obtained Marks :-  " + obtn_marks + "\n Total Marks :- " + total_marks);
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null, "No data found in database");
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }


}
