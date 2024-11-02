package model.type;

import model.value.IValue;
import model.value.IntValue;

public class IntType implements IType {
    public boolean equals(IType type) {
        return type instanceof IntType;
    }

    public String toString() {
        return "IntType";
    }

    @Override
    public IValue defaultValue() {
        return new IntValue(0);
    }
}

