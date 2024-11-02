package model.adt;

import model.exception.UndefinedVariable;

public interface MyIDictionary<TKey, TValue> {
    boolean isDefined(TKey key);

    void update(TKey key, TValue val) throws UndefinedVariable;

    TValue lookup(TKey key) throws UndefinedVariable;

    void add(TKey key, TValue val);
}
