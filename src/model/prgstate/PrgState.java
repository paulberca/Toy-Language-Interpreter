package model.prgstate;

import model.prgstate.dataStruct.*;
import model.statement.*;

public class PrgState {
    private IExeStack exeStack;
    private ISymTable symTable;
    private IOutput out;
    private IFileTable fileTable;
    private IHeap heap;
    private IStmt originalProgram;

    public PrgState(IExeStack stk, ISymTable symtbl, IOutput ot, IFileTable ft, IHeap hp,  IStmt prg) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = ft;
        heap = hp;
        originalProgram = prg.deepCopy();
        stk.push(prg);
    }

    public IExeStack getExeStack() {
        return exeStack;
    }

    public ISymTable getSymTable() {
        return symTable;
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

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public String toString() {
        return "ExeStack:\n" + exeStack.toString() + "\nSymTable:\n" + symTable.toString() + "\nOut:\n" + out.toString() + "\n\nHeap:\n" + heap.toString() + "\nFileTable:\n" + fileTable.toString() + "\n";
    }

    public void setExeStack(IExeStack stk) {
        exeStack = stk;
    }

    public void setSymTable(ISymTable symtbl) {
        symTable = symtbl;
    }

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
