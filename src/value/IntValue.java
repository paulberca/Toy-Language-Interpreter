package value;

import type.*;

public class IntValue implements IValue {
    private int val;

    public IntValue(int val) {
        this.val = val;
    }

    public IntValue() {
        this.val = 0;
    }

    public int getVal() {
        return val;
    }

    public String toString() {
        return "" + val;
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public boolean equals(IValue v) {
        if (!v.getType().equals(new IntType())) {
            return false;
        }
        IntValue val = (IntValue) v;
        return this.val == val.getVal();
    }
}

