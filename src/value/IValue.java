package value;

import type.IType;

public interface IValue {
    boolean equals(IValue v);
    IType getType();
}

