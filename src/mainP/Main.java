package mainP;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    Stage window;
    Scene scene1,scene2;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("UNIROM");
        primaryStage.setScene(new Scene(root, 1020, 580));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
