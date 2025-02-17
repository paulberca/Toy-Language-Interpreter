package model.statement;

import model.adt.MyIDictionary;
import model.exception.MyException;
import model.prgstate.PrgState;
import model.prgstate.dataStruct.ILockTable;
import model.prgstate.dataStruct.ISymTable;
import model.type.IType;
import model.type.IntType;
import model.value.IntValue;

public class LockStmt implements IStmt {
    private final String var;

    public LockStmt(String var) {
        this.var = var;
    }

    public PrgState execute(PrgState state) throws MyException {
        ILockTable lockTable = state.getLockTable();
        ISymTable symTable = state.getSymTable();
        if (!symTable.isDefined(this.var)) {
            throw new MyException("Variable " + this.var + " is not defined in the Symbol Table.");
        }
        IType type = symTable.lookup(this.var).getType();
        if (!type.equals(new IntType())) {
            throw new MyException("Variable " + var + " is not of type IntType");
        }

        int foundIndex = ((IntValue)symTable.lookup(this.var)).getVal();
        if (!lockTable.isDefined(foundIndex)) {
            throw new MyException("Index " + foundIndex + " is not in the Lock Table.");
        }

        synchronized(lockTable) {
            if (lockTable.get(foundIndex) == -1) {
                lockTable.update(foundIndex, state.getId());
            } else {
                state.getExeStack().push(this);
            }
        }

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new LockStmt(this.var);
    }

    public String toString() {
        return "lock(" + this.var + ")";
    }

    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType type = typeEnv.lookup(this.var);
        if (!type.equals(new IntType())) {
            throw new MyException("Variable " + this.var + " is not of type IntType.");
        }
        return typeEnv;
    }
}