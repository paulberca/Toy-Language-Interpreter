package model.type;

import model.value.*;

public class StringType implements IType{
    public boolean equals(IType type) {
        return type instanceof StringType;
    }

    @Override
    public IValue defaultValue() {
        return new StringValue("");
    }

    public String toString() {
        return "StringType";
    }
}
