package expression;

import exception.*;
import value.*;
import adt.*;

public class VarExp implements IExpression {
    private String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl) throws MyException {
        return tbl.lookup(id);
    }
}

