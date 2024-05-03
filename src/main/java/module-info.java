module it.itismeucci {
    requires javafx.controls;
    requires javafx.fxml;

    opens it.itismeucci to javafx.fxml;
    exports it.itismeucci;
}
