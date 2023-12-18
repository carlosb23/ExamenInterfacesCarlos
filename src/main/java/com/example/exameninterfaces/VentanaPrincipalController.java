package com.example.exameninterfaces;

import com.example.exameninterfaces.models.Cliente;
import com.example.exameninterfaces.models.Coche;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentanaPrincipalController implements Initializable {

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
    private TableColumn<Coche,String> columMatricula;
    @FXML
    private TableColumn<Coche,String> columModelo;
    @FXML
    private TableColumn<Coche,String> columFechaentra;
    @FXML
    private TableColumn<Coche,String> columnFechasalida;
    @FXML
    private TableColumn<Coche,String> columCliente;
    @FXML
    private TableColumn<Coche,String> columTarifa;
    @FXML
    private TableColumn<Coche,String> columCoste;
    @FXML
    private ChoiceBox choiseboxcliente;

    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();
    private ObservableList<Coche> coches = FXCollections.observableArrayList();
    @FXML
    private TableView<Coche> tabla;
    @FXML
    private Button añadir;
    @FXML
    private Button salir;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Combo de modelos
        ObservableList<String> datosmodelo = FXCollections.observableArrayList();
        datosmodelo.addAll("M-35","124 spider","Vantage","Citroen C3","Bronco");
        combomodelo.setItems(datosmodelo);


        if (Session.getClientes().isEmpty()){
            ArrayList<Cliente> cli = new ArrayList<>();
            cli.add(new Cliente("Carlos","carlosforma@gmail.com"));
            cli.add(new Cliente("Paco","pacojose@gmail.com"));
            cli.add(new Cliente("Maria","mariajosefa@gmail.com"));
            cli.add(new Cliente("Lucia","lulu@gmail.com"));
            Session.setClientes(cli);
        }

        clientes.addAll(Session.getClientes());

        choiseboxcliente.setConverter(new StringConverter<Cliente>() {
            public String toString(Cliente cliente) {
                if (cliente != null) {
                    return cliente.getNombre();
                } else {
                    return null;
                }
            }

            @Override
            public Cliente fromString(String s) {
                return null;
            }
        });
        choiseboxcliente.setItems(clientes);


        if (Session.getCoches().isEmpty()){
            ArrayList<Coche> coch = new ArrayList<>();
            coch.add(new Coche("2154jkf","Vantage",LocalDate.now(),LocalDate.now() ,"Lucia","Standard",5));
            coch.add(new Coche("2558klk","Vantage",LocalDate.now(),LocalDate.now(),"Maria","Standard",5));
            coch.add(new Coche("2558jlk","Vantage",LocalDate.now(),LocalDate.now(),"Carlos","Standard",5));
            coch.add(new Coche("3233fgt","Vantage",LocalDate.now(),LocalDate.now(),"Paco","Standard",5));
            Session.setCoches(coch);
        }

        coches.addAll(Session.getCoches());

        columMatricula.setCellValueFactory((fila)->{
            String matricula = fila.getValue().getMatricula();
            return new SimpleStringProperty(matricula);
        });
        columModelo.setCellValueFactory((fila)->{
            String modelo = fila.getValue().getModelo();
            return new SimpleStringProperty(modelo);
        });
        columFechaentra.setCellValueFactory((fila) -> {
            LocalDate fechaentrada = fila.getValue().getFechaentrada();
            return new SimpleStringProperty(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(fechaentrada));
        });
        columnFechasalida.setCellValueFactory((fila) -> {
            LocalDate fechasalida = fila.getValue().getFechaentrada();
            return new SimpleStringProperty(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(fechasalida));
        });
        columCliente.setCellValueFactory((fila)->{
            String cliente = fila.getValue().getCliente();
            return new SimpleStringProperty(cliente);
        });
        columTarifa.setCellValueFactory((fila)->{
            String tarifa = fila.getValue().getTarifa();
            return new SimpleStringProperty(tarifa);
        });
        columCoste.setCellValueFactory((fila) -> {
            Integer coste = fila.getValue().getCoste();
            if (coste != null) {
                return new SimpleStringProperty(coste.toString());
            } else {
                return new SimpleStringProperty("");
            }
        });

        tabla.setItems(coches);
    }

    @FXML
    public void buttonañadir(ActionEvent actionEvent) {


    }

    @FXML
    public void btnexit(ActionEvent actionEvent) {
        System.exit(0);

    }
}