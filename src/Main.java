import javafx.application.Application;
import javafx.stage.Stage;
import view.GUI.SetProgramWindow;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        SetProgramWindow setProgramWindow = new SetProgramWindow();
        setProgramWindow.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}