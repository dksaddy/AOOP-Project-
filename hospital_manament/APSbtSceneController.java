package com.example.hospital_manament;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class APSbtSceneController implements Initializable {

    private Stage stage;

    @FXML
    private TableColumn<AppoPatient, String> AppoName;

    @FXML
    private TableColumn<AppoPatient, String> AppoMail;

    @FXML
    private TableColumn<AppoPatient, String> AppoHealthIssue;

    @FXML
    private TableColumn<AppoPatient, String> AppoPM;

    @FXML
    private TableColumn<AppoPatient, String> AppoBill;


    @FXML
    private TextArea prescription;

    @FXML
    private TableView<AppoPatient> tv;

    @FXML
    private Label status;



    @FXML
    void backMethod(ActionEvent event) throws IOException {
        FXMLLoader ld = new FXMLLoader(getClass().getResource("doctorHome.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(ld.load());
        stage.setScene(scene);
        stage.setTitle("Doctor's Home");
        stage.show();
    }



    @FXML
    void clearMethod(ActionEvent event) {
        //Some Work Left "EASY".
    }


    @FXML
    void updateMethod(ActionEvent event) {
        status.setText("");

        ObservableList<AppoPatient> allPeople, selected;
        allPeople = tv.getItems();
        selected = tv.getSelectionModel().getSelectedItems();

        if (selected.size()==0) {
            status.setText("Select Patient for Updating Medicine");
        }
        else if (prescription.getText().equals("")) {
            status.setText("Write Prescription Because \"Prescribed Medicine\" cell is Empty");
        }
        else
        {
            String t = String.valueOf(selected);
            String t1 = t.replace("[","").replace("]", "");
            String[] arr = t1.split("~");

            tv.getItems().add(new AppoPatient(arr[0], arr[1], arr[2], prescription.getText(),arr[4]));

            selected.forEach(allPeople::remove);

            tv.getSelectionModel().clearSelection();
            prescription.setText("");
        }

    }//Method Ends



    @FXML
    void sendPresMethod(ActionEvent event) throws IOException {

        ObservableList<AppoPatient> allPeople, selected;
        allPeople = tv.getItems();
        selected = tv.getSelectionModel().getSelectedItems();

        String t = String.valueOf(selected);
        String t1 = t.replace("[","").replace("]","");

        if (selected.size()==0)
        {
            status.setText("Select Patient From Table");
        }
        else if (t1.contains("~~"))
        {
            status.setText("Write Prescription");
        }
        else if (allPeople.size()==1)
        {
                FileWriter fw = new FileWriter("src/main/resources/com/example/hospital_manament/" +
                        "Records/Runing Patients",true);
                BufferedWriter bw = new BufferedWriter(fw);
                String mod = t1.replaceFirst("\n","");
                bw.write(mod);
                bw.close();
                fw.close();

                tv.getItems().clear();

                FileWriter fwn = new FileWriter("src/main/resources/com/example/hospital_manament/" +
                        "Records/Appointed Patients");
                BufferedWriter bwn = new BufferedWriter(fwn);
                String s = String.valueOf(allPeople);
                String s1 = s.replace("[","").replace("]","");
                bwn.write(s1);
                bwn.close();
                fwn.close();

                tv.getSelectionModel().clearSelection();
                prescription.setText("");

        }
        else
        {
                FileWriter fw = new FileWriter("src/main/resources/com/example/hospital_manament/" +
                        "Records/Runing Patients", true);
                BufferedWriter bw = new BufferedWriter(fw);
                String mod = t1.replaceFirst("\n","");
                bw.write(mod);
                bw.close();
                fw.close();

                String[] x = t1.split("~");

                selected.forEach(allPeople::remove);

                ArrayList<Patient1> list = new ArrayList<>();
                FileReader fr = new FileReader("src/main/resources/com/example/hospital_manament/" +
                        "Records/Appointed Patients");
                BufferedReader br = new BufferedReader(fr);
                while (true) {
                    String s = br.readLine();
                    if (s==null) break;
                    String[] w = s.split("~");
                    list.add(new Patient1(w[0], w[1], w[2], w[3], w[4], w[5], w[6], w[7], w[8]));
                }//while end

            Iterator<Patient1> it = list.iterator();
            while (it.hasNext()){
                Patient1 p = it.next();
                if (x[1].equals(p.e))
                {
                   it.remove();
                   String f = String.valueOf(list);
                   String f1 = f.replace("[","").replace("]","");
                   FileWriter fwn = new FileWriter("src/main/resources/com/example/hospital_manament/" +
                           "Records/Appointed Patients");
                   BufferedWriter bwn = new BufferedWriter(fwn);
                   bwn.write(f1);
                   bwn.close();
                   fwn.close();
                }
            }//Iterator Ends

            tv.getSelectionModel().clearSelection();
            prescription.setText("");


        }//else End

    }//Method End





    void readingAppoFile() throws IOException {

        FileReader fr = new FileReader("src/main/resources/com/example/hospital_manament/" +
                "Records/Appointed Patients");
        BufferedReader br = new BufferedReader(fr);
        while (true) {
            String s = br.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            tv.getItems().add(new AppoPatient(x[0], x[1], x[7], "", x[8]));
        }
        br.close();
        fr.close();

    }//Method end


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            readingAppoFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AppoName.setCellValueFactory(new PropertyValueFactory<>("n"));
        AppoMail.setCellValueFactory(new PropertyValueFactory<>("m"));
        AppoHealthIssue.setCellValueFactory(new PropertyValueFactory<>("hi"));
        AppoPM.setCellValueFactory(new PropertyValueFactory<>("pm"));
        AppoBill.setCellValueFactory(new PropertyValueFactory<>("bills"));
    }


}//Class Ends.
