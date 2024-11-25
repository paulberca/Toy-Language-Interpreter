package model.adt;

import model.exception.UndefinedVariable;

import java.util.Map;

public interface MyIDictionary<TKey, TValue> {
    boolean isDefined(TKey key);

    void update(TKey key, TValue val) throws UndefinedVariable;

    TValue lookup(TKey key) throws UndefinedVariable;

    void add(TKey key, TValue val);

    void delete(TKey key) throws UndefinedVariable;




    // ugly stuff used for garbage collector

    void setContent(Map<TKey, TValue> newDict);
    Map<TKey, TValue> getContent();
}
