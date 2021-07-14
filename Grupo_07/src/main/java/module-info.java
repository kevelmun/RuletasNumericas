module com.mycompany.grupo_07 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.grupo_07 to javafx.fxml;
    exports com.mycompany.grupo_07;
}