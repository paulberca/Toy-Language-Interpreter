package model.prgstate.dataStruct;

import javafx.util.Pair;
import model.adt.MyIList;
import model.statement.IStmt;

import java.util.Map;

public interface IProcTable {
    public boolean containsFunction(String name);

    public Pair<MyIList<String>, IStmt> returnBody(String address);

    public void addToHeap(String fname, Pair<MyIList<String>, IStmt> body);

    public Map<String, Pair<MyIList<String>, IStmt>> getContent();

    public void setContent(Map<String, Pair<MyIList<String>, IStmt>> newContent);

    public String toString();
}