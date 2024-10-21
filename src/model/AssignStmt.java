package model;

import expression.Exp;

public class AssignStmt implements IStmt {
    String id;
    Exp exp;

    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    public String toString() {
        return id + "=" + exp.toString();
    }

//    public PrgState execute(PrgState state) throws MyException {
//        MyIStack<IStmt> stk = state.getStk();
//        MyIDictionary<String, Value> symTbl = state.getSymTable();
//
//        if (symTbl.isDefined(id)) {
//            Value val = exp.eval(symTbl));
//            Type typId = (symTbl.lookup(id)).getType();
//            if (val.getType().equals(typId)) {
//                symTbl.update(id, val);
//            }
//            else {
//                throw new MyException("declared type of variable " + id + " and type of the assigned expression do not match");
//            }
//        }
//
//        return state;
//    }
}

