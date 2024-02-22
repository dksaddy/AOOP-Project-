package com.example.hospital_manament;

import javafx.collections.FXCollections;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class RPbtSceneController implements Initializable {


    private Stage stage;

    @FXML
    private TableView<Patient2> tv;

    @FXML
    private TableColumn<Patient2, String> nameCol;

    @FXML
    private TableColumn<Patient2, String> mailCol;

    @FXML
    private TableColumn<Patient2, String> hcCol;

    @FXML
    private TableColumn<Patient2, String> medCol;

    @FXML
    private TableColumn<Patient2, String> billCol;


    @FXML
    private Label st;



    @FXML
    void backMethod(ActionEvent event) throws IOException {
        FXMLLoader ld = new FXMLLoader(getClass().getResource("doctorHome.fxml"));
        Scene scene = new Scene(ld.load());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Doctor's Home");
        stage.show();
    }

    @FXML
    void reliaseMethod(ActionEvent event) throws IOException {
        ObservableList<Patient2> allPeople, selected;

        allPeople = tv.getItems();
        selected = tv.getSelectionModel().getSelectedItems();

        String t = String.valueOf(selected).replace("[","").replace("]","");
        String[] x = t.split("~");

        if (selected.size()==0){
            st.setText("Please Select Patient");
        }
        else if (allPeople.size()==1) {
            if (x[4].equals("0\n")){
               tv.getItems().clear();
                BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/com/example/" +
                        "hospital_manament/Records/Runing Patients"));
                String j = String.valueOf(allPeople);
                String j1 = j.replace("[","").replace("]","");
                bw.write(j1);
                bw.close();
            }else st.setText("Payment Due");
        }
        else {
            if (x[4].equals("0\n")){
                selected.forEach(allPeople::remove);

                BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/com/example/" +
                        "hospital_manament/Records/Runing Patients"));
                bw.write(String.valueOf(allPeople).replace("[","").replace("]","")
                                                  .replace(", ",""));
                bw.close();
            }else st.setText("Payment Due");

        }//else end

    }//Method Ends






    void readingRPFile() throws IOException {

        FileReader fr = new FileReader("src/main/resources/com/example/hospital_manament/" +
                "Records/Runing Patients");
        BufferedReader br = new BufferedReader(fr);
        while (true) {
            String s = br.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            tv.getItems().add(new Patient2(x[0], x[1], x[2], x[3], x[4]));
        }
        br.close();
        fr.close();

    }//Method end


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            readingRPFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        nameCol.setCellValueFactory(new PropertyValueFactory<>("n"));
        mailCol.setCellValueFactory(new PropertyValueFactory<>("e"));
        hcCol.setCellValueFactory(new PropertyValueFactory<>("hc"));
        medCol.setCellValueFactory(new PropertyValueFactory<>("med"));
        billCol.setCellValueFactory(new PropertyValueFactory<>("bill"));

    }

}//Class End
