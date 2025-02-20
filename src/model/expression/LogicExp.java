package model.expression;

import model.adt.MyIDictionary;
import model.exception.*;
import model.prgstate.dataStruct.IHeap;
import model.prgstate.dataStruct.ISymTable;
import model.value.*;
import model.type.*;

public class LogicExp implements IExpression {
    private final IExpression e1;
    private final IExpression e2;
    LogicalOperator op;

    public LogicExp(IExpression e1, IExpression e2, LogicalOperator op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public IValue eval(ISymTable tbl, IHeap hp) throws MyException {
        IValue v1, v2;
        v1 = e1.eval(tbl, hp);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(tbl, hp);
            if (v2.getType().equals(new BoolType())) {
                BoolValue b1 = (BoolValue) v1;
                BoolValue b2 = (BoolValue) v2;
                boolean n1, n2;
                n1 = b1.getVal();
                n2 = b2.getVal();
                if (op == LogicalOperator.AND) return new BoolValue(n1 && n2);
                if (op == LogicalOperator.OR) return new BoolValue(n1 || n2);
            }
            else
                throw new MyException("second operand is not a boolean");
        }
        throw new MyException("first operand is not a boolean");
    }

    public String toString() {
        return e1.toString() + " " + op.toString() + " " + e2.toString();
    }

    @Override
    public IExpression deepCopy() {
        return new LogicExp(e1.deepCopy(), e2.deepCopy(), op);
    }

    @Override
    public IType typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType type1, type2;
        type1 = e1.typecheck(typeEnv);
        type2 = e2.typecheck(typeEnv);
        if (type1.equals(new BoolType())) {
            if (type2.equals(new BoolType())) {
                return new BoolType();
            }
            else
                throw new MyException("second operand is not a boolean");
        }
        throw new MyException("first operand is not a boolean");
    }
}

