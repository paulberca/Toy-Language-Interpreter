package controller;

import model.exception.MyException;
import model.exception.StackException;
import model.prgstate.PrgState;
import model.statement.IStmt;
import repo.IRepo;
import model.prgstate.dataStruct.*;

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

    public PrgState oneStep(PrgState state) throws StackException, MyException {
        IStmt crtStmt = state.getExeStack().pop();
        return crtStmt.execute(state);
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

                garbageCollector(prg, collector);

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

    public void changePrgState(IStmt stmt) {
        IExeStack stack = new ExeStack();
        ISymTable symTable = new SymTable();
        IOutput out = new Output();
        IFileTable fileTable = new FileTable();
        IHeap heap = new Heap();
        PrgState prg = new PrgState(stack, symTable, out, fileTable, heap, stmt);
        repo.changePrgState(prg);
    }

    private void garbageCollector(PrgState prg, GarbageCollector collector) {
        // prg.getHeap().setContent(collector.unsafeGarbageCollector(collector.getAddrFromSymTable(prg.getSymTable().getContent().values()), prg.getHeap().getContent()));

        List<Integer> symTableAddr = collector.getAddrFromSymTable(new ArrayList<>(prg.getSymTable().getContent().values()));
        Set<Integer> reachableAddr = collector.computeReachableAddr(symTableAddr, prg.getHeap().getContent());
        prg.getHeap().setContent(collector.safeGarbageCollector(reachableAddr, prg.getHeap().getContent()));
    }
}
