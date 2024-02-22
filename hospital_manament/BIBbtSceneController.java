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

public class BIBbtSceneController implements Initializable {

    private Stage stage;
    String[] daysCount = {"1", "2", "3", "4", "5", "6"};
    static String price = "";

    @FXML
    private TableView<bedIcuTabCLS> bedTv;

    @FXML
    private TableColumn<bedIcuTabCLS, String> bedCol;

    @FXML
    private TableView<bedIcuTabCLS1> icuTv;

    @FXML
    private TableColumn<bedIcuTabCLS1, String> icuCol;



    @FXML
    private TextField email;

    @FXML
    private TextField disease;

    @FXML
    private TextField pName;

    @FXML
    private TextField pPhone;

    @FXML
    private TextField pAdrs;

    @FXML
    private TextField pBG;

    @FXML
    private TextField pGen;
    @FXML
    private TextField roomNo;


    @FXML
    private ComboBox<String> days;

    @FXML
    private Label st;

    @FXML
    private Label st1;


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
    void bookRoomMethod(ActionEvent event) throws IOException {
        st1.setText("");

        FileReader fr = new FileReader("src/main/resources/com/example/hospital_manament/" +
                "Records/Admited Patient List");
        BufferedReader br = new BufferedReader(fr);
        String checker = "";
        while (true) {
            String s= br.readLine();
            if (s==null) break;
            String[] x = s.split("~");
            if (email.getText().equals(x[0]))
                checker = x[0];
        }//while end
        br.close();
        fr.close();


        if (days.getValue()==null){
            st1.setText("Select How many Days you need to Stay");
        }
        else if (roomNo.getText().isEmpty()){
            st1.setText("Room isn't selected yet");
        }
        else if (checker.equals(email.getText())) {
            st1.setText("You have already booked a room");
        }
        else
        {
            price = calculate(days.getValue(), roomNo.getText());
            FileWriter fw = new FileWriter("src/main/resources/com/example/hospital_manament/" +
                    "Records/Admited Patient List",true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(email.getText() + "~" + pName.getText() + "~" + pPhone.getText() + "~" +
                    pAdrs.getText() + "~" + pBG.getText() + "~" + pGen.getText() + "~" +
                    roomNo.getText() + "~" + days.getValue() + "~" + price + "~" +
                    disease.getText() + "\n");
            bw.close();
            fw.close();

            if (roomNo.getText().length()==3)
                hospitalRoomDelete();
            else ICURoomDelete();


        }

    }//Method end

    @FXML
    void clearMethod(ActionEvent event) {

    }



    @FXML
    void fillUpMethod(ActionEvent event) throws IOException {
        st.setText("");
        ObservableList<bedIcuTabCLS> takenHospitalBed;
        ObservableList<bedIcuTabCLS1>  takenIcuBed;
        String set = "";


        takenHospitalBed = bedTv.getSelectionModel().getSelectedItems();

        takenIcuBed = icuTv.getSelectionModel().getSelectedItems();

        if (disease.getText().isEmpty())
        {
            st.setText("Enter Health Condition or Disease");
        }
        else if (takenHospitalBed.size()==0 && takenIcuBed.size()==0)
        {
            st.setText("Select Room Type From Table");
        }
        else
        {
            String set1 = "";
            if (takenHospitalBed.size()==1) {
                set = String.valueOf(takenHospitalBed);
                set1 = set.replace("[","").replace("]","");
            }
            else if (takenIcuBed.size()==1) {
                set = String.valueOf(takenIcuBed);
                set1 = set.replace("[","").replace("]","");
            }

            ArrayList<Patient> list = new ArrayList<>();

            FileReader fr = new FileReader("src/main/resources/com/example/hospital_manament/" +
                    "Records/Patient's Record");
            BufferedReader br = new BufferedReader(fr);
            while (true) {

                String s = br.readLine();
                if (s==null) break;
                String[] x = s.split("~");
                list.add(new Patient(x[0], x[3], x[1], x[5], x[6]));

            }//while end
            br.close();
            fr.close();
            Iterator<Patient> it = list.iterator();
            while (it.hasNext()){
                Patient p = it.next();
                if (p.email.equals(email.getText()))
                {
                    //System.out.println("Okay");
                    pName.setText(p.name);
                    pPhone.setText(p.phNo);
                    pAdrs.setText(loginController.calculateAge(p.dob));
                    pBG.setText(p.bloodGr);
                    pGen.setText(loginController.sGender);
                   if (roomNo.getText().isEmpty()){
                       roomNo.setText(set1);
                   }else {
                       st.setText("You have already Assigned a room");
                   }
                    break;
                }//If end

            }//Iterator Ends

        }//else end

    }//Method End


    void readingHospitalBed() throws IOException {
        FileReader fr = new FileReader("src/main/resources/com/example/hospital_manament/" +
                "Records/Hospital Bed");
        BufferedReader br = new BufferedReader(fr);
        while (true) {
            String s =br.readLine();
            if (s==null) break;
            bedTv.getItems().add(new bedIcuTabCLS(s));
        }
    }

    void readingIcuBed() throws IOException {
        FileReader fr = new FileReader("src/main/resources/com/example/hospital_manament/" +
                "Records/ICU Bed");
        BufferedReader br = new BufferedReader(fr);
        while (true) {
            String s =br.readLine();
            if (s==null) break;
            icuTv.getItems().add(new bedIcuTabCLS1(s));
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            readingHospitalBed();
        } catch (IOException e) {
            e.printStackTrace();
        }

        bedCol.setCellValueFactory(new PropertyValueFactory<>("hd"));

        try {
            readingIcuBed();
        } catch (IOException e) {
            e.printStackTrace();
        }
        icuCol.setCellValueFactory(new PropertyValueFactory<>("icr"));

        days.getItems().addAll(daysCount);

    }//Initialize able End

    //Self Created Method
    public void setMail(String id){
        email.setText(id);
    }


    public String calculate(String d, String r) {
        int day = Integer.parseInt(d);
        int sum;
        if (r.length()==3){
            sum = day*2500;
        }else {
            sum = day*12500;
        }
        return String.valueOf(sum);
    }



    void hospitalRoomDelete() throws IOException {
        ArrayList<bedIcuTabCLS> list = new ArrayList<>();
        FileReader fr = new FileReader("src/main/resources/com/example/hospital_manament/" +
                "Records/Hospital Bed");
        BufferedReader br = new BufferedReader(fr);
        while (true) {
            String s = br.readLine();
            if (s==null) break;
            list.add(new bedIcuTabCLS(s));
        }
        fr.close();
        br.close();
        Iterator<bedIcuTabCLS> it = list.iterator();
        while (it.hasNext()) {
            bedIcuTabCLS p = it.next();
            if (p.getHd().equals(roomNo.getText())){
                it.remove();
                FileWriter fw = new FileWriter("src/main/resources/com/example/hospital_manament/" +
                        "Records/Hospital Bed");
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(String.valueOf(list).replace("[","").replace("]","")
                                            .replace(", ",""));
                bw.close();
                fw.close();
            }
        }//Iterator end
    }//Method end



    void ICURoomDelete() throws IOException {
        ArrayList<bedIcuTabCLS1> list = new ArrayList<>();
        FileReader fr = new FileReader("src/main/resources/com/example/hospital_manament/" +
                "Records/ICU Bed");
        BufferedReader br = new BufferedReader(fr);
        while (true) {
            String s = br.readLine();
            if (s==null) break;
            list.add(new bedIcuTabCLS1(s));
        }
        fr.close();
        br.close();
        Iterator<bedIcuTabCLS1> it = list.iterator();
        while (it.hasNext()) {
            bedIcuTabCLS1 p = it.next();
            if (p.getIcr().equals(roomNo.getText())){
                it.remove();
                FileWriter fw = new FileWriter("src/main/resources/com/example/hospital_manament/" +
                        "Records/ICU Bed");
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(String.valueOf(list).replace("[","").replace("]","")
                        .replace(", ",""));
                bw.close();
                fw.close();
            }
        }//Iterator end
    }//Method end



    @FXML
    void unSelectHD() {
        bedTv.getSelectionModel().clearSelection();
    }
    @FXML
    void unSelectICU() {
        icuTv.getSelectionModel().clearSelection();
    }

}
