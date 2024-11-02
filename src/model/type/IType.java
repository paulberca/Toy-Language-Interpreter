package model.type;

import model.value.IValue;

public interface IType {
    boolean equals(IType v);

    IValue defaultValue();
}
