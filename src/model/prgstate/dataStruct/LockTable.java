package model.prgstate.dataStruct;

import model.adt.MyConcurrentDictionary;
import model.adt.MyIDictionary;
import model.exception.MyException;

import java.util.Map;

public class LockTable implements ILockTable {
    private final MyIDictionary<Integer, Integer> lockTbl;
    private int address;

    public LockTable() {
        lockTbl = new MyConcurrentDictionary<>();
        address = 0;
    }

    @Override
    public Integer get(Integer key) throws MyException {
        synchronized (lockTbl) {
            return lockTbl.lookup(key);
        }
    }

    @Override
    public boolean isDefined(Integer key) {
        synchronized (lockTbl) {
            return lockTbl.isDefined(key);
        }
    }

    @Override
    public void update(Integer key, Integer value) {
        synchronized (lockTbl) {
            lockTbl.add(key, value);
        }
    }

    @Override
    public String toString() {
        return lockTbl.toString();
    }

    @Override
    public synchronized Integer getFreeLocation() {
        address++;
        return address;
    }

    @Override
    public Map<Integer, Integer> getContent() {
        synchronized (lockTbl) {
            return lockTbl.getContent();
        }
    }
}
