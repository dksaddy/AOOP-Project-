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

public class HRbtSceneController implements Initializable {

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
    private TableColumn<HelthReportForTable, String> ageCol;

    @FXML
    private TableColumn<HelthReportForTable, String> bgCol;

    @FXML
    private TableColumn<HelthReportForTable, String> mailCol;

    @FXML
    private TableColumn<HelthReportForTable, String> nameCol;

    @FXML
    private TableColumn<HelthReportForTable, String> recoCol;

    @FXML
    private TableColumn<HelthReportForTable, String> sideEffeCol;

    @FXML
    private TableColumn<HelthReportForTable, String> sufeEffeDetaCol;

    @FXML
    private TableView<HelthReportForTable> tv;



    void reding() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/example/" +
                "hospital_manament/Records/Health Report"));
        while (true) {
            String s = br.readLine();
            if (s==null) break;

            String[] x = s.split("~");
            tv.getItems().addAll(new HelthReportForTable(x[0],x[1],x[2], x[3],x[4],x[5], x[6]));
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            reding();
        } catch (IOException e) {
            e.printStackTrace();
        }

        nameCol.setCellValueFactory(new PropertyValueFactory<>("n"));
        mailCol.setCellValueFactory(new PropertyValueFactory<>("e"));
        bgCol.setCellValueFactory(new PropertyValueFactory<>("bg"));

        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        recoCol.setCellValueFactory(new PropertyValueFactory<>("rev"));
        sideEffeCol.setCellValueFactory(new PropertyValueFactory<>("se"));

        sufeEffeDetaCol.setCellValueFactory(new PropertyValueFactory<>("sed"));

    }
}
