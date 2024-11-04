package model.expression;

import model.value.*;
import model.adt.*;

public class ValueExp implements IExpression {
    private IValue e;

    public ValueExp(IValue e) {
        this.e = e;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl) {
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

