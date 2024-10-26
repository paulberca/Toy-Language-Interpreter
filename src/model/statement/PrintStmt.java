package model.statement;

import model.exception.MyException;
import model.expression.IExpression;
import model.prgstate.PrgState;
import model.adt.*;

public class PrintStmt implements IStmt {
    private IExpression exp;

    public PrintStmt(IExpression exp) {
        this.exp = exp;
    }

    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {
        // code to be added
        return state;
    }
}

