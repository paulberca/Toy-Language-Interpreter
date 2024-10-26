package model.statement;

import model.exception.MyException;
import model.prgstate.PrgState;

public interface IStmt {
     PrgState execute(PrgState state) throws MyException;
//          which is the execution method for a model.statement
}

