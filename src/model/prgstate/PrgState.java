package model.prgstate;

import model.adt.*;
import model.statement.*;
import model.value.*;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, IValue> symTable;
    private MyIList<IValue> out;
    private IStmt originalProgram;  // optional

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, IValue> symtbl, MyIList<IValue> ot, IStmt prg) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        originalProgram = prg.deepCopy();
        stk.push(prg);
    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public MyIDictionary<String, IValue> getSymTable() {
        return symTable;
    }

    public MyIList<IValue> getOut() {
        return out;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public String toString() {
        return "ExeStack:\n" + exeStack.toString() + "\nSymTable:\n" + symTable.toString() + "\nOut:\n" + out.toString() + "\n";
    }

    public void setExeStack(MyIStack<IStmt> stk) {
        exeStack = stk;
    }

    public void setSymTable(MyIDictionary<String, IValue> symtbl) {
        symTable = symtbl;
    }

    public void setOut(MyIList<IValue> ot) {
        out = ot;
    }

    public void setOriginalProgram(IStmt prg) {
        originalProgram = prg;
    }
}
