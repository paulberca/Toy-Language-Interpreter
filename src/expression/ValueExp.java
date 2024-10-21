package expression;

import value.Value;

public class ValueExp implements Exp {
    private Value e;

    public ValueExp(Value e) {
        this.e = e;
    }

    @Override
    public Value eval(/*MyIDictionary<String, Value> tbl*/) throws /*My*/Exception {
        return e;
    }
}

