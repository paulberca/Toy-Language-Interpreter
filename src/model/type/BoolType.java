package model.type;

import model.value.*;

public class BoolType implements IType {
    public boolean equals(IType type) {
        return type instanceof BoolType;
    }

    public String toString() {
        return "BoolType";
    }

    @Override
    public IValue defaultValue() {
        return new BoolValue(false);
    }
}

