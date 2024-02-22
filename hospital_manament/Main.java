package com.example.hospital_manament;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader ld = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(ld.load());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Home");
        primaryStage.show();


        primaryStage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            shut(primaryStage);
        });

    }


    void shut(Stage stage) {
        String al = "Are You sure to close the Program ?";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,al, ButtonType.OK, ButtonType.CLOSE);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK){
            HomePageController.msg = "out";

            try {

                HomePageController.oos.writeObject(HomePageController.msg);
                System.out.println("You Close the Program");

                Object s = HomePageController.ois.readObject();
                String s1 = (String) s;
                System.out.println("Server: " + s1);



            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Problem Found in logout Button Method first catch clause");
            } finally {
                try {
                    HomePageController.s.close(); HomePageController.ois.close(); HomePageController.oos.close();
                    stage.close();
                }catch (IOException e) {
                    System.out.println("Problem Caught in log out Method Button");
                }
            }

        }//if end
        if (alert.getResult() == ButtonType.CLOSE) alert.close();

    }//Method End



}
