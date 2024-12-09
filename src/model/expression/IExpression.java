package model.expression;

import model.adt.MyIDictionary;
import model.exception.*;
import model.prgstate.dataStruct.IHeap;
import model.prgstate.dataStruct.ISymTable;
import model.type.IType;
import model.value.*;

public interface IExpression {
    IValue eval(ISymTable tbl, IHeap hp) throws MyException;

    IExpression deepCopy();

    IType typecheck(MyIDictionary<String, IType> typeEnv) throws MyException;
}

