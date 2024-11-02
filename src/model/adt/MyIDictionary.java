package model.adt;

import model.exception.UndefinedVariable;
import model.type.IType;

public interface MyIDictionary<TKey, TValue> {
    boolean isDefined(TKey key);

    void update(TKey key, TValue val) throws UndefinedVariable;

    TValue lookup(TKey key) throws UndefinedVariable;

    void declareVariable(TKey key, IType type);

    void add(TKey key, TValue val);
}
