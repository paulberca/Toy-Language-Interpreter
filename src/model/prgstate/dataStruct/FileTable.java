package model.prgstate.dataStruct;

import model.adt.MyDictionary;
import model.adt.MyIDictionary;

import java.io.BufferedReader;

public class FileTable implements IFileTable {
    private final MyIDictionary<String, BufferedReader> fileTable;

    public FileTable() {
        fileTable = new MyDictionary<>();
    }

    @Override
    public boolean isDefined(String key) {
        return fileTable.isDefined(key);
    }

    @Override
    public BufferedReader lookup(String key) {
        return fileTable.lookup(key);
    }

    @Override
    public void add(String key, BufferedReader val) {
        fileTable.add(key, val);
    }

    @Override
    public void delete(String key) {
        fileTable.delete(key);
    }

    @Override
    public String toString() {
        return fileTable.toString();
    }
}
