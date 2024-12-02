package model.statement;

import model.exception.MyException;
import model.expression.IExpression;
import model.prgstate.PrgState;
import model.prgstate.dataStruct.IHeap;
import model.prgstate.dataStruct.ISymTable;
import model.type.IType;
import model.type.RefType;
import model.value.IValue;
import model.value.RefValue;

public class WriteHeapStmt implements IStmt {
    private final String name;
    private final IExpression expression;

    public WriteHeapStmt(String name, IExpression expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "writeHeap(" + name + ", " + expression.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        ISymTable symTable = state.getSymTable();
        if (!(symTable.isDefined(name))) {
            throw new RuntimeException("Variable " + name + " is not defined");
        }

        IType type = symTable.lookup(name).getType();
        if (!(type instanceof RefType)) {
            throw new RuntimeException("Variable " + name + " is not a reference");
        }

        RefValue refValue = (RefValue) symTable.lookup(name);
        int address = refValue.getAddress();
        IValue value = expression.eval(symTable, state.getHeap());

        if (!value.getType().equals(refValue.getLocationType())) {
            throw new MyException("The value is not of the same type as the location type");
        }

        state.getHeap().write(address, value);

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new WriteHeapStmt(name, expression.deepCopy());
    }
}
