package com.example.hospital_manament;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class loginController {

    private Stage stage;

    static String identity = "";
    static String sName = "";
    static String sGender = "";
    static String sBloodGroup = "";
    static String sLocation = "";
    static String sPhoneNumber = "";
    static String sDob = "";
    static String sDUe = "";


    static String dIdentity = "";
    static String dsName = "";
    static String dsGender = "";
    static String dsBloodGroup = "";
    static String dsDepartment = "";
    static String dsPhoneNumber = "";
    static String dsDob = "";




    @FXML
    private RadioButton adminRb;

    @FXML
    private Button createAccBt;

    @FXML
    private RadioButton doctorRb;

    @FXML
    private Button loginBt;

    @FXML
    private ToggleGroup loginType;

    @FXML
    private PasswordField password;

    @FXML
    private RadioButton patientRb;

    @FXML
    private Label status;

    @FXML
    private TextField userName;


    @FXML
        //Create Account Buttons works.
    void CreateAccMethod(ActionEvent event) throws IOException {

        status.setText("Status:"); //Setting the Text like before.

        if (patientRb.isSelected()) //Patient Reg page will open.
        {
            FXMLLoader ld = new FXMLLoader(getClass().getResource("patientAccReg.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(ld.load());
            stage.setScene(scene);
            stage.setTitle("Registration Form");
            stage.show();
        } else if (doctorRb.isSelected()) //Doctor Reg page will open.
        {
            FXMLLoader ld = new FXMLLoader(getClass().getResource("doctorAccReg.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(ld.load());
            stage.setScene(scene);
            stage.setTitle("Registration Form");
            stage.show();
        } else {
            status.setText(status.getText() + " Account Type is not Selected");
        }

    }//Method End

    @FXML
    void loginMethod(ActionEvent event) throws IOException, ClassNotFoundException {

        status.setText(""); //Setting the Text like before.
//Patient work in log in.
        if (patientRb.isSelected()) {
            //Taking arraylist
            ArrayList<Patient> pList = new ArrayList<>();

            FileReader frp = new FileReader("src/main/resources/com/example/hospital_manament/" +
                    "Records/Patient's Record");
            BufferedReader brp = new BufferedReader(frp);
            while (true) {
                String s = brp.readLine();
                if (s == null) break;
                String[] x = s.split("~");
                pList.add(new Patient(x[0],x[1],x[2],x[3],x[4],x[5],x[6],x[7]));
            }//while Ends
            frp.close();
            brp.close();

            Iterator<Patient> it = pList.iterator();
            while (it.hasNext()){
                Patient p = it.next();
                if (p.email.equals(userName.getText()) && p.pass.equals(password.getText()))
                {
                    //Set user Info wherever he goes.
                    identity = p.email;
                    sName = p.name;
                    sGender = p.gender;
                    sBloodGroup = p.bloodGr;
                    sLocation = p.address;
                    sPhoneNumber = p.phNo;
                    sDob = p.dob;

                    FXMLLoader ld = new FXMLLoader(getClass().getResource("patientHome.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(ld.load());
                    stage.setScene(scene);
                    stage.setTitle("Patient's Home");
                    stage.show();

                    HomePageController.msg = "A Patient Logged in";
                    HomePageController.oos.writeObject(HomePageController.msg);
                    Object s = HomePageController.ois.readObject();
                    System.out.println(s);

                }else {
                    status.setText("Status: User Name or Password is incorrect!");
                }
            }//Iterator Ends

        } //if end

        //Doctor work in login
        else if (doctorRb.isSelected()) {

            ArrayList<Doctor> dList = new ArrayList<>();

            FileReader frd = new FileReader("src/main/resources/com/example/hospital_manament/" +
                    "Records/Doctor's Record");
            BufferedReader brd = new BufferedReader(frd);
            while (true) {

                String s = brd.readLine();
                if (s == null) break;
                String[] x = s.split("~");
                dList.add(new Doctor(x[0],x[1],x[2],x[3],x[4],x[5],x[6],x[7]));

            }//while ends
            frd.close();
            brd.close();

            Iterator<Doctor> it = dList.iterator();
            while (it.hasNext()){
                Doctor d = it.next();
                if (d.email.equals(userName.getText()) && d.pass.equals(password.getText()))
                {
                    //Set user Info wherever he goes.
                    dIdentity = d.email;
                    dsName = d.name;
                    dsGender = d.gender;
                    dsBloodGroup = d.bloodGr;
                    dsDepartment = d.department;
                    dsPhoneNumber = d.phNo;
                    dsDob = d.dob;

                    FXMLLoader ld = new FXMLLoader(getClass().getResource("doctorHome.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(ld.load());
                    stage.setScene(scene);
                    stage.setTitle("Doctor's Home");
                    stage.show();

                    HomePageController.msg = "A Doctor Logged in";
                    HomePageController.oos.writeObject(HomePageController.msg);
                    Object s = HomePageController.ois.readObject();
                    System.out.println(s);

                }else {
                    status.setText("Status: User Name or Password is incorrect!");
                }
            }//Iterator Ends

        } // else if end
        else {
            status.setText(status.getText() + " Account Type is not Selected!");
        }

    }//Method Ends



    //Self Created Method.
    public static String calculateAge(String date) {
        String[] arr = date.split("-");

        int year = Integer.parseInt(arr[0]);
        int result = 2022-year;

        return String.valueOf(result);
    }//Method end


}//Class End
