package com.example.hospital_manament;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;


public class ASbtSceneController {

    private Stage stage;



    @FXML
    void backMethod(ActionEvent event) throws IOException {
            FXMLLoader ld = new FXMLLoader(getClass().getResource("doctorHome.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(ld.load());
            stage.setScene(scene);
            stage.setTitle("Doctor's Home");
            stage.show();
    }//Method Ends

    @FXML
    private Label mail;

    @FXML
    private Label name;

    @FXML
    private Label phn;

    @FXML
    private Label postCode;

    @FXML
    private Label rAdrs;

    @FXML
    private Label sAdrs;

    @FXML
    private Label st;

    @FXML
    private Label thana;



    @FXML
    void updateMethod(ActionEvent event) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/example/" +
                "hospital_manament/Records/Ambulance"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/com/example/" +
                "hospital_manament/Records/Ambulance log",true));

        while (true) {
            String s = br.readLine();
            if (s==null) break;
            bw.write(s+"\n");
        }
        bw.close();
        br.close();

        BufferedWriter bw1 = new BufferedWriter(new FileWriter("src/main/resources/com/example/" +
                "hospital_manament/Records/Ambulance"));
        bw1.write("");
        bw1.close();
        st.setText("Ambulance Booking DataBase has Updated");
    }//Method Ends


    @FXML
    void ambuServiceHistoryMethod(ActionEvent event) throws IOException {
        FXMLLoader ld = new FXMLLoader(getClass().getResource("ambulanceLog.fxml"));
        Scene scene = new Scene(ld.load());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Ambulance Log Page");
        stage.show();
    }






    //        ********  ***************    Self Created Method   ****************  ********
    public void readForSet() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/example/" +
                "hospital_manament/Records/Ambulance"));
        while (true) {
            String s = br.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            setInfoMethod(x[0], x[2], x[1], x[3], x[4], x[5], x[6]);
        }
        br.close();
    }//Method End
    public void setInfoMethod(String a, String b, String c, String d, String e, String f, String g) {
        name.setText(a); phn.setText(b); mail.setText(c);
        rAdrs.setText(d); thana.setText(e); postCode.setText(f);
        sAdrs.setText(g);
    }//Method end


}
