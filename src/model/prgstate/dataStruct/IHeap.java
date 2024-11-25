package model.prgstate.dataStruct;

import model.exception.MyException;
import model.value.IValue;

import java.util.Map;

public interface IHeap {
    int allocate(IValue value);
    IValue read(int address) throws MyException;
    void write(int address, IValue value) throws MyException;
    void deallocate(int address) throws MyException;





    // ugly stuff used for garbage collector

    void setContent(Map<Integer, IValue> newHeap);
    Map<Integer, IValue> getContent();
}
