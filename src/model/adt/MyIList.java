package model.adt;

import model.exception.ListException;

import java.util.List;

public interface MyIList<T> {
    void add(T value);

    void remove(int index) throws ListException;

    T get(int index) throws ListException;

    void set(int index, T value) throws ListException;

    int size();

    boolean isEmpty();

    List<T> toList();
}
