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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class APbtSceneController implements Initializable {

    private Stage stage;

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
    private TableColumn<AddoForRelease, String> ageCol;

    @FXML
    private TableColumn<AddoForRelease, String> bgCol;

    @FXML
    private TableColumn<AddoForRelease, String> billCol;

    @FXML
    private TableColumn<AddoForRelease, String> deseaseCol;

    @FXML
    private TableColumn<AddoForRelease, String> durationCol;

    @FXML
    private TableColumn<AddoForRelease, String> genderCol;

    @FXML
    private TableColumn<AddoForRelease, String> mailCol;

    @FXML
    private TableColumn<AddoForRelease, String> nameCol;

    @FXML
    private TableColumn<AddoForRelease, String> phnCol;

    @FXML
    private TableColumn<AddoForRelease, String> roomCol;

    @FXML
    private Label st;

    @FXML
    private TableView<AddoForRelease> tv;




    @FXML
    void releaseMethod(ActionEvent event) throws IOException {
        ObservableList<AddoForRelease> allPeople, selected;

        allPeople = tv.getItems();
        selected = tv.getSelectionModel().getSelectedItems();

        String t = String.valueOf(selected).replace("[","").replace("]","");
        String[] x = t.split("~");

        if (selected.size()==0){
            st.setText("Please Select Patient");
        }
        else if (allPeople.size()==1) {
            if (x[8].equals("0")){
                tv.getItems().clear();
                BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/com/example/" +
                        "hospital_manament/Records/Admited Patient List"));
                String j = String.valueOf(allPeople);
                String j1 = j.replace("[","").replace("]","");
                bw.write(j1);
                bw.close();

                tv.getSelectionModel().clearSelection();
                st.setText("Patient Released Successfully");

            }else st.setText("Payment Due");

        }
        else {
            if (x[8].equals("0")){
                selected.forEach(allPeople::remove);

                BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/com/example/" +
                        "hospital_manament/Records/Admited Patient List"));
                bw.write(String.valueOf(allPeople).replace("[","").replace("]","")
                        .replace(", ",""));
                bw.close();


                tv.getSelectionModel().clearSelection();
                st.setText("Patient Released Successfully");

            }else st.setText("Payment Due");

        }//else end


    }



    void reading() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/example/" +
                "hospital_manament/Records/Admited Patient List"));
        while (true) {
            String s = br.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            tv.getItems().addAll(new AddoForRelease(x[1],x[0],x[2], x[3],x[5],x[4], x[9],x[6], x[7],x[8]));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            reading();
        }catch (IOException e) {
            e.printStackTrace();
        }

        nameCol.setCellValueFactory(new PropertyValueFactory<>("n"));
        mailCol.setCellValueFactory(new PropertyValueFactory<>("e"));
        phnCol.setCellValueFactory(new PropertyValueFactory<>("p"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("a"));

        genderCol.setCellValueFactory(new PropertyValueFactory<>("g"));
        bgCol.setCellValueFactory(new PropertyValueFactory<>("b"));
        deseaseCol.setCellValueFactory(new PropertyValueFactory<>("d"));

        roomCol.setCellValueFactory(new PropertyValueFactory<>("r"));
        durationCol.setCellValueFactory(new PropertyValueFactory<>("day"));
        billCol.setCellValueFactory(new PropertyValueFactory<>("due"));


    }



}
