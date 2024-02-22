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

public class DoctorAccRegController implements Initializable {

    private Stage stage;
    String[] genType = {"Male", "Female"};
    String[] bloodType = {"O+", "A+", "B+", "AB+", "O-", "A-", "B-", "AB-"};
    String[] departmentType = {"Cardiologist", "Medicine", "ENT","Eye Specialist","Neurologist"};

    @FXML
    private ComboBox<String> bloodGroup;

    @FXML
    private ComboBox<String> department;

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
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.show();
    }

    @FXML
    void registerMethod(ActionEvent event) throws IOException {
        status.setText("");

        FileReader fr = new FileReader("src/main/resources/com/example/hospital_manament/" +
                                                "Records/Doctor's Record");
        BufferedReader br = new BufferedReader(fr);
        String check = "";
        while (true){
            String s = br.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            if (x[6].equals(email.getText())){
                check = x[6];
            }
        }//while Ends

        if (fullName.getText().isEmpty() || dob.getValue()==null || gender.getValue()==null ||
                department.getValue()==null || bloodGroup.getValue()==null ||
                phoneNo.getText().isEmpty() || email.getText().isEmpty() ||
                password.getText().isEmpty() || confirmPassword.getText().isEmpty())
        {
            status.setText(status.getText()+"*** Some Fields you might leave Empty!");
        }

        else if (email.getText().equals(check))
        {
            status.setText("*** E-mail has already used!");
        }

        else if (password.getText().equals(confirmPassword.getText()))
        {
            // Confirm Details work not be done yet.
            ArrayList<Doctor> dList = new ArrayList<>();
            dList.add(new Doctor(fullName.getText(), dob.getValue().toString(),
                    gender.getValue(), bloodGroup.getValue(), department.getValue() ,
                    phoneNo.getText(), email.getText(), password.getText()));

            String t = String.valueOf(dList);
            String t1 = t.replace("[","").replace("]","").
                          replace(", ","");

            FileWriter fwd = new FileWriter("src/main/resources/com/example/hospital_manament/" +
                    "Records/Doctor's Record", true);
            BufferedWriter bwd = new BufferedWriter(fwd);
            bwd.write(t1);
            bwd.close();
            fwd.close();
            //working

            FXMLLoader ld = new FXMLLoader(getClass().getResource("login.fxml"));
            Scene scene = new Scene(ld.load());
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login Form");
            stage.show();
        }

        else {
            status.setText(status.getText()+"*** Retype your Password properly!");
        }


    }//Method Ends


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender.getItems().addAll(genType);
        bloodGroup.getItems().addAll(bloodType);
        department.getItems().addAll(departmentType);
    }
}
