package GUI.MainProgram;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import model.prgstate.PrgState;
import model.statement.IStmt;
import repo.IRepo;

import java.util.List;

public class MainProgramController {
    Controller controller;
    PrgState prgState;
    IRepo repo;

    @FXML
    public Label programLabel;

    @FXML
    public Button runOneStepButton;

    @FXML
    public HBox mainBox;

    @FXML
    public TableView<?> heapTable;

    @FXML
    public TextField nrOfPrgStates;

    @FXML
    public ListView<?> outList;

    @FXML
    public ListView<?> fileTableList;

    @FXML
    public ListView<Integer> PrgStateIDsList;

    @FXML
    public TableView<?> symTable;

    @FXML
    public ListView<String> exeStackList;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setPrgState(PrgState prgState) {
        this.prgState = prgState;
    }

    public void setRepo(IRepo repo) {
        this.repo = repo;
    }
}