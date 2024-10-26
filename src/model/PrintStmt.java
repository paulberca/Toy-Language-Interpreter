package model;

import expression.IExpression;

public class PrintStmt implements IStmt {
    private IExpression exp;

    public PrintStmt(IExpression exp) {
        this.exp = exp;
    }

    public String toString() {
        return "print(" + exp.toString() + ")";
    }

//    PrgState execute(PrgState state) throws MyException {
//    ...
//    return state
//    }
}

