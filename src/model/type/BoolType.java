package model.type;

public class BoolType implements IType {
    public boolean equals(IType type) {
        return type instanceof BoolType;
    }

    public String toString() {
        return "BoolType";
    }
}

