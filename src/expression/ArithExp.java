package expression;

import exception.*;
import type.*;
import value.*;
import adt.*;

public class ArithExp implements IExpression {
    private IExpression e1;
    private IExpression e2;
    private int op; //1-plus, 2-minus, 3-star, 4-divide

    public ArithExp(IExpression e1, IExpression e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        if (op < 1 || op > 4)
            throw new IllegalArgumentException("Invalid operator");
        this.op = op;
    }

    @Override
    public IValue eval(MyIDictionary<String, IValue> tbl) throws MyException {
        IValue v1, v2;
        v1 = e1.eval(tbl);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op == 1) return new IntValue(n1 + n2);
                if (op == 2) return new IntValue(n1 - n2);
                if (op == 3) return new IntValue(n1 * n2);
                if (op == 4) {
                    if (n2 == 0)
                        throw new MyException("division by zero");
                    return new IntValue(n1 / n2);
                }
            }
            else
                throw new MyException("second operand is not an integer");
        }
        throw new MyException("first operand is not an integer");
    }
}

