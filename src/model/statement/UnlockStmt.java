package model.statement;

import model.adt.MyIDictionary;
import model.exception.MyException;
import model.prgstate.PrgState;
import model.prgstate.dataStruct.ILockTable;
import model.prgstate.dataStruct.ISymTable;
import model.type.IType;
import model.type.IntType;
import model.value.IntValue;

public class UnlockStmt implements IStmt {
    private final String var;

    public UnlockStmt(String var) {
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
            return null;
        }

        synchronized(lockTable) {
            if (lockTable.get(foundIndex) == state.getId()) {
                lockTable.update(foundIndex, -1);
            }
        }

        return null;
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType type = typeEnv.lookup(this.var);
        if (!type.equals(new IntType())) {
            throw new MyException("Variable " + this.var + " is not of type IntType.");
        }
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new UnlockStmt(this.var);
    }

    public String toString() {
        return "unlock(" + this.var + ")";
    }
}