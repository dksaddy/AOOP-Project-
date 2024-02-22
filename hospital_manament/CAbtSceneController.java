package com.example.hospital_manament;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CAbtSceneController implements Initializable {


    String[] thaNa = {"Mohammadpur", "Dhanmondi", "Mirpur","Badda"};
    String[] postCd = {"1207", "1205", "1206" };
    static String ambuPrice = "1800";



    private Stage stage;


    @FXML
    void backMethod(ActionEvent event) throws IOException {
        FXMLLoader ld = new FXMLLoader(getClass().getResource("patientHome.fxml"));
        Scene scene = new Scene(ld.load());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Patient Home");
        stage.show();
    }


    @FXML
    private Label mail;

    @FXML
    private Label name;

    @FXML
    private Label phnNo;

    @FXML
    private Label registeredAdrs;

    @FXML
    private ComboBox<String> postCode;

    @FXML
    private ComboBox<String> thana;

    @FXML
    private TextField shortAdrs;

    @FXML
    private TextArea ambuDetails;

    @FXML
    private Label st;


    @FXML
    void callAmbulanceMethod(ActionEvent event) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/example/hospital_manament/" +
                "Records/Ambulance"));
        String[] x = {};
        while (true) {
            String s = br.readLine();
            if (s==null) break;
             x = s.split("\n");
        }
        br.close();

        //Another file reading
        BufferedReader bry = new BufferedReader(new FileReader("src/main/resources/com/example/" +
                "hospital_manament/Records/Ambulance log"));
        String check = "";
        while (true) {
            String s = bry.readLine();
            if (s==null) break;
            String[] y = s.split("~");
            if (loginController.identity.equals(y[1])){
                check = y[1];
            }
        }//while loop end
        bry.close();

        if (thana.getValue()==null || postCode.getValue()==null || shortAdrs.getText().isEmpty()){
            st.setText("Please Fill up Every Field");
        }
        else  if (x.length==1){
            st.setText("Currently, Ambulance is occupied");
        }
        else {
            //read ambu history
           if (check.equals(loginController.identity))
                st.setText("You have Due Please clear Payment");
           else {
               st.setText("Ambulance Request has Sent see the Box");
               ambuDetails.setText("Ambulance is ready to go. Call Driver if you need. Diver Phone No.: 01365248988");
               FileWriter fw = new FileWriter("src/main/resources/com/example/hospital_manament/" +
                       "Records/Ambulance",true);
               BufferedWriter bw = new BufferedWriter(fw);
               bw.write(name.getText()+"~"+mail.getText()+"~"+phnNo.getText()+"~"+registeredAdrs.getText()
                       +"~"+ thana.getValue()+"~"+postCode.getValue()+"~"+shortAdrs.getText()+"~"+ambuPrice+"\n");
               bw.close();
               fw.close();
           }

        }//outer else

    }//Method Ends




    @FXML
    void clearMethod(ActionEvent event) {

    }

    public void setInfo() {
        name.setText(loginController.sName);
        mail.setText(loginController.identity);
        phnNo.setText(loginController.sPhoneNumber);
        registeredAdrs.setText(loginController.sLocation);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thana.getItems().addAll(thaNa);
        postCode.getItems().addAll(postCd);
    }
}
