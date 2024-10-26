package adt;

import exception.MyException;

public interface MyIDictionary<TKey, TValue> {
    boolean isDefined(TKey key);

    void update(TKey key, TValue val);

    TValue lookup(TKey key) throws MyException;

    void add(TKey key, TValue val);
}
