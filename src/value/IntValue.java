package value;

import type.IntType;
import type.Type;

public class IntValue implements Value {
    private int val;

    public IntValue(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public String toString() {
        return Integer.toString(val);
    }

    @Override
    public Type getType() {
        return new IntType();
    }
}

