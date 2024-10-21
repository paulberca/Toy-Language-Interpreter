package expression;

import value.Value;

public interface Exp {
    public Value eval(/*MyIDictionary<String, Value> tbl*/) throws /*My*/Exception;
}

