package GUI.MainProgram;

import controller.Controller;
import controller.GarbageCollector;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import model.prgstate.PrgState;
import model.prgstate.dataStruct.*;
import model.statement.IStmt;
import model.value.IValue;
import repo.IRepo;
import repo.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainProgram extends Application {
    private final IStmt stmt;
    private final PrgState prgState;
    private final IRepo repo;
    private final Controller ctr;
    private ExecutorService executor;
    private List<PrgState> prgList;
    private int currentPrgStateID;

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

        controller.runOneStepButton.setOnAction(_ -> runOneStep(controller));
//        controller.runOneStepButton.setOnAction(event -> {
//            try {
//                ctr.allStep();
//                refreshViews(controller);
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        });

        controller.PrgStateIDsList.getSelectionModel().selectedIndexProperty().addListener((_, _, newValue) -> {
            if (newValue != null && newValue.intValue() >= 0) {
                currentPrgStateID = controller.PrgStateIDsList.getItems().get(newValue.intValue());
                refreshExeStack(controller);
                refreshSymTableView(controller);
            }
        });

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Run Program");
        primaryStage.show();
    }

    private void initializeComponents(MainProgramController controller) {
        controller.programLabel.setText("Program: " + stmt);
        controller.exeStackList.getItems().add(prgState.getExeStack().toString());
        controller.nrOfPrgStates.setText("No of PrgStates: " + repo.getPrgList().size());
        controller.PrgStateIDsList.getItems().add(repo.getPrgList().getFirst().getId());

        currentPrgStateID = 1;

        executor = Executors.newFixedThreadPool(2);
        prgList = ctr.removeCompletedPrg(repo.getPrgList());
        try {
            repo.clearFile();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error clearing file");
            alert.setContentText(e.getMessage());
        }
    }

    private void runOneStep(MainProgramController controller) {
        GarbageCollector collector = new GarbageCollector();

        if (!prgList.isEmpty()) {
            for (PrgState prg : prgList) {
                List<Integer> symTableAddr = collector.getAddrFromSymTable(new ArrayList<>(prg.getSymTable().getContent().values()));
                Set<Integer> reachableAddr = collector.computeReachableAddr(symTableAddr, prg.getHeap().getContent());
                prg.getHeap().setContent(collector.safeGarbageCollector(reachableAddr, prg.getHeap().getContent()));
            }

            try {
                ctr.oneStepForAllPrg(prgList, executor);
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error running one step");
                alert.setContentText(e.getMessage());
            }
            prgList = ctr.removeCompletedPrg(repo.getPrgList());

            refreshViews(controller);
        }
        else {
            executor.shutdownNow();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Execution");
            alert.setHeaderText("Program finished");
            alert.setContentText("All programs have finished executing.");
            alert.showAndWait();

            repo.setPrgList(prgList);
        }
    }

    private void refreshViews(MainProgramController controller) {
        refreshHeap(controller);
        refreshExeStack(controller);
        refreshOut(controller);
        refreshPrgStateIDList(controller);
        refreshSymTableView(controller);
        refreshNrOfPrgStates(controller);
        refreshFileTable(controller);
    }

    private void refreshHeap(MainProgramController controller) {
        controller.heapTable.setItems(FXCollections.observableArrayList(prgState.getHeap().getContent().entrySet()));
        controller.heapTable.getColumns().clear();

        TableColumn<Map.Entry<Integer, IValue>, Integer> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getKey()));

        TableColumn<Map.Entry<Integer, IValue>, IValue> valueColumn = new TableColumn<>("Value");
        valueColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getValue()));

        controller.heapTable.getColumns().add(addressColumn);
        controller.heapTable.getColumns().add(valueColumn);
    }

    private void refreshExeStack(MainProgramController controller) {
        controller.exeStackList.getItems().clear();
        PrgState currentPrgState = null;
        for (PrgState prg : prgList) {
            if (prg.getId() == currentPrgStateID) {
                currentPrgState = prg;
                break;
            }
        }
        if (currentPrgState == null) {
            System.out.println("Could not find PrgState with ID: " + currentPrgStateID);
            if (!prgList.isEmpty()) {
                currentPrgState = prgList.getFirst();
                currentPrgStateID = prgList.getFirst().getId();
                System.out.println("Setting currentPrgState to PrgState with ID: " + currentPrgStateID);
            }
            else {
                System.out.println("prgList is empty");
                return;
            }
        }
        List<IStmt> exeStack = currentPrgState.getExeStack().toList();
        for (IStmt stmt : exeStack) {
            controller.exeStackList.getItems().add(stmt.toString());
        }
    }

    private void refreshOut(MainProgramController controller) {
        controller.outList.getItems().clear();
        List<IValue> out = prgState.getOut().toList();
        for (IValue value : out) {
            controller.outList.getItems().add(value.toString());
        }
    }

    private void refreshPrgStateIDList(MainProgramController controller) {
        controller.PrgStateIDsList.getItems().clear();
        for (PrgState prg : prgList) {
            controller.PrgStateIDsList.getItems().add(prg.getId());
        }
    }

    private void refreshSymTableView(MainProgramController controller) {
        PrgState currentPrgState = null;
        for (PrgState prg : prgList) {
            if (prg.getId() == currentPrgStateID) {
                currentPrgState = prg;
                break;
            }
        }
        if (currentPrgState == null) {
            if (!prgList.isEmpty()) {
                currentPrgState = prgList.getFirst();
                currentPrgStateID = prgList.getFirst().getId();
            }
            else {
                return;
            }
        }
        controller.symTableView.setItems(FXCollections.observableArrayList(currentPrgState.getSymTable().getContent().entrySet()));
        controller.symTableView.getColumns().clear();

        TableColumn<Map.Entry<String, IValue>, String> varNameColumn = new TableColumn<>("Variable Name");
        varNameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getKey()));

        TableColumn<Map.Entry<String, IValue>, String> valueColumn = new TableColumn<>("Value");
        valueColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getValue().toString()));

        TableColumn<Map.Entry<String, IValue>, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getValue().getType().toString()));

        controller.symTableView.getColumns().add(varNameColumn);
        controller.symTableView.getColumns().add(valueColumn);
        controller.symTableView.getColumns().add(typeColumn);
    }

    private void refreshNrOfPrgStates(MainProgramController controller) {
        controller.nrOfPrgStates.setText("No of PrgStates: " + prgList.size());
    }

    private void refreshFileTable(MainProgramController controller) {
        controller.fileTableList.getItems().clear();
        List<String> fileTable = prgState.getFileTable().toList();
        for (String file : fileTable) {
            controller.fileTableList.getItems().add(file);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
