package model.statement;

import model.adt.MyIDictionary;
import model.exception.MyException;
import model.prgstate.PrgState;
import model.type.IType;

public interface IStmt {
     PrgState execute(PrgState state) throws MyException;
//          which is the execution method for a model.statement

     MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException;

     IStmt deepCopy();
}

