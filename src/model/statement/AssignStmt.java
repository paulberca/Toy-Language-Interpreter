package model.statement;

import model.exception.MyException;
import model.expression.IExpression;
import model.prgstate.PrgState;
import model.prgstate.dataStruct.ISymTable;
import model.value.IValue;
import model.type.IType;

public class AssignStmt implements IStmt {
    private final String id;
    private final IExpression exp;

    public AssignStmt(String id, IExpression exp) {
        this.id = id;
        this.exp = exp;
    }

    public String toString() {
        return id + "=" + exp.toString();
    }

    public PrgState execute(PrgState state) throws MyException {
        ISymTable symTbl = state.getSymTable();

        if (symTbl.isDefined(id)) {
            IValue val = exp.eval(symTbl, state.getHeap());
            IType typId = (symTbl.lookup(id)).getType();
            if (val.getType().equals(typId)) {
                symTbl.update(id, val);
            }
            else {
                throw new MyException("Declared model.type of variable " + id + " and model.type of the assigned model.expression do not match");
            }
        }
        else {
            throw new MyException("The used variable " + id + " was not declared before");
        }

        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new AssignStmt(id, exp.deepCopy());
    }
}

