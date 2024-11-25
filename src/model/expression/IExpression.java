package model.expression;

import model.exception.*;
import model.prgstate.dataStruct.IHeap;
import model.prgstate.dataStruct.ISymTable;
import model.value.*;

public interface IExpression {
    IValue eval(ISymTable tbl, IHeap hp) throws MyException;

    IExpression deepCopy();
}

