package expression;

import type.IntType;
import value.IntValue;
import value.Value;

public class ArithExp implements Exp {
    private Exp e1;
    private Exp e2;
    private int op; //1-plus, 2-minus, 3-star, 4-divide

    public ArithExp(Exp e1, Exp e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        if (op < 1 || op > 4)
            throw new IllegalArgumentException("Invalid operator");
        this.op = op;
    }

    @Override
    public Value eval(/*MyIDictionary<String, Value> tbl*/) throws /*My*/Exception {
        Value v1, v2;
        v1 = e1.eval(/*tbl*/);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(/*tbl*/);
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
                        throw new /*My*/Exception("division by zero");
                    return new IntValue(n1 / n2);
                }
            }
            else
                throw new /*My*/Exception("second operand is not an integer");
        }
        throw new /*My*/Exception("first operand is not an integer");
    }
}

