package model.statement;

import model.adt.MyIDictionary;
import model.exception.MyException;
import model.expression.IExpression;
import model.expression.NegateExp;
import model.prgstate.PrgState;
import model.type.IType;

public class RepeatUntilStmt implements IStmt {
    private final IStmt stmt;
    private final IExpression exp;

    public RepeatUntilStmt(IStmt stmt, IExpression exp) {
        this.stmt = stmt;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "repeat {" + stmt.toString() + "} until (" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) {
        // on the stack we push this: stmt1; (while(!exp2) stmt1;)
        state.getExeStack().push(new CompStmt(stmt, new WhileStmt(new NegateExp(exp), stmt)));
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new RepeatUntilStmt(stmt.deepCopy(), exp.deepCopy());
    }
}
