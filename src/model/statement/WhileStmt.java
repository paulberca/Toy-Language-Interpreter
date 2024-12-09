package model.statement;

import model.adt.MyIDictionary;
import model.exception.MyException;
import model.expression.IExpression;
import model.prgstate.PrgState;
import model.type.BoolType;
import model.type.IType;
import model.value.BoolValue;
import model.value.IValue;

public class WhileStmt implements IStmt {
    private final IExpression condition;
    private final IStmt statement;

    public WhileStmt(IExpression condition, IStmt statement) {
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "while (" + condition.toString() + ") {" + statement.toString() + "}";
    }

    @Override
    public IStmt deepCopy() {
        return new WhileStmt(condition.deepCopy(), statement.deepCopy());
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IValue value = condition.eval(state.getSymTable(), state.getHeap());

        if (!(value.getType() instanceof BoolType)) {
            throw new MyException("Condition is not a boolean");
        }

        if (((BoolValue) value).getVal()) {
            state.getExeStack().push(this);
            state.getExeStack().push(statement);
        }

        return null;
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        if ((new BoolType()).equals(condition.typecheck(typeEnv))) {
            statement.typecheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else {
            throw new MyException("The condition of WHILE has not the type bool");
        }
    }
}
