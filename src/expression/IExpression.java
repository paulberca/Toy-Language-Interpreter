package expression;

import adt.*;
import exception.*;
import value.*;

public interface IExpression {
    public IValue eval(MyIDictionary<String, IValue> tbl) throws MyException;
}

