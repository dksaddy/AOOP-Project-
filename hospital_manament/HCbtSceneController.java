package com.example.hospital_manament;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;

public class HCbtSceneController {

    private Stage stage;
    String pullSideEffect = "";

    @FXML
    private Label name;

    @FXML
    private Label mail;

    @FXML
    private Label bg;

    @FXML
    private Label age;

    @FXML
    private Label rs;

    @FXML
    private Label ses;



    @FXML
    private CheckBox recoverCH;

    @FXML
    private CheckBox recoverCH1;

    @FXML
    private CheckBox sideEffectCH;

    @FXML
    private CheckBox sideEffectCH1;

    @FXML
    private TextArea sideEfectNote;



    @FXML
    private Label st;



    @FXML
    void anotherAppointmentMethod(ActionEvent event) throws IOException {
        BufferedReader br1 = new BufferedReader(new FileReader("src/main/resources/com/example/" +
                "hospital_manament/Records/Runing Patients"));
        String RmailCheck = "";
        while (true) {
            String s = br1.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            if (x[1].equals(loginController.identity)){
                RmailCheck = x[1];
                System.out.println(RmailCheck);
            }
        }//while ends

        FileReader fr = new FileReader("src/main/resources/com/example/hospital_manament/" +
                "Records/Appointed Patients");
        BufferedReader br = new BufferedReader(fr);
        String mailCheck = "";
        while (true) {
            String s = br.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            if (x[1].equals(loginController.identity)){
                mailCheck = x[1];
                System.out.println(mailCheck);
            }

        }//while ends

        if (loginController.identity.equals(mailCheck)) {
            st.setText("You Are Appointed");
        }else if (loginController.identity.equals(RmailCheck)) {
            st.setText("You are not Released yet");
        }else {
            FXMLLoader ld = new FXMLLoader(getClass().getResource("appointmentForm.fxml"));
            Scene scene = new Scene(ld.load());
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Appointment Form");
            stage.show();
        }
    }//Method End



    @FXML
    void autoFillMethod(ActionEvent event) {
        if (rs.getText().isEmpty()){
           st.setText("Check if you recover or not");
        }
        else if (ses.getText().isEmpty()){
            st.setText("Check if you noticed any Side-Effect");
        }else {
            if (ses.getText().equals("YES")){
                if (sideEfectNote.getText().isEmpty()){
                    st.setText("Write down Side-Effect in the Text Area");
                } else if (sideEfectNote.getText().contains("\n\n")){
                    st.setText("Do not use Double ENTER KEY");
                } else if (sideEfectNote.getText().contains("|")){
                    st.setText("Please Don't use this \"|\" sign");
                } else {
                    Filler();
                    pullSideEffect = sideEfectNote.getText();
                    Restore1();
                    st.setText("Click \"Send Health Report\" Button");
                }
            }else {
               Filler();
               pullSideEffect = sideEfectNote.getText();
               Restore1();
                st.setText("Click \"Send Health Report\" Button");
            }//else end

        }//else

    }//Method


    @FXML
    void sendReportMethod(ActionEvent event) throws IOException {
        if (name.getText().isEmpty() || mail.getText().isEmpty()){
            st.setText("Identification Field is Empty");
        } else {

            String ignoreEnter = pullSideEffect.replace("\n","|");
            String save = name.getText() +"~"+ mail.getText() +"~"+ bg.getText() +"~"+ age.getText() +"~"+
                    rs.getText() +"~"+ ses.getText() +"~"+ ignoreEnter +"\n";

            FileReader fr = new FileReader("src/main/resources/com/example/hospital_manament/" +
                    "Records/Health Report");
            BufferedReader br = new BufferedReader(fr);
            String checker = "";
            while (true) {
                String s = br.readLine();
                if (s==null) break;
                String[] x = s.split("~");
                if (mail.getText().equals(x[1]))
                    checker = x[1];

            }//while end
            br.close();
            fr.close();

            if (checker.equals(mail.getText())){
               st.setText("You have already sent Report");
            }
            else
            {
                String msg = """
                    This Report will be saved & further Changes might not Possible\s
                     If you sure click "OK"\s
                     Unless click "CANCEL" & do Changes you might want to make""";

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,msg, ButtonType.OK, ButtonType.CANCEL);
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.OK){
                    FileWriter fw = new FileWriter("src/main/resources/com/example/hospital_manament/" +
                            "Records/Health Report",true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(save);
                    bw.close();
                    fw.close();
                    alert.close();
                    Restore();
                    Restore1();
                    rs.setText("");
                    ses.setText("");
                    st.setText("Report Sent Successfully Click \"Back\"");
                }
                if (alert.getResult() == ButtonType.CANCEL){
                    alert.close();
                    Restore();
                    Restore1();
                    rs.setText("");
                    ses.setText("");
                }

            }//else End

        }//Else end

    }//Method Ends

    @FXML
    void clearMethod(ActionEvent event) {
            Restore();
            Restore1();
            rs.setText("");
            ses.setText("");
    }

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
    void recoverCHmethod(ActionEvent event) {
        if (recoverCH.isSelected()){
            Restore();
            rs.setText("YES");
            recoverCH1.setSelected(false);
        } else rs.setText("");
    }
    @FXML
    void recoverCHmethod1(ActionEvent event) {
        if (recoverCH1.isSelected()){
            Restore();
            rs.setText("NO");
            recoverCH.setSelected(false);
        } else rs.setText("");
    }



    @FXML
    void sideEffectCHmethod(ActionEvent event) {
        if (sideEffectCH.isSelected()){
            Restore();
            ses.setText("NO");
            sideEffectCH1.setSelected(false);
            sideEfectNote.setText("No Effect at all");
            sideEfectNote.setEditable(false);
        } else ses.setText("");
    }
    @FXML
    void sideEffectCHmethod1(ActionEvent event) {
        if (sideEffectCH1.isSelected()){
            Restore();
            ses.setText("YES");
            sideEffectCH.setSelected(false);
            sideEfectNote.setEditable(true);
        } else ses.setText("");
    }


    //Self Created Method.
    void Filler() {
        name.setText(loginController.sName);
        mail.setText(loginController.identity);
        bg.setText(loginController.sBloodGroup);
        age.setText(loginController.calculateAge(loginController.sDob));
    }

    void Restore() {
        name.setText("");
        mail.setText("");
        bg.setText("");
        age.setText("");
    }

    void Restore1() {
        sideEfectNote.setText("");
        recoverCH.setSelected(false);
        recoverCH1.setSelected(false);
        sideEffectCH.setSelected(false);
        sideEffectCH1.setSelected(false);
    }

}
