package com.example.hospital_manament;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class AFbtSceneController implements Initializable {

    private Stage stage;
    static String visitFee = "1050";

    @FXML
    private TableView<Doctor1> tv;

    @FXML
    private TableColumn<Doctor1, String> nameCol;

    @FXML
    private TableColumn<Doctor1, String> phoneCol;

    @FXML
    private TableColumn<Doctor1, String> emailCol;

    @FXML
    private TableColumn<Doctor1, String> deptCol;
    //Table View Injection End.

    @FXML
    private TextField Helthissue;

    @FXML
    private TextField dMail;

    @FXML
    private TextField dName;

    @FXML
    private TextField dept;

    @FXML
    private TextField pAdrs;

    @FXML
    private TextField pMail;

    @FXML
    private TextField pName;

    @FXML
    private TextField pPhone;

    @FXML
    private Label pushBtStatus;

    @FXML
    private Label bookAppBtStatus;

    // Injection ends. Methods are down here.


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
    void bookAppointMethod(ActionEvent event) throws IOException {
        bookAppBtStatus.setText("");

        BufferedReader br1 = new BufferedReader(new FileReader("src/main/resources/com/example/" +
                "hospital_manament/Records/Runing Patients"));
        String RmailCheck = "";
        while (true) {
            String s = br1.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            if (x[1].equals(pMail.getText())){
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
           if (x[1].equals(pMail.getText())){
               mailCheck = x[1];
               System.out.println(mailCheck);
           }

        }//while ends

        String store;

        if (pName.getText().isEmpty() || pPhone.getText().isEmpty() || pAdrs.getText().isEmpty() ||
                dName.getText().isEmpty() || dMail.getText().isEmpty() || dept.getText().isEmpty())
        {
           bookAppBtStatus.setText("Form Fields are Empty!");
        }
        else if (mailCheck.equals(pMail.getText()))
        {
            bookAppBtStatus.setText("Appointment already Booked!");
        }
        else if (RmailCheck.equals(pMail.getText()))
        {
            bookAppBtStatus.setText("You aren't released yet");
        }
        else {
            ArrayList<Patient1> list = new ArrayList<>();
            list.add(new Patient1(pName.getText(), pMail.getText(), pPhone.getText(), pAdrs.getText(),
            dName.getText(), dMail.getText(),dept.getText(), Helthissue.getText(), visitFee));
            store = String.valueOf(list);
            String store1 = store.replace("[","").replace("]","");
            FileWriter fw = new FileWriter("src/main/resources/com/example/hospital_manament/" +
                    "Records/Appointed Patients", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(store1);
            bw.close();
            fw.close();
            restore();
            bookAppBtStatus.setText("Appointment Booked");
        }

    }


    @FXML
    void clearMethod(ActionEvent event) {
    restore();
    Helthissue.setText("");
    }


    @FXML
    void pushInfoMethod(ActionEvent event) throws IOException {
        pushBtStatus.setText("");

        ObservableList<Doctor1> select;
        select = tv.getSelectionModel().getSelectedItems();

        if (pMail.getText().isEmpty() || Helthissue.getText().isEmpty()) {
            pushBtStatus.setText("Fields Are Empty");
        }
        else if (select.size()==0){
            pushBtStatus.setText("Please Select Doctor From Table");
        }
        else {
            ArrayList<Patient> list = new ArrayList<>();

            FileReader fr = new FileReader("src/main/resources/com/example/hospital_manament/" +
                    "Records/Patient's Record");
            BufferedReader br = new BufferedReader(fr);
            while (true) {

                String s = br.readLine();
                if (s==null) break;
                String[] x = s.split("~");
                list.add(new Patient(x[0], x[5], x[4], x[6]));
            }//while End
            fr.close();
            br.close();

            Iterator<Patient> it = list.iterator();
            while (it.hasNext()){
                Patient p = it.next();
                if (p.email.equals(pMail.getText()))
                {
                    //System.out.println("Okay");
                    pName.setText(p.name);
                    pPhone.setText(p.phNo);
                    pAdrs.setText(p.address);
                    String r = String.valueOf(select);
                    String r1 = r.replace("[","").replace("]","");
                    String[] r3 = r1.split("~");
                    dName.setText(r3[0]);
                    dMail.setText(r3[2]);
                    dept.setText(r3[3]);
                    pushBtStatus.setText("");
                    break;
                }else {
                    pushBtStatus.setText("Email does not exist!");
                }
            }//Iterator Ends

        }//Else End here

    }//Push Method end


    //Reading Doctor's File Record  to set on Table View.
    void readingF() throws IOException {
        FileReader fr = new FileReader("src/main/resources/com/example/hospital_manament/" +
                "Records/Doctor's Record");
        BufferedReader br = new BufferedReader(fr);
        while (true) {
            String s = br.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            tv.getItems().add( new Doctor1(x[0], x[5], x[6], x[4]));
        }
        fr.close();
        br.close();
    }//Method End

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            readingF();
        } catch (IOException e) {
            e.printStackTrace();
        }

        nameCol.setCellValueFactory(new PropertyValueFactory<>("n"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("p"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("e"));
        deptCol.setCellValueFactory(new PropertyValueFactory<>("d"));

    }//Initialize able Method End

    //Self created Method for restoring fields
    void restore() {
        pName.setText("");
        pPhone.setText("");
        pAdrs.setText("");
        dName.setText("");
        dMail.setText("");
        dept.setText("");
    }

    public void setMail(String id) {
       pMail.setText(id);
    }

}


