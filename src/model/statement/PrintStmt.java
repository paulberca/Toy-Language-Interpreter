package model.statement;

import model.exception.MyException;
import model.expression.IExpression;
import model.prgstate.PrgState;
import model.prgstate.dataStruct.IOutput;
import model.value.IValue;

public class PrintStmt implements IStmt {
    private final IExpression exp;

    public PrintStmt(IExpression exp) {
        this.exp = exp;
    }

    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {
        IValue val = exp.eval(state.getSymTable(), state.getHeap());
        IOutput out = state.getOut();
        out.add(val);
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }
}

