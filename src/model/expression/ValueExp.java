package model.expression;

import model.prgstate.dataStruct.ISymTable;
import model.value.*;

public class ValueExp implements IExpression {
    private final IValue e;

    public ValueExp(IValue e) {
        this.e = e;
    }

    @Override
    public IValue eval(ISymTable tbl) {
        return e;
    }

    @Override
    public IExpression deepCopy() {
        return new ValueExp(e.deepCopy());
    }

    public String toString() {
        return e.toString();
    }
}

