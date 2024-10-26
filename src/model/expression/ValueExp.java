package model.expression;

import model.value.*;
import model.adt.*;
import model.exception.*;

public class ValueExp implements IExpression {
    private IValue e;

    public ValueExp(IValue e) {
        this.e = e;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl) throws MyException {
        return e;
    }
}

