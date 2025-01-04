package GUI.MainProgram;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.adt.MyDictionary;
import model.exception.MyException;
import model.prgstate.PrgState;
import model.prgstate.dataStruct.*;
import model.statement.IStmt;
import repo.IRepo;
import repo.Repository;

public class MainProgram extends Application {
    private final IStmt stmt;
    private PrgState prgState;
    private IRepo repo;
    private Controller ctr;

    public MainProgram(IStmt stmt) {
        this.stmt = stmt;
        prgState = new PrgState(new ExeStack(), new SymTable(), new Output(), new FileTable(), new Heap(), stmt);
        repo = new Repository(prgState, "log.txt");
        ctr = new Controller(repo);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/MainProgram/MainProgram.fxml"));
        Parent root = loader.load();

        MainProgramController controller = loader.getController();
        controller.setController(ctr);
        controller.setPrgState(prgState);
        controller.setRepo(repo);

        initializeComponents(controller);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Run Program");
        primaryStage.show();
    }

    public void initializeComponents(MainProgramController controller) {
        controller.programLabel.setText("Program: " + stmt);
        controller.exeStackList.getItems().add(prgState.getExeStack().toString());
        controller.nrOfPrgStates.setText("No of PrgStates: " + repo.getPrgList().size());
        controller.PrgStateIDsList.getItems().add(repo.getPrgList().getFirst().getId());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
