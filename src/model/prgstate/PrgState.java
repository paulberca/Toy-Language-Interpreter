package model.prgstate;

import model.prgstate.dataStruct.*;
import model.statement.*;

public class PrgState {
    private IExeStack exeStack;
    private ISymTable symTable;
    private IOutput out;
    private IFileTable fileTable;
    private IStmt originalProgram;

    public PrgState(IExeStack stk, ISymTable symtbl, IOutput ot, IFileTable ft, IStmt prg) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = ft;
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

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public String toString() {
        return "ExeStack:\n" + exeStack.toString() + "\nSymTable:\n" + symTable.toString() + "\nOut:\n" + out.toString() + "\nFileTable:\n" + fileTable.toString() + "\n";
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

    public void setOriginalProgram(IStmt prg) {
        originalProgram = prg;
    }
}
