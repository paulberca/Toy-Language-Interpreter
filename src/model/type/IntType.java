package model.type;

public class IntType implements IType {
    public boolean equals(IType type) {
        return type instanceof IntType;
    }

    public String toString() {
        return "IntType";
    }
}

