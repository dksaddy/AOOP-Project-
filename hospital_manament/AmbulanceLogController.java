package com.example.hospital_manament;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AmbulanceLogController implements Initializable {

    private Stage stage;

    @FXML
    void backMethod(ActionEvent event) throws IOException {
        FXMLLoader ld = new FXMLLoader(getClass().getResource("ambulanceService.fxml"));
        Scene scene = new Scene(ld.load());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Ambulance Service Page");
        stage.show();
    }

    @FXML
    private TableColumn<AmbuLog2, String> billCol;

    @FXML
    private TableColumn<AmbuLog2, String> mailCol;

    @FXML
    private TableColumn<AmbuLog2, String> nameCol;

    @FXML
    private TableColumn<AmbuLog2, String> pcCol;

    @FXML
    private TableColumn<AmbuLog2, String> phnCol;

    @FXML
    private TableColumn<AmbuLog2, String> regAdrsCol;

    @FXML
    private TableColumn<AmbuLog2, String> sAdrsCol;

    @FXML
    private TableColumn<AmbuLog2, String> thanaCol;

    @FXML
    private TableView<AmbuLog2> tv;



    void reading() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/example/" +
                "hospital_manament/Records/Ambulance log"));
        while (true) {
            String s= br.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            tv.getItems().add(new AmbuLog2(x[0], x[1], x[2], x[3], x[4], x[5], x[6], x[7]));
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            reading();
        } catch (IOException e) {
            e.printStackTrace();
        }

        nameCol.setCellValueFactory(new PropertyValueFactory<>("n"));
        mailCol.setCellValueFactory(new PropertyValueFactory<>("e"));
        phnCol.setCellValueFactory(new PropertyValueFactory<>("p"));
        regAdrsCol.setCellValueFactory(new PropertyValueFactory<>("ra"));

        thanaCol.setCellValueFactory(new PropertyValueFactory<>("t"));
        pcCol.setCellValueFactory(new PropertyValueFactory<>("pc"));
        sAdrsCol.setCellValueFactory(new PropertyValueFactory<>("sa"));
        billCol.setCellValueFactory(new PropertyValueFactory<>("b"));

    }



}
