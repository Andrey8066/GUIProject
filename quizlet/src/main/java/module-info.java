module com.quizlet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.quizlet to javafx.fxml;
    exports com.quizlet;

}
