package model.value;

import model.type.IType;

public interface IValue {
    boolean equals(IValue v);
    IType getType();
}

