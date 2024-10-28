package model.adt;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private Stack<T> stack;

    public MyStack() {
        stack = new Stack<>();
    }

    @Override
    public void push(T value) {
        stack.push(value);
    }

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public T peek() {
        return stack.peek();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (T value : stack) {
            buffer.append(value).append("\n");
        }
        return buffer.toString();
    }
}
