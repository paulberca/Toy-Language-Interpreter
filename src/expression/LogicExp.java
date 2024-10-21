package expression;

import type.BoolType;
import value.BoolValue;
import value.Value;

public class LogicExp implements Exp {
    private Exp e1;
    private Exp e2;
    private int op;

    public LogicExp(Exp e1, Exp e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Value eval(/*MyIDictionary<String, Value> tbl*/) throws /*My*/Exception {
        Value v1, v2;
        v1 = e1.eval(/*tbl*/);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(/*tbl*/);
            if (v2.getType().equals(new BoolType())) {
                BoolValue b1 = (BoolValue) v1;
                BoolValue b2 = (BoolValue) v2;
                boolean n1, n2;
                n1 = b1.getVal();
                n2 = b2.getVal();
                if (op == 1) return new BoolValue(n1 && n2);
                if (op == 2) return new BoolValue(n1 || n2);
            }
            else
                throw new /*My*/Exception("second operand is not a boolean");
        }
        throw new /*My*/Exception("first operand is not a boolean");
    }
}

