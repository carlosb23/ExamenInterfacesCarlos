package com.example.exameninterfaces;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField txtmatricula;
    @FXML
    private ComboBox combomodelo;
    @FXML
    private Label labelcosto;
    @FXML
    private DatePicker dataSalida;
    @FXML
    private DatePicker dataentrada;
    @FXML
    private RadioButton radioStandar;
    @FXML
    private RadioButton radioOferta;
    @FXML
    private RadioButton radioLarga;
    @FXML
    private Button añadirtabla;
    @FXML
    private Button salirapp;
    @FXML
    private TableColumn columMatricula;
    @FXML
    private TableColumn columModelo;
    @FXML
    private TableColumn columFechaentra;
    @FXML
    private TableColumn columnFechasalida;
    @FXML
    private TableColumn columCliente;
    @FXML
    private TableColumn columTarifa;
    @FXML
    private TableColumn columCoste;
    @FXML
    private ChoiceBox choiseboxcliente;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Combo de modelos
        ObservableList<String> datosmodelo = FXCollections.observableArrayList();
        datosmodelo.addAll("M-35","124 spider","Vantage","Citroen C3","Bronco");
        combomodelo.setItems(datosmodelo);

        if()

    }
}