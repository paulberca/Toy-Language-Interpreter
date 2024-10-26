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
    public PrgState execute(PrgState state) throws MyException {
        // code to be added
        return state;
    }
}

