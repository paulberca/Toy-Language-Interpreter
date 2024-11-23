package model.expression;

import model.exception.*;
import model.prgstate.dataStruct.ISymTable;
import model.type.*;
import model.value.*;

public class ArithExp implements IExpression {
    private final IExpression e1;
    private final IExpression e2;
    private final int op; //1-plus, 2-minus, 3-star, 4-divide

    public ArithExp(IExpression e1, IExpression e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        if (op < 1 || op > 4)
            throw new IllegalArgumentException("Invalid operator");
        this.op = op;
    }

    @Override
    public IValue eval(ISymTable tbl) throws MyException {
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
                        throw new DivByZero("Division by zero");
                    return new IntValue(n1 / n2);
                }
                throw new UndefinedVariable(op + " is not a valid operator");
            }
            else throw new UndefinedVariable("Second operand <" + v2 + "> is not an integer");
        }
        throw new UndefinedVariable("First operand <" + v1 + ">is not an integer");
    }

    @Override
    public IExpression deepCopy() {
        return new ArithExp(e1.deepCopy(), e2.deepCopy(), op);
    }

    public String toString() {
        String opStr = "";
        if (op == 1) opStr = "+";
        if (op == 2) opStr = "-";
        if (op == 3) opStr = "*";
        if (op == 4) opStr = "/";
        return e1.toString() + opStr + e2.toString();
    }
}
