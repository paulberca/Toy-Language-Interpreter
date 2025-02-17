package model.prgstate;

import model.adt.MyIStack;
import model.exception.MyException;
import model.exception.StackException;
import model.prgstate.dataStruct.*;
import model.statement.*;

public class PrgState {
    private IExeStack exeStack;
    private MyIStack<ISymTable> symTables;
    private IOutput out;
    private IFileTable fileTable;
    private IHeap heap;
    private IStmt originalProgram;
    private final ILockTable lockTable;
    private final int id;
    private static int nextId = 0;
    private IProcTable procTable;

    public PrgState(IExeStack stk, MyIStack<ISymTable> symtbl, IOutput ot, IFileTable ft, IHeap hp, ILockTable locktbl, IProcTable procTbl, IStmt prg) {
        id = getNextID();
        exeStack = stk;
        symTables = symtbl;
        if (symTables.isEmpty()) {          // only for the first program state
            symTables.push(new SymTable());
        }
        out = ot;
        fileTable = ft;
        heap = hp;
        lockTable = locktbl;
        procTable = procTbl;
        originalProgram = prg.deepCopy();
        stk.push(prg);
    }

    public synchronized int getNextID() {
        nextId++;
        return nextId;
    }

    public int getId() {
        return id;
    }

    public boolean isNotCompleted() {
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException, StackException {
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    public IExeStack getExeStack() {
        return exeStack;
    }

    public ISymTable getSymTable() {
        try {
            return symTables.peek();
        } catch (StackException e) {
            throw new RuntimeException("SymTable stack is empty");
        }
    }

    public MyIStack<ISymTable> getSymTablesStack() {
        return symTables;
    }

    public IOutput getOut() {
        return out;
    }

    public IFileTable getFileTable() {
        return fileTable;
    }

    public IHeap getHeap() {
        return heap;
    }

    public IProcTable getProcTable() {
        return procTable;
    }

    public ILockTable getLockTable() {
        return lockTable;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public String toString() {
        return "ID: " + id
                + "\nExeStack:\n" + exeStack.toString()
                + "\nSymTable:\n" + symTables.toString()
                + "\nOut:\n" + out.toString()
                + "\n\nHeap:\n" + heap.toString()
                + "\nFileTable:\n" + fileTable.toString()
                + "\nLockTable:\n" + lockTable.toString()
                + "\nProcTable:\n" + procTable.toString()
                + "\n";
    }

    public void setExeStack(IExeStack stk) {
        exeStack = stk;
    }

//    error but not used anyways
//    public void setSymTable(ISymTable symtbl) {
//        symTables = symtbl;
//    }

    public void setOut(IOutput ot) {
        out = ot;
    }

    public void setFileTable(IFileTable ft) {
        fileTable = ft;
    }

    public void setHeap(IHeap hp) {
        heap = hp;
    }

    public void setOriginalProgram(IStmt prg) {
        originalProgram = prg;
    }
}
