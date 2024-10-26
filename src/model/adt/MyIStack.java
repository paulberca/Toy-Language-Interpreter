package model.adt;

public interface MyIStack<T> {
    void push(T value);

    T pop();

    T peek();

    boolean isEmpty();

    String toString();
}