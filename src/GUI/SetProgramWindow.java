package GUI;

import GUI.MainProgram.MainProgram;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import model.adt.MyDictionary;
import model.statement.IStmt;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import view.interpreter.Interpreter;


import java.util.ArrayList;

public class SetProgramWindow extends Application {
    private IStmt selectedProgram;

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Please select a program:");
        label.setFont(new Font("Arial", 20));
        Button runButton = new Button("Run");
        runButton.setFont(new Font("Arial", 20));

        ArrayList<IStmt> statements = new Interpreter().getStatements();
        ListView<String> listView = new ListView<>();
        for (int i = 0; i < statements.size(); i++) {
            listView.getItems().add((i+1) + ". " + statements.get(i).toString());
        }


        // this is to change the font and the size of the text in the listView
        listView.setCellFactory(lv -> new ListCell<>() {
            @Override
            public void updateItem(String item, boolean empty) { super.updateItem(item, empty); if (empty) { setText(null); } else { setText(item); setFont(new Font("Arial", 17)); } } });

        // this is to know which program is currently selected by the user
        listView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.intValue() >= 0) {
                selectedProgram = statements.get(newValue.intValue());
            }
        });

        runButton.setOnAction(event -> {
            if (selectedProgram != null) {
                try {
                    selectedProgram.typecheck(new MyDictionary<>());
                } catch (Exception e) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Type check error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                    return;
                }

                MainProgram mainProgram = new MainProgram(selectedProgram);
                Stage newStage = new Stage();
                try {
                    mainProgram.start(newStage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                primaryStage.close();
            }
            else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("No Program Selected");
                alert.setContentText("Please select a program before clicking 'Run'.");
                alert.showAndWait();
            }
        });

        HBox labelBox = new HBox(label);
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setPadding(new javafx.geometry.Insets(10, 0, 0, 0));

        HBox buttonBox = new HBox(runButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new javafx.geometry.Insets(10, 0, 0, 0));

        VBox vbox = new VBox(labelBox, listView, buttonBox);
        Scene scene = new Scene(vbox, 1200, 500);
        primaryStage.setTitle("Select a program");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
