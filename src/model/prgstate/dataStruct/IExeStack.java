package model.prgstate.dataStruct;

import model.exception.StackException;
import model.statement.IStmt;

public interface IExeStack {
    void push(IStmt value);

    IStmt pop() throws StackException;

    boolean isEmpty();

    String toString();
}
