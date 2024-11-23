package model.prgstate.dataStruct;

import model.adt.MyDictionary;
import model.adt.MyIDictionary;
import model.value.IValue;

public class SymTable implements ISymTable {
    private final MyIDictionary<String, IValue> symTable;

    public SymTable() {
        symTable = new MyDictionary<>();
    }

    @Override
    public boolean isDefined(String key) {
        return symTable.isDefined(key);
    }

    @Override
    public void update(String key, IValue val) {
        symTable.update(key, val);
    }

    @Override
    public IValue lookup(String key) {
        return symTable.lookup(key);
    }

    @Override
    public void add(String key, IValue val) {
        symTable.add(key, val);
    }

    @Override
    public String toString() {
        return symTable.toString();
    }
}
