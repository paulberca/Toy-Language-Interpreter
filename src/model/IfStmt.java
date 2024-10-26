package model;

import adt.*;
import expression.*;
import type.*;
import value.*;

public class IfStmt implements IStmt {
    private IExpression exp;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt(IExpression exp, IStmt thenS, IStmt elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    public String toString() {
        return "(IF(" + exp.toString() + ") THEN(" + thenS.toString() + ") ELSE(" + elseS.toString() + "))";
    }

//    public PrgState execute(PrgState state) throws MyException {
//        MyIDictionary<String, IValue> symTbl = state.getSymTable();
//        IValue val = exp.eval(symTbl);
//
//        if (!val.getType().equals(new BoolType())) {
//            throw new MyException("If condition is not a boolean");
//        }
//
//        BoolValue v = (BoolValue) val;
//        if (v.getVal()) {
//            state.getExeStack().push(thenS);
//        } else {
//            state.getExeStack().push(elseS);
//        }
//
//        return state;
//    }
}

