package model.statement;

import model.exception.MyException;
import model.prgstate.PrgState;
import model.type.IType;

public class VarDeclStmt implements IStmt {
    private String name;
    private IType typ;

    public VarDeclStmt(String name, IType typ) {
        this.name = name;
        this.typ = typ;
    }

    @Override
    public PrgState execute(PrgState state) {
        state.getSymTable().add(name, typ.defaultValue());
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(name, typ);
    }

    public String toString() {
        return name + ":" + typ.toString();
    }
}

