package model.statement;

import model.adt.MyIDictionary;
import model.exception.MyException;
import model.prgstate.PrgState;
import model.type.IType;

public class ReturnStmt implements IStmt {
    public ReturnStmt() {}

    @Override
    public String toString() {
        return "return";
    }

    @Override
    public IStmt deepCopy() {
        return new ReturnStmt();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        try {
            state.getSymTablesStack().pop();
        } catch (Exception e) {
            throw new MyException("Return error: Empty SymTable stack");
        }

        return null;
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return typeEnv;
    }
}
