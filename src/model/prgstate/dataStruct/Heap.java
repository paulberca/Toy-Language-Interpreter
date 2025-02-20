package model.prgstate.dataStruct;

import model.adt.MyConcurrentDictionary;
import model.adt.MyIDictionary;
import model.exception.MyException;
import model.exception.UndefinedVariable;
import model.value.IValue;

import java.util.Map;

public class Heap implements IHeap {
    private final MyIDictionary<Integer, IValue> heap;
    private int firstFree;

    public Heap() {
        heap = new MyConcurrentDictionary<>();
        firstFree = 1;
    }

    @Override
    public int allocate(IValue value) {
        heap.add(firstFree, value);
        firstFree++;
        return firstFree - 1;
    }

    @Override
    public IValue read(int address) throws MyException {
        try {
            return heap.lookup(address);
        } catch (UndefinedVariable e) {
            throw new MyException("Address " + address + " is not allocated in the heap.");
        }
    }

    @Override
    public void write(int address, IValue value) throws MyException {
        if (!heap.isDefined(address)) {
            throw new MyException("Address " + address + " is not allocated in the heap.");
        }
        heap.update(address, value);
    }

    @Override
    public void deallocate(int address) throws MyException {
        try {
            heap.delete(address);
        } catch (UndefinedVariable e) {
            throw new MyException("Address " + address + " is not allocated in the heap.");
        }
    }

    @Override
    public String toString() {
        return heap.toString();
    }





    // stuff used for garbage collector

    @Override
    public void setContent(Map<Integer, IValue> newHeap) {
        heap.setContent(newHeap);
    }

    @Override
    public Map<Integer, IValue> getContent() {
        return heap.getContent();
    }
}
