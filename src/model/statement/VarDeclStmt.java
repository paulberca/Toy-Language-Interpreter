package model.statement;

import model.exception.MyException;
import model.prgstate.PrgState;
import model.adt.MyIDictionary;
import model.type.IType;

public class VarDeclStmt implements IStmt {
    private final String name;
    private final IType typ;

    public VarDeclStmt(String name, IType typ) {
        this.name = name;
        this.typ = typ;
    }

    @Override
    public PrgState execute(PrgState state) {
        state.getSymTable().add(name, typ.defaultValue());
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        typeEnv.add(name, typ);
        return typeEnv;
    }

    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(name, typ);
    }

    public String toString() {
        return name + ":" + typ.toString();
    }
}

