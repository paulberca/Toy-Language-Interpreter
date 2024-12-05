package model.statement;

import model.exception.MyException;
import model.expression.IExpression;
import model.prgstate.PrgState;
import model.prgstate.dataStruct.ISymTable;
import model.type.IType;
import model.type.RefType;
import model.value.IValue;
import model.value.RefValue;

public class NewStmt implements IStmt {
    private final String name;
    private final IExpression expression;

    public NewStmt(String name, IExpression expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        ISymTable symTbl = state.getSymTable();

        if (symTbl.isDefined(name)) {
            IType type = symTbl.lookup(name).getType();
            if (type instanceof RefType) {
                IValue value = expression.eval(symTbl, state.getHeap());
                RefValue refValue = (RefValue) symTbl.lookup(name);
                if (value.getType().equals(refValue.getLocationType())) {
                    int address = state.getHeap().allocate(value);
                    symTbl.update(name, new RefValue(address, value.getType()));
                }
                else {
                    throw new MyException("The declared type of variable " + name + " and the type of the assigned expression do not match");
                }
            }
            else {
                throw new MyException("The variable " + name + " is not a reference");
            }
        }
        else {
            throw new MyException("The used variable " + name + " was not declared before");
        }

        return null;
//        IValue value = expression.eval(state.getSymTable(), state.getHeap());
//        state.getSymTable().add(name, new RefValue(state.getHeap().allocate(value), value.getType()));
//        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NewStmt(name, expression.deepCopy());
    }

    @Override
    public String toString() {
        return "new(" + name + ", " + expression.toString() + ")";
    }
}
