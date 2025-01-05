package model.adt;

import model.exception.ListException;

import java.util.List;
import java.util.ArrayList;

public class MyList<T> implements MyIList<T> {
    private List<T> list;

    public MyList() {
        list = new ArrayList<T>();
    }

    @Override
    public void add(T value) {
        list.add(value);
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= list.size()) {
            throw new ListException("Index out of bounds");
        }

        list.remove(index);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= list.size()) {
            throw new ListException("Index out of bounds");
        }

        return list.get(index);
    }

    @Override
    public void set(int index, T value) {
        if (index < 0 || index >= list.size()) {
            throw new ListException("Index out of bounds");
        }

        list.set(index, value);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public List<T> toList() {
        return new ArrayList<T>(list);
    }
}
