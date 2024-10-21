package expression;

import value.Value;

public interface Exp {
    Value eval(/*MyIDictionary<String, Value> tbl*/) throws /*My*/Exception;
}

