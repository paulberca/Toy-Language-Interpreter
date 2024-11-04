package model.value;

import model.type.*;

public class StringValue implements IValue {
    private String value;

    public StringValue(String value) {
        this.value = value;
    }

    public StringValue() {
        this.value = "";
    }

    public String getVal() {
        return value;
    }

    public String toString() {
        return value;
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public boolean equals(IValue v) {
        if (!v.getType().equals(new StringType())) {
            return false;
        }
        StringValue val = (StringValue) v;
        return this.value.equals(val.getVal());
    }

    @Override
    public IValue deepCopy() {
        return new StringValue(value); // even though String is immutable?
    }
}
