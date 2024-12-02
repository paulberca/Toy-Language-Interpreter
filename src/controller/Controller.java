package controller;

import model.exception.MyException;
import model.exception.StackException;
import model.prgstate.PrgState;
import model.statement.IStmt;
import repo.IRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Controller {
    private final IRepo repo;
    boolean printFlag;

    public Controller(IRepo repo) {
        this.repo = repo;
        printFlag = true;
    }

    public void allStep() {
        PrgState prg = repo.getCrtPrg();
        GarbageCollector collector = new GarbageCollector();
        try {
            repo.clearFile();
            repo.logPrgStateExec();
        } catch (MyException e) { System.out.println(e.getMessage()); }
        while (!prg.getExeStack().isEmpty()) {
            try {
                if (printFlag) {
                    displayCrtPrgState(prg);
                }
                oneStep(prg);
                try { repo.logPrgStateExec(); } catch (MyException e) { System.out.println(e.getMessage()); }

                List<Integer> symTableAddr = collector.getAddrFromSymTable(new ArrayList<>(prg.getSymTable().getContent().values()));
                Set<Integer> reachableAddr = collector.computeReachableAddr(symTableAddr, prg.getHeap().getContent());
                prg.getHeap().setContent(collector.safeGarbageCollector(reachableAddr, prg.getHeap().getContent()));

            } catch (MyException | StackException e) {
                System.out.println(e.getMessage());
            }
        }
        displayCrtPrgState(prg);
    }

    public void displayCrtPrgState(PrgState prg) {
        System.out.print(prg.toString());
        System.out.println("-".repeat(50));
    }
}
