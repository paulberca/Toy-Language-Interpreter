package model.statement;

import model.exception.MyException;
import model.prgstate.PrgState;
import model.prgstate.dataStruct.IExeStack;

public class CompStmt implements IStmt {
    private final IStmt first;
    private final IStmt snd;

    public CompStmt(IStmt first, IStmt snd) {
        this.first = first;
        this.snd = snd;
    }

    public String toString() {
        return "(" + first.toString() + ";" + snd.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {
        IExeStack stk = state.getExeStack();
        stk.push(snd);
        stk.push(first);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CompStmt(first.deepCopy(), snd.deepCopy());
    }
}

