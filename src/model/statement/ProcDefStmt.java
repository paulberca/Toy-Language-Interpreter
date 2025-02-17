package model.statement;

import javafx.util.Pair;
import model.adt.MyIDictionary;
import model.adt.MyIList;
import model.exception.MyException;
import model.prgstate.PrgState;
import model.type.IType;

public class ProcDefStmt implements IStmt {
    private final String name;
    private final MyIList<String> params;
    private final IStmt body;

    public ProcDefStmt(String name, MyIList<String> params, IStmt body) {
        this.name = name;
        this.params = params;
        this.body = body;
    }

    @Override
    public String toString() {
        return "proc " + name + "(" + params.toString() + ") {" + body.toString() + "}";
    }

    @Override
    public IStmt deepCopy() {
        return new ProcDefStmt(name, params, body.deepCopy());
    }

    @Override
    public PrgState execute(PrgState state) {
        Pair<MyIList<String>, IStmt> content = new Pair<>(params, body);
        state.getProcTable().addToHeap(name, content);

        return null;
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return typeEnv;
    }
}
