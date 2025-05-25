module com.quizlet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.pdfbox;

    opens com.quizlet to javafx.fxml;
    exports com.quizlet;

}
