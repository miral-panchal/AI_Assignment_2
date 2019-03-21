package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    String [] city_names;
    ArrayList<String []> distances = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Calculations calc = new Calculations(city_names, distances);


        primaryStage.setTitle("Travelling Salesman Problem - Genetic Algorithm");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
