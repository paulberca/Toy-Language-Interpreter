package model.statement;

import model.adt.MyIDictionary;
import model.exception.MyException;
import model.expression.IExpression;
import model.prgstate.PrgState;
import model.type.BoolType;
import model.type.IType;

public class CondAssignStmt implements IStmt {
    private final String var;
    private final IExpression exp1;
    private final IExpression exp2;
    private final IExpression exp3;

    public CondAssignStmt(String var, IExpression exp1, IExpression exp2, IExpression exp3) {
        this.var = var;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
    }

    public String toString() {
        return var + "=(" + exp1.toString() + ")?" + exp2.toString() + ":" + exp3.toString();
    }

    public PrgState execute(PrgState state) {
        IStmt ifStmt = new IfStmt(exp1, new AssignStmt(var, exp2), new AssignStmt(var, exp3));
        state.getExeStack().push(ifStmt);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CondAssignStmt(var, exp1.deepCopy(), exp2.deepCopy(), exp3.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType typeVar = typeEnv.lookup(var);
        IType typeExp1 = exp1.typecheck(typeEnv);
        IType typeExp2 = exp2.typecheck(typeEnv);
        IType typeExp3 = exp3.typecheck(typeEnv);

        if (!typeExp1.equals(new BoolType())) {
            throw new MyException("The condition of the conditional assignment is not a boolean");
        }

        if (typeVar.equals(typeExp2) && typeVar.equals(typeExp3)) {
            return typeEnv;
        }
        else {
            throw new MyException("The two expressions of the conditional assignment have different types");
        }
    }
}
