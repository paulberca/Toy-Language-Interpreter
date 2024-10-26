package value;

import type.*;

public class BoolValue implements IValue {
    private boolean val;

    public BoolValue(boolean val) {
        this.val = val;
    }

    public BoolValue() {
        this.val = false;
    }

    public boolean getVal() {
        return val;
    }

    public String toString() {
        return "" + val;
    }

    @Override
    public IType getType() {
        return new BoolType();
    }

    @Override
    public boolean equals(IValue v) {
        if (!v.getType().equals(new BoolType())) {
            return false;
        }
        BoolValue val = (BoolValue) v;
        return this.val == val.getVal();
    }
}

