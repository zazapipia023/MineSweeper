module ru.zaza.minesweeper {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.zaza.minesweeper to javafx.fxml;
    exports ru.zaza.minesweeper;
}