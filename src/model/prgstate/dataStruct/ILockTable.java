package model.prgstate.dataStruct;

import model.exception.MyException;

import java.util.Map;

public interface ILockTable {
    Integer get(Integer key) throws MyException;

    boolean isDefined(Integer key);

    void update(Integer key, Integer value);

    Integer getFreeLocation();

    Map<Integer, Integer> getContent();
}
