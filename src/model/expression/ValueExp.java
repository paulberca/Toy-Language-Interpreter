package model.expression;

import model.adt.MyIDictionary;
import model.exception.MyException;
import model.prgstate.dataStruct.IHeap;
import model.prgstate.dataStruct.ISymTable;
import model.type.IType;
import model.value.*;

public class ValueExp implements IExpression {
    private final IValue e;

    public ValueExp(IValue e) {
        this.e = e;
    }

    @Override
    public IValue eval(ISymTable tbl, IHeap hp) {
        return e;
    }

    @Override
    public IType typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return e.getType();
    }

    @Override
    public IExpression deepCopy() {
        return new ValueExp(e.deepCopy());
    }

    public String toString() {
        return e.toString();
    }
}

