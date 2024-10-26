package adt;

public interface MyIDictionary<TKey, TValue> {
    boolean isDefined(TKey key);

    void update(TKey key, TValue val);

    TValue lookup(TKey key);

    void add(TKey key, TValue val);

    String toString();
}
