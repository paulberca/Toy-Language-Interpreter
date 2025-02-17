package GUI.MainProgram;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import model.prgstate.PrgState;
import model.value.IValue;
import repo.IRepo;

import java.util.Map;

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
    public TableView<Map.Entry<Integer, IValue>> heapTable;

    @FXML
    public TextField nrOfPrgStates;

    @FXML
    public ListView<String> outList;

    @FXML
    public ListView<String> fileTableList;

    @FXML
    public ListView<Integer> PrgStateIDsList;

    @FXML
    public TableView<Map.Entry<String, IValue>> symTableView;

    @FXML
    public TableView<Map.Entry<Integer, Integer>> lockTableView;

    @FXML
    public TableView<Map.Entry<String, String>> procTableView;

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