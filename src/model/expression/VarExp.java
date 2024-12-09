package model.expression;

import model.adt.MyIDictionary;
import model.exception.*;
import model.prgstate.dataStruct.IHeap;
import model.prgstate.dataStruct.ISymTable;
import model.type.IType;
import model.value.*;

public class VarExp implements IExpression {
    private final String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public IValue eval(ISymTable tbl, IHeap hp) throws MyException {
        return tbl.lookup(id);
    }

    @Override
    public IType typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return typeEnv.lookup(id);
    }

    @Override
    public IExpression deepCopy() {
        return new VarExp(id);
    }

    public String toString() {
        return id;
    }
}

