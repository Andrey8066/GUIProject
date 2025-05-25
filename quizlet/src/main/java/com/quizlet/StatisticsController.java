package com.quizlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StatisticsController {
    @FXML
    private ComboBox<String> ChooseDirrectoryCombo;
    @FXML
    private VBox StatisticsVBox;
    @FXML
    private TableView<Statistic> StatisticsTable;
    @FXML
    private TextArea Answer;
    @FXML
    private TableColumn<Statistic, String> NameColumn;
    @FXML
    private TableColumn<Statistic, String> PercentColumn;
    private Topics topics;
    private Questions questions;
    private Statistics statistics;

    @FXML
    public void initialize() throws Exception {
        this.topics = new Topics();
        this.questions = new Questions();
        statistics = new Statistics();
        for (Topic topic : this.topics.getAll()) {
            ChooseDirrectoryCombo.getItems().add(new String(topic.getName()));
        }

        NameColumn.setPrefWidth(200);
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PercentColumn.setCellValueFactory(new PropertyValueFactory<>("percent"));
        StatisticsTable.getColumns().addAll(NameColumn, PercentColumn);
    }

    @FXML
    public void handleDirrectorySelection() {
        StatisticsTable.getItems().clear();
        try {
            int f = 0;
            String selectedTopic = ChooseDirrectoryCombo.getValue();

            ArrayList<Statistic> stats = statistics.getStat(selectedTopic);
            for (Statistic stat : stats) {
                if (!stat.getPercent().contains("NaN")) {
                    System.out.println(stat.getPercent());
                    StatisticsTable.getItems().addAll(stat);
                    f += 1;
                }
            }
            if (f == 0) {
                Stage noStatistics = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("checkAnswer.fxml"));
                Scene scene = new Scene(loader.load());
                noStatistics.setWidth(200);
                noStatistics.setHeight(200);

                noStatistics.setScene(scene);
                noStatistics.show();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleMainMenuButton() throws SQLException {
        try {
            App.setRoot("Welcome");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
