package model.type;

import model.value.IValue;
import model.value.RefValue;

public class RefType implements IType {
    IType inner;

    public RefType(IType inner) {
        this.inner = inner;
    }

    public IType getInner() {
        return inner;
    }

    @Override
    public boolean equals(IType other) {
        if (other instanceof RefType) {
            return inner.equals(((RefType) other).getInner());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Ref(" + inner.toString() + ")";
    }

    @Override
    public IValue defaultValue() {
        return new RefValue(0, inner);
    }
}
