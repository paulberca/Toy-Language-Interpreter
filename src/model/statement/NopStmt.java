package model.statement;

import model.exception.MyException;
import model.prgstate.PrgState;

public class NopStmt implements IStmt {
    public NopStmt() {
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return "nop";
    }

    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }
}

