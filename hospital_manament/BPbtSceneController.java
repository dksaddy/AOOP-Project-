package com.example.hospital_manament;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class BPbtSceneController implements Initializable {

    private Stage stage;
    String[] pmMethodName = {"Mobile Banking", "Credit Card", "Online Banking"};


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
    private TextField docVisit;

    @FXML
    private TextField bedBill;

    @FXML
    private TextField ambBill;

    @FXML
    private TextField total;

    @FXML
    private TextField due;

    @FXML
    private ComboBox<String> pmMethod;

    @FXML
    private TextField amount;


    @FXML
    private Label st;




    @FXML
    void proceedMethod(ActionEvent event) throws IOException {

        if (amount.getText().isEmpty() || pmMethod.getValue()==null){
            st.setText("Select Payment Method and Amount");
        }

        else {

            ArrayList<Payment> list = new ArrayList<>();

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/example/" +
                    "hospital_manament/Records/Payment"));

            String check = "";
            while (true) {
                String s = br.readLine();
                if (s==null) break;
                String[] x = s.split("~");
                list.add(new Payment(x[0],x[1]));
                if (loginController.identity.equals(x[0])){
                    check = x[0];
                }

            } //while end
            br.close();


            if (loginController.identity.equals(check))
            {
                double a = Double.parseDouble(amount.getText());
                double d = Double.parseDouble(due.getText());

                if (a>d) {
                    st.setText("big money");
                }else {
                    d -= a;
                    due.setText(String.valueOf(d));

                    Iterator<Payment> it = list.iterator();
                    while (it.hasNext()) {
                        Payment p = it.next();
                        if (p.email.equals(loginController.identity)) {
                            p.due = due.getText();
                            if (d==0){
                                ambulanceBillNil();
                                bedBillNil();
                                appointmentBillNil();
                               it.remove();
                            }
                            BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/com/example/" +
                                    "hospital_manament/Records/Payment"));
                            bw.write(String.valueOf(list).replace("[","").replace("]","")
                                    .replace(", ",""));
                            bw.close();
                        }
                    }//Iterator Ends
                }

                amount.setText("");
                pmMethod.getSelectionModel().clearSelection();
            }//email existing checking in payment file er IF
            else {
                suddenlyNeeded();
                amount.setText("");
                pmMethod.getSelectionModel().clearSelection();
            }

        }//else ends

    }//Method ends


    public void suddenlyNeeded() throws IOException {

        double t = Double.parseDouble(total.getText());
        double a = Double.parseDouble(amount.getText());

        if (a>t) {
            st.setText("over Payment");
        } else {
            double d = t - a;

            if (d==0){
                ambulanceBillNil();
                bedBillNil();
                appointmentBillNil();
            }
            due.setText(String.valueOf(d));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/com/example/" +
                    "hospital_manament/Records/Payment",true));
            bw.write(loginController.identity+"~"+due.getText()+"\n");
            bw.close();

        }



    }//Method ends




    //           **************************  self created Method for setting up ***************************

    void readForDocVisit() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/example/" +
                "hospital_manament/Records/Runing Patients"));
        while (true) {
            String s = br.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            if (loginController.identity.equals(x[1])){
                docVisit.setText(x[4]);
            }

        }//while end
        br.close();

    }//Method ends
    void readForHospitalBed() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/example/" +
                "hospital_manament/Records/Admited Patient List"));
        while (true) {
            String s = br.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            if (loginController.identity.equals(x[0])){
                bedBill.setText(x[8]);
            }

        }//while end

    }//Method ends
    void readForAmbulance() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/example/" +
                "hospital_manament/Records/Ambulance log"));
        while (true) {
            String s = br.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            if (loginController.identity.equals(x[1])){
                 ambBill.setText(x[7]);
            }

        }//while end

    }//Method ends
    void totalCalculation() {
       double dv = Double.parseDouble(docVisit.getText());
       double hb = Double.parseDouble(bedBill.getText());
       double as = Double.parseDouble(ambBill.getText());

        double sum = dv + hb + as;

        total.setText(String.valueOf(sum));
    }


    void readForDue() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/example/" +
                "hospital_manament/Records/Payment"));
        while (true) {
            String s = br.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            if (loginController.identity.equals(x[0])){
                due.setText(x[1]);
            }

        }//while end

    }//Method ends


    //  ********************  self created Method for setting up   end here ***********************


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pmMethod.getItems().addAll(pmMethodName);

        try {
            readForDocVisit();
            readForHospitalBed();
            readForAmbulance();
            readForDue();
        } catch (IOException e) {
            e.printStackTrace();
        }

        totalCalculation();

    }

    //**************************************  MORE  METHOD   FOR  PAYMENT  STUFF    ****************************

    void appointmentBillNil() throws IOException {
        ArrayList<Appo2> appo2List = new ArrayList<>();

        BufferedReader dbr = new BufferedReader(new FileReader("src/main/resources/com/example/" +
                "hospital_manament/Records/Runing Patients"));
        while (true) {
            String s = dbr.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            appo2List.add(new Appo2(x[0],x[1],x[2],x[3],x[4]));
        }//while end
        dbr.close();

        Iterator<Appo2> it = appo2List.iterator();
        while (it.hasNext()) {
            Appo2 p = it.next();
            if (p.pMail.equals(loginController.identity)){
                p.bill = "0";
                BufferedWriter dbw = new BufferedWriter(new FileWriter("src/main/resources/com/example/" +
                        "hospital_manament/Records/Runing Patients"));
                dbw.write(String.valueOf(appo2List).replace("[","").replace("]","")
                        .replace(", ",""));
                dbw.close();
            }
        }//iterator end

    }//Method End

    void bedBillNil() throws IOException {
        ArrayList<Addo2> addo2List = new ArrayList<>();

        BufferedReader dbr = new BufferedReader(new FileReader("src/main/resources/com/example/" +
                "hospital_manament/Records/Admited Patient List"));
        while (true) {
            String s = dbr.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            addo2List.add(new Addo2(x[0],x[1], x[2], x[3], x[4], x[5], x[6],x[7], x[8],x[9]));
        }//while end
        dbr.close();

        Iterator<Addo2> it = addo2List.iterator();
        while (it.hasNext()) {
            Addo2 p = it.next();
            if (p.pMail.equals(loginController.identity)){
                p.bill = "0";
                BufferedWriter dbw = new BufferedWriter(new FileWriter("src/main/resources/com/example/" +
                        "hospital_manament/Records/Admited Patient List"));
                dbw.write(String.valueOf(addo2List).replace("[","").replace("]","")
                        .replace(", ",""));
                dbw.close();
            }
        }//iterator end

    }//Method End

    void ambulanceBillNil() throws IOException {
        ArrayList<AmbuLog> list = new ArrayList<>();

        BufferedReader dbr = new BufferedReader(new FileReader("src/main/resources/com/example/" +
                "hospital_manament/Records/Ambulance log"));
        while (true) {
            String s = dbr.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            list.add(new AmbuLog(x[0],x[1],x[2],x[3],x[4],x[5],x[6],x[7]));
        }//while end
        dbr.close();

        Iterator<AmbuLog> it = list.iterator();
        while (it.hasNext()) {
            AmbuLog p = it.next();
            if (p.mail.equals(loginController.identity)){
                it.remove();
                BufferedWriter dbw = new BufferedWriter(new FileWriter("src/main/resources/com/example/" +
                        "hospital_manament/Records/Ambulance log"));
                dbw.write(String.valueOf(list).replace("[","").replace("]","")
                        .replace(", ",""));
                dbw.close();
            }
        }//iterator end

    }//Method End


}
