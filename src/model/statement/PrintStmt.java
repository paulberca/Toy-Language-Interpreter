package model.statement;

import model.adt.MyIList;
import model.exception.MyException;
import model.expression.IExpression;
import model.prgstate.PrgState;
import model.value.IValue;

public class PrintStmt implements IStmt {
    private IExpression exp;

    public PrintStmt(IExpression exp) {
        this.exp = exp;
    }

    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {
        IValue val = exp.eval(state.getSymTable());
        MyIList<IValue> out = state.getOut();
        out.add(val);
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }
}

