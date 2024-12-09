package model.statement;

import model.adt.MyIDictionary;
import model.exception.MyException;
import model.prgstate.PrgState;
import model.prgstate.dataStruct.ExeStack;
import model.type.IType;

public class ForkStmt implements IStmt {
    private final IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) {
        return new PrgState(new ExeStack(), state.getSymTable().deepCopy(), state.getOut(), state.getFileTable(), state.getHeap(), stmt);
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
