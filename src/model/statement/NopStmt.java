package model.statement;

import model.exception.MyException;
import model.prgstate.PrgState;

public class NopStmt implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException {
        // code to be added
        return state;
    }
}

