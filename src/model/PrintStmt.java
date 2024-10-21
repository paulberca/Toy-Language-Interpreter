package model;

import expression.Exp;

public class PrintStmt implements IStmt {
    Exp exp;

    public PrintStmt(Exp exp) {
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

