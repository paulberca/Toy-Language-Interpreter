package model.statement;

import model.adt.MyIDictionary;
import model.exception.MyException;
import model.prgstate.PrgState;
import model.type.IType;
import model.type.IntType;
import model.value.IntValue;

public class NewLockStmt implements IStmt {
    private final String var;

    public NewLockStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        int newLockAddress;
        synchronized (state.getLockTable()) {
            newLockAddress= state.getLockTable().getFreeLocation();
            state.getLockTable().update(newLockAddress, -1);
        }
        if (state.getSymTable().isDefined(var)) {
            if (!state.getSymTable().lookup(var).getType().equals(new IntType())) {
                throw new MyException("Variable " + var + " is not of the type IntType");
            }
            state.getSymTable().update(var, new IntValue(newLockAddress));
        } else {
            state.getSymTable().add(var, new IntValue(newLockAddress));
        }

        return null;
    }

    @Override
    public String toString() {
        return "newLock(" + this.var + ")";
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType type = typeEnv.lookup(var);
        if (!type.equals(new IntType())) {
            throw new MyException("Variable " + var + " is not of type IntType.");
        }
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new NewLockStmt(this.var);
    }
}