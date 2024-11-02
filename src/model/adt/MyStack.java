package model.adt;

import model.exception.StackException;

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
    public T pop() throws StackException {
        if (stack.isEmpty()) {
            throw new StackException("Stack is empty");
        }

        return stack.pop();
    }

    @Override
    public T peek() throws StackException {
        if (stack.isEmpty()) {
            throw new StackException("Stack is empty");
        }

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
