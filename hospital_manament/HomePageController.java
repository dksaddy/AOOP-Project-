package com.example.hospital_manament;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Inherited;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    private Stage stage;

    @FXML
    void loginPage(ActionEvent event) throws IOException {
        FXMLLoader ld = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(ld.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Patient Profile");
        stage.show();
    }

    //  ***************   Networking Part   ***************    START   FROM  HERE  ********************

    static String msg = "";

    static Socket s = null;
    static ObjectOutputStream oos = null;
    static ObjectInputStream ois = null;

    public void connection() {
        try {
            s = new Socket("localhost",8818);
            System.out.println("You connected to Server");

            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }
    } //Method end


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection();
    }





}
