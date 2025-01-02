package view.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.statement.IStmt;

public class MainProgram extends Application {
    private final IStmt stmt;

    public MainProgram(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Program: " + stmt.toString());
        VBox vbox = new VBox(label);
        Scene scene = new Scene(vbox, 1200, 800);
        primaryStage.setTitle("Program");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
