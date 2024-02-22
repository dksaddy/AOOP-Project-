package com.example.hospital_manament;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DoctorHomeController implements Initializable {

    private Stage stage;

    @FXML
    private Button APSbt;

    @FXML
    private Button HRbt;

    @FXML
    private Button RPbt;

    @FXML
    private Button APbt;

    @FXML
    private Button ambulanceBt;

    @FXML
    private Button logoutBt;



    //***********************           *********************                  *********************
    @FXML
    private Label age;

    @FXML
    private Label bg;

    @FXML
    private Label d;

    @FXML
    private Label dob;

    @FXML
    private Label g;

    @FXML
    private Label m;

    @FXML
    private Label n;

    @FXML
    private Label p;

    @FXML
    private Label runningP;

    @FXML
    private Label admittedP;
    @FXML
    private Label ambuP;

    @FXML
    private Label appoP;




    @FXML
    void logoutMethod(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Log out ?",
                ButtonType.OK, ButtonType.CANCEL);

        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK){
            HomePageController.msg = "out";

            try {

                HomePageController.oos.writeObject(HomePageController.msg);

                Object s = HomePageController.ois.readObject();
                String s1 = (String) s;
                System.out.println("Server: " + s1);



            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Problem Found in logout Button Method first catch clause");
            } finally {
                try {
                    HomePageController.s.close(); HomePageController.ois.close(); HomePageController.oos.close();
                    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    stage.close();
                }catch (IOException e) {
                    System.out.println("Problem Caught in log out Method Button");
                }
            }

        }//if end

        else if (alert.getResult() == ButtonType.CANCEL) {
            alert.close();
        }

    }

    @FXML
    void APSmethod(ActionEvent event) throws IOException {
        FXMLLoader ld = new FXMLLoader(getClass().getResource("appoinetedPatient.fxml"));
        Scene scene = new Scene(ld.load());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Appointed Patient List");
        stage.show();
    }

    @FXML
    void RPmethod(ActionEvent event) throws IOException {
        FXMLLoader ld = new FXMLLoader(getClass().getResource("runningPatient.fxml"));
        Scene scene = new Scene(ld.load());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Running Patient Page");
        stage.show();
    }

    @FXML
    void APmethod(ActionEvent event) throws IOException {
        FXMLLoader ld = new FXMLLoader(getClass().getResource("admittedPatient.fxml"));
        Scene scene = new Scene(ld.load());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Admitted Patient Page");
        stage.show();
    }

    @FXML
    void HRmethod(ActionEvent event) throws IOException {
        FXMLLoader ld = new FXMLLoader(getClass().getResource("healthReport.fxml"));
        Scene scene = new Scene(ld.load());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Health Report Page");
        stage.show();
    }

    @FXML
    void ambulanceServiceMethod(ActionEvent event) throws IOException {
        FXMLLoader ld = new FXMLLoader(getClass().getResource("ambulanceService.fxml"));
        Scene scene = new Scene(ld.load());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Ambulance Service");
        stage.show();
        ASbtSceneController ob = ld.getController();
        ob.readForSet();
    }


    void profile() {
        n.setText(loginController.dsName);
        m.setText(loginController.dIdentity);
        p.setText(loginController.dsPhoneNumber);
        d.setText(loginController.dsDepartment);

        bg.setText(loginController.dsBloodGroup);
        g.setText(loginController.dsGender);
        dob.setText(loginController.dsDob);
        age.setText(loginController.calculateAge(dob.getText()));
    }

    void task() throws IOException {
        BufferedReader br1 = new BufferedReader(new FileReader("src/main/resources/com/example/" +
                "hospital_manament/Records/Appointed Patients"));
        BufferedReader br2 = new BufferedReader(new FileReader("src/main/resources/com/example/" +
                "hospital_manament/Records/Runing Patients"));
        BufferedReader br3 = new BufferedReader(new FileReader("src/main/resources/com/example/" +
                "hospital_manament/Records/Ambulance"));
        BufferedReader br4 = new BufferedReader(new FileReader("src/main/resources/com/example/" +
                "hospital_manament/Records/Admited Patient List"));

        while (true) {
            String s = br1.readLine();
            if (s==null) break;
            String[] x = s.split("\n");
            appoP.setText(String.valueOf(x.length));
        }

        while (true) {
            String s = br2.readLine();
            if (s==null) break;
            String[] x = s.split("\n");
            runningP.setText(String.valueOf(x.length));
        }

        while (true) {
            String s = br3.readLine();
            if (s==null) break;
            String[] x = s.split("\n");
            ambuP.setText(String.valueOf(x.length));
        }

        while (true) {
            String s = br4.readLine();
            if (s==null) break;
            String[] x = s.split("\n");
            admittedP.setText(String.valueOf(x.length));
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profile();

        try {
            task();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
