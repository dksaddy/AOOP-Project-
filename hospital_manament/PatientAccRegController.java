package com.example.hospital_manament;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PatientAccRegController implements Initializable {

    private Stage stage;
    String[] genType = {"Male", "Female"};
    String[] bloodType = {"O+", "A+", "B+", "AB+", "O-", "A-", "B-", "AB-"};

    @FXML
    private TextField address;

    @FXML
    private ComboBox<String> bloodGroup;

    @FXML
    private Button cancelBt;

    @FXML
    private TextField confirmPassword;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField email;

    @FXML
    private TextField fullName;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private TextField password;

    @FXML
    private TextField phoneNo;

    @FXML
    private Button registerBt;

    @FXML
    private Label status;



    @FXML
    void cancelMethod(ActionEvent event) throws IOException {
        FXMLLoader ld = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(ld.load());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.show();
    }

    @FXML
    void registerMethod(ActionEvent event) throws IOException {

        status.setText("");

        FileReader fr = new FileReader("src/main/resources/com/example/hospital_manament/" +
                                "Records/Patient's Record");
        BufferedReader br = new BufferedReader(fr);
        String check = "";
        while (true){
            String s = br.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            if (email.getText().equals(x[6])){
                check = x[6];
            }
        }//while ends

        if (fullName.getText().isEmpty() || dob.getValue()==null || gender.getValue()==null ||
        bloodGroup.getValue()==null || address.getText().isEmpty() || phoneNo.getText().isEmpty() ||
         email.getText().isEmpty() || password.getText().isEmpty() || confirmPassword.getText().isEmpty())
        {
                status.setText("*** Some Fields you might leave Empty!");
        }

        else if (check.equals(email.getText()))
        {
            status.setText("*** E-mail has already used!");
        }


        else if (password.getText().equals(confirmPassword.getText()))
        {
            // Kepping information in a List.
            ArrayList<Patient> patientList = new ArrayList<>();

            patientList.add(new Patient(fullName.getText(), dob.getValue().toString(), gender.getValue(),
                                        bloodGroup.getValue(), address.getText(), phoneNo.getText(),
                                        email.getText(), password.getText()));

            //Converting the List Into String.
            String t = String.valueOf(patientList);
            String t1 = t.replace("[","").replace("]","").
                          replace(", ","");

            FileWriter fw = new FileWriter("src/main/resources/com/example/hospital_manament/" +
                                      "Records/Patient's Record",true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(t1);
            bw.close();
            fw.close();


           //do some dialouge box work here for more accurate.

            FXMLLoader ld = new FXMLLoader(getClass().getResource("login.fxml"));
            Scene scene = new Scene(ld.load());
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login Form");
            stage.show();
        }


        else {
            status.setText("*** Retype your Password properly!");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       gender.getItems().addAll(genType);
       bloodGroup.getItems().addAll(bloodType);
    }
}
