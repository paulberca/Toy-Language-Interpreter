package model.prgstate.dataStruct;

import model.exception.UndefinedVariable;
import model.value.IValue;

public interface ISymTable {
    boolean isDefined(String key);

    void update(String key, IValue val) throws UndefinedVariable;

    IValue lookup(String key) throws UndefinedVariable;

    void add(String key, IValue val);
}
