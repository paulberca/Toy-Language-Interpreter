package value;

import type.Type;

public interface IValue {
    String toString();
    boolean equals(IValue v);
    Type getType();
}

