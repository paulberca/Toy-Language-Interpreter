package model;

import type.IType;

public class VarDeclStmt implements IStmt {
    private String name;
    private IType typ;

    public VarDeclStmt(String name, IType typ) {
        this.name = name;
        this.typ = typ;
    }
}

