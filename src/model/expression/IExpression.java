package model.expression;

import model.adt.*;
import model.exception.*;
import model.value.*;

public interface IExpression {
    public IValue eval(MyIDictionary<String, IValue> tbl) throws MyException;
}

