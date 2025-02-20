package model.prgstate.dataStruct;

import model.exception.UndefinedVariable;
import java.io.BufferedReader;
import java.util.List;

public interface IFileTable {
    boolean isDefined(String key);

    BufferedReader lookup(String key);

    void add(String key, BufferedReader val);

    void delete(String key) throws UndefinedVariable;

    List<String> toList();
}
