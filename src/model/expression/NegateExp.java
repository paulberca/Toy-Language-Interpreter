package model.expression;

import model.adt.MyIDictionary;
import model.exception.MyException;
import model.prgstate.dataStruct.IHeap;
import model.prgstate.dataStruct.ISymTable;
import model.type.BoolType;
import model.type.IType;
import model.value.BoolValue;
import model.value.IValue;

public class NegateExp implements IExpression {
    private final IExpression exp;

    public NegateExp(IExpression exp) {
        this.exp = exp;
    }

    @Override
    public IValue eval(ISymTable tbl, IHeap hp) throws MyException {
        IValue v = exp.eval(tbl, hp);
        if (v.getType().equals(new BoolType())) {
            BoolValue b = (BoolValue) v;
            return new BoolValue(!b.getVal());
        }
        throw new MyException("Operand is not a boolean");
    }

    public String toString() {
        return "!" + exp.toString();
    }

    @Override
    public IExpression deepCopy() {
        return new NegateExp(exp.deepCopy());
    }

    @Override
    public IType typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType type = exp.typecheck(typeEnv);
        if (type.equals(new BoolType())) {
            return new BoolType();
        }
        throw new MyException("Operand is not a boolean");
    }
}
