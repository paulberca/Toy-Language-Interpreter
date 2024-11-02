package model.value;

import model.type.*;

public class IntValue implements IValue {
    private int intValue;

    public IntValue(int val) {
        intValue = val;
    }

    public IntValue() {
        intValue = 0;
    }

    public int getVal() {
        return intValue;
    }

    public String toString() {
        return "" + intValue;
    }

    @Override
    public IType getType() {
        return new IntType();
    }

    @Override
    public boolean equals(IValue v) {
        if (!v.getType().equals(new IntType())) {
            return false;
        }
        IntValue val = (IntValue) v;
        return this.intValue == val.getVal();
    }

    @Override
    public IValue deepCopy() {
        return new IntValue(intValue);
    }
}
