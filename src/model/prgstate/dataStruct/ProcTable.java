package model.prgstate.dataStruct;

import javafx.util.Pair;
import model.adt.MyIList;
import model.statement.IStmt;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProcTable implements IProcTable {
    private Map<String, Pair<MyIList<String>, IStmt>> procTable;

    public ProcTable() {
        this.procTable = new ConcurrentHashMap<String, Pair<MyIList<String>, IStmt>>();
    }

    public boolean containsFunction(String name) {
        return this.procTable.containsKey(name);
    }

    public Pair<MyIList<String>, IStmt> returnBody(String address) {
        return this.procTable.get(address);
    }

    public void addToHeap(String fname, Pair<MyIList<String>, IStmt> body) {
        this.procTable.put(fname, body);
    }

    public Map<String, Pair<MyIList<String>, IStmt>> getContent() {
        return this.procTable;
    }


    public void setContent(Map<String, Pair<MyIList<String>, IStmt>> newContent) {
        this.procTable = newContent;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        for (String key : this.procTable.keySet()) {
            s.append(key).append(" -> ").append(this.procTable.get(key)).append("\n");
        }

        return s.toString();
    }
}