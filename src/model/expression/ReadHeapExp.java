package model.expression;

import model.exception.MyException;
import model.prgstate.dataStruct.IHeap;
import model.prgstate.dataStruct.ISymTable;
import model.value.IValue;
import model.value.RefValue;

public class ReadHeapExp implements IExpression {
    private final IExpression expression;

    public ReadHeapExp(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "readHeap(" + expression.toString() + ")";
    }

    @Override
    public IValue eval(ISymTable tbl, IHeap hp) throws MyException {
        IValue value = expression.eval(tbl, hp);

        if (!(value instanceof RefValue)) {
            throw new MyException("The expression is not a reference");
        }

        int address = ((RefValue) value).getAddress();
        return hp.read(address);
    }

    @Override
    public IExpression deepCopy() {
        return new ReadHeapExp(expression.deepCopy());
    }
}
