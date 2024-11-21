package model.value;

import model.type.IType;
import model.type.RefType;

public class RefValue implements IValue {
    int address;
    IType locationType;

    public RefValue(int address, IType locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return address;
    }

    public IType getLocationType() {
        return locationType;
    }

    @Override
    public boolean equals(IValue v) {
        if (v instanceof RefValue) {
            return address == ((RefValue) v).getAddress() && locationType.equals(((RefValue) v).getLocationType());
        }
        return false;
    }

    @Override
    public IType getType() {
        return new RefType(locationType);
    }

    @Override
    public IValue deepCopy() {
        return new RefValue(address, locationType);
    }
}
