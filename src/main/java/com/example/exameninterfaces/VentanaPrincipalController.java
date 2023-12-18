package com.example.exameninterfaces;

import com.example.exameninterfaces.models.Cliente;
import com.example.exameninterfaces.models.Coche;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
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

    double standard = 8.0;
    double oferta = 6.0;
    double Largaduracion = 2.0;
    @FXML
    private RadioButton radiooferta;
    @FXML
    private RadioButton radiolarga;
    @FXML
    private RadioButton radioStandard;

    private final ToggleGroup toggleGroup = new ToggleGroup();
    @FXML
    private Label clckinfo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Combo de modelos
        ObservableList<String> datosmodelo = FXCollections.observableArrayList();
        datosmodelo.addAll("M-35","124 spider","Vantage","Citroen C3","Bronco");
        combomodelo.setItems(datosmodelo);


        if (Session.getClientes().isEmpty()){
            ArrayList<Cliente> cli = new ArrayList<>();
            cli.add(new Cliente(1L,"Carlos","carlosforma@gmail.com"));
            cli.add(new Cliente(2L,"Paco","pacojose@gmail.com"));
            cli.add(new Cliente(3L,"Maria","mariajosefa@gmail.com"));
            cli.add(new Cliente(4L,"Lucia","lulu@gmail.com"));
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

        //Para tener la tabla rellena con algo
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
            return new SimpleStringProperty(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(fechaentrada));
        });
        columnFechasalida.setCellValueFactory((fila) -> {
            LocalDate fechasalida = fila.getValue().getFechasalida();
            return new SimpleStringProperty(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(fechasalida));
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


        radioStandard.setToggleGroup(toggleGroup);
        radiooferta.setToggleGroup(toggleGroup);
        radiolarga.setToggleGroup(toggleGroup);

    }

    @FXML
    public void buttonañadir(ActionEvent actionEvent) {

        if (camposEstanLlenos()) {
            Coche cochenuevo = new Coche();
            cochenuevo.setMatricula(txtmatricula.getText());
            cochenuevo.setModelo(combomodelo.getValue()+ "");
            cochenuevo.setCliente(String.valueOf((Cliente) choiseboxcliente.getValue()));
            cochenuevo.setFechaentrada(dataentrada.getValue());
            cochenuevo.setFechasalida(dataSalida.getValue());

            var entrada = dataentrada.getValue();
            var salida = dataSalida.getValue();
            RadioButton seleccion = (RadioButton) toggleGroup.getSelectedToggle();
            Period tiempo = Period.between(entrada,salida);
            var numeroPeriodo = tiempo.getDays();

            if(seleccion != null){
                if(seleccion == radioStandard){
                    var total = numeroPeriodo * 8;
                    labelcosto.setText(total + " €");
                    cochenuevo.setCoste(total);
                }
                if(seleccion == radiooferta){
                    var total = numeroPeriodo * 6;
                    labelcosto.setText(total + " €");
                    cochenuevo.setCoste(total);
                }
                if(seleccion == radiolarga){
                    var total = numeroPeriodo * 2;
                    labelcosto.setText(total + " €");
                    cochenuevo.setCoste(total);
                }
            }

            cochenuevo.setTarifa(((RadioButton) toggleGroup.getSelectedToggle()).getText());
            coches.add(cochenuevo);
            tabla.setItems(coches);

        } else {
            // Mostrar una alerta indicando que algunos campos están vacíos
            mostrarAlerta("Error", "Todos los campos son obligatorios");
        }

    }

    private boolean camposEstanLlenos() {
        return !txtmatricula.getText().isEmpty() &&
                combomodelo.getValue() != null &&
                dataentrada.getValue() != null &&
                dataSalida.getValue() != null &&
                choiseboxcliente.getValue() != null &&
                toggleGroup.getSelectedToggle() != null;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    public void btnexit(ActionEvent actionEvent) {
        System.exit(0);

    }

    @FXML
    public void info(Event event) {

        String miNombre = "Carlos Bustos Torralbo \n 2ºDAM";
        String mensaje = String.format(miNombre);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información Personal");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}