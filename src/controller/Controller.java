package controller;

import model.adt.MyIStack;
import model.exception.MyException;
import model.exception.StackException;
import model.prgstate.PrgState;
import model.statement.IStmt;
import repo.IRepo;

public class Controller {
    private IRepo repo;
    boolean printFlag;

    public Controller(IRepo repo) {
        this.repo = repo;
        printFlag = true;
    }

    public PrgState oneStep(PrgState state) throws StackException, MyException {
        MyIStack<IStmt> stk = state.getExeStack();

        if (stk.isEmpty()) {
            throw new StackException("prgstate stack is empty");
        }

        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    public void allStep() {
        PrgState prg = repo.getCrtPrg();
        while (!prg.getExeStack().isEmpty()) {
            try {
                if (printFlag) {
                    displayCrtPrgState(prg);
                }
                oneStep(prg);
            } catch (MyException | StackException e) {
                System.out.println(e.getMessage());
            }
        }
        displayCrtPrgState(prg);
    }

    public void displayCrtPrgState(PrgState prg) {
        System.out.println(prg.toString());
    }
}
