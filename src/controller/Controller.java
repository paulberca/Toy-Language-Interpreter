package controller;

import model.adt.*;
import model.exception.MyException;
import model.exception.StackException;
import model.prgstate.PrgState;
import model.statement.IStmt;
import model.value.IValue;
import repo.IRepo;

import java.io.BufferedReader;

public class Controller {
    private IRepo repo;
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
        MyIStack<IStmt> stack = new MyStack<>();
        MyIDictionary<String, IValue> symTable = new MyDictionary<>();
        MyIList<IValue> out = new MyList<>();
        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
        PrgState prg = new PrgState(stack, symTable, out, fileTable, stmt);
        repo.changePrgState(prg);
    }
}
