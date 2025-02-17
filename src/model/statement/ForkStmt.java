package model.statement;

import model.adt.MyIDictionary;
import model.adt.MyIStack;
import model.adt.MyStack;
import model.exception.MyException;
import model.prgstate.PrgState;
import model.prgstate.dataStruct.ExeStack;
import model.prgstate.dataStruct.ISymTable;
import model.type.IType;

public class ForkStmt implements IStmt {
    private final IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIStack<ISymTable> symTables = new MyStack<>();
        for (ISymTable symTable : state.getSymTablesStack().toList()) {
            symTables.push(symTable.deepCopy());
        }

        return new PrgState(new ExeStack(), symTables, state.getOut(), state.getFileTable(), state.getHeap(), state.getLockTable(), state.getProcTable(), stmt);
    }

    @Override
    public IStmt deepCopy() {
        return new ForkStmt(stmt.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return stmt.typecheck(typeEnv);
    }


    @Override
    public String toString() {
        return "fork(" + stmt.toString() + ")";
    }
}
