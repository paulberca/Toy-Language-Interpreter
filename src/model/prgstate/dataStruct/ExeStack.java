package model.prgstate.dataStruct;

import model.adt.MyIStack;
import model.adt.MyStack;
import model.exception.StackException;
import model.statement.IStmt;

public class ExeStack implements IExeStack {
    private final MyIStack<IStmt> stack;

    public ExeStack() {
        stack = new MyStack<>();
    }

    @Override
    public void push(IStmt value) {
        stack.push(value);
    }

    @Override
    public IStmt pop() throws StackException {
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
