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

public class PatientHomeController implements Initializable {

    private Stage stage;

    @FXML
    private Button AFbt;

    @FXML
    private Button BIBbt;

    @FXML
    private Button CAbt;

    @FXML
    private Button HCbt;

    @FXML
    private Button PBbt;

    @FXML
    private Button logoutBt;




    @FXML
    private Label a;

    @FXML
    private Label age;

    @FXML
    private Label ambulance;

    @FXML
    private Label apooint;

    @FXML
    private Label bedIcu;

    @FXML
    private Label bg;

    @FXML
    private Label dob;

    @FXML
    private Label e;

    @FXML
    private Label g;

    @FXML
    private Label n;

    @FXML
    private Label p;

    @FXML
    private Label runnig;






    @FXML
    void logoutMethod(ActionEvent event)  {
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

    }//Method end



    //Appointment Form Button
    @FXML
    void AFmethod(ActionEvent event) throws IOException {
        FXMLLoader ld = new FXMLLoader(getClass().getResource("appointmentForm.fxml"));
        Scene scene = new Scene(ld.load());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Appointment Form");
        stage.show();
        AFbtSceneController ob = ld.getController();
        ob.setMail(loginController.identity);
    }

    //Bed and ICU Booking Button
    @FXML
    void BIBmethod(ActionEvent event) throws IOException {
        FXMLLoader ld = new FXMLLoader(getClass().getResource("BedAndICUboking.fxml"));
        Scene scene = new Scene(ld.load());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Bad & ICU Booking Page");
        stage.show();
        BIBbtSceneController ob = ld.getController();
        ob.setMail(loginController.identity);
    }

    //Health Condition Button
    @FXML
    void HCmethod(ActionEvent event) throws IOException {
        FXMLLoader ld = new FXMLLoader(getClass().getResource("healthCondition.fxml"));
        Scene scene = new Scene(ld.load());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Health Condition Page");
        stage.show();
    }

    //Call Ambulance Button
    @FXML
    void CAmethod(ActionEvent event) throws IOException {
        FXMLLoader ld = new FXMLLoader(getClass().getResource("callAmbulance.fxml"));
        Scene scene = new Scene(ld.load());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Ambulance Service Page");
        stage.show();
        CAbtSceneController ob = ld.getController();
        ob.setInfo();
    }

    //Bills and Payment Button
    @FXML
    void PBmethod(ActionEvent event) throws IOException {
        FXMLLoader ld = new FXMLLoader(getClass().getResource("billsAndPayment.fxml"));
        Scene scene = new Scene(ld.load());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Bills & Payment Page");
        stage.show();
    }





    void profile() {
        n.setText(loginController.sName);
        e.setText(loginController.identity);
        p.setText(loginController.sPhoneNumber);
        a.setText(loginController.sLocation);

        g.setText(loginController.sGender);
        bg.setText(loginController.sBloodGroup);
        dob.setText(loginController.sDob);
        age.setText(loginController.calculateAge(dob.getText()));
    }//Method End

    void reading() throws IOException {
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
            String[] x = s.split("~");
            if (x[1].equals(loginController.identity))
                apooint.setText("You are Appointed.");
        }

        while (true) {
            String s = br2.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            if (x[1].equals(loginController.identity))
                runnig.setText("You are Observing Follow Prescription.");
        }

        while (true) {
            String s = br3.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            if (x[1].equals(loginController.identity))
                ambulance.setText("You Booked an Ambulance.");
        }

        while (true) {
            String s = br4.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            if (x[0].equals(loginController.identity))
                bedIcu.setText("You are Admitted "+x[6]+ " No. Room for "+x[7]+" Days.");
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profile();
        try {
            reading();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
