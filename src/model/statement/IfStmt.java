package model.statement;

import model.exception.MyException;
import model.expression.*;
import model.prgstate.PrgState;
import model.prgstate.dataStruct.ISymTable;
import model.type.BoolType;
import model.value.BoolValue;
import model.value.IValue;

public class IfStmt implements IStmt {
    private final IExpression exp;
    private final IStmt thenS;
    private final IStmt elseS;

    public IfStmt(IExpression exp, IStmt thenS, IStmt elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    public String toString() {
        return "(IF(" + exp.toString() + ") THEN(" + thenS.toString() + ") ELSE(" + elseS.toString() + "))";
    }

    public PrgState execute(PrgState state) throws MyException {
        ISymTable symTbl = state.getSymTable();
        IValue val = exp.eval(symTbl, state.getHeap());

        if (!val.getType().equals(new BoolType())) {
            throw new MyException("If condition is not a boolean");
        }

        BoolValue v = (BoolValue) val;
        if (v.getVal()) {
            state.getExeStack().push(thenS);
        } else {
            state.getExeStack().push(elseS);
        }

        return null;
    }

    public IStmt deepCopy() {
        return new IfStmt(exp.deepCopy(), thenS.deepCopy(), elseS.deepCopy());
    }
}

