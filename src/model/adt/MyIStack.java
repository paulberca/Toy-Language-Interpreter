package model.adt;

import model.exception.StackException;

public interface MyIStack<T> {
    void push(T value);

    T pop() throws StackException;

    T peek() throws StackException;

    boolean isEmpty();

    String toString();
}