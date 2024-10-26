package model.expression;

import model.adt.*;
import model.exception.*;
import model.value.*;
import model.type.*;

public class LogicExp implements IExpression {
    private IExpression e1;
    private IExpression e2;
    LogicalOperator op;

    public LogicExp(IExpression e1, IExpression e2, LogicalOperator op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl) throws MyException {
        IValue v1, v2;
        v1 = e1.eval(tbl);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(tbl);
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
}

