module com.example.exameninterfaces {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.example.exameninterfaces to javafx.fxml;
    opens images;
    exports com.example.exameninterfaces;
}