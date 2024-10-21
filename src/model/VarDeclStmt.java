package model;

import type.Type;

public class VarDeclStmt implements IStmt {
    private String name;
    private Type typ;

    public VarDeclStmt(String name, Type typ) {
        this.name = name;
        this.typ = typ;
    }
}

