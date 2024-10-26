package model.adt;

public interface MyIList<T> {
    void add(T value);

    void remove(int index);

    T get(int index);

    void set(int index, T value);

    int size();

    boolean isEmpty();
}
