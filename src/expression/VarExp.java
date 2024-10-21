package expression;

import value.Value;

public class VarExp implements Exp {
    private String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public Value eval(/*MyIDictionary<String, Value> tbl*/) throws /*My*/Exception {
//        return tbl.lookup(id);
        return null;
    }
}

