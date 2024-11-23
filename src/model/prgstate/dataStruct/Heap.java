package model.prgstate.dataStruct;

import model.adt.MyDictionary;
import model.adt.MyIDictionary;
import model.exception.MyException;
import model.exception.UndefinedVariable;
import model.value.IValue;

public class Heap implements IHeap {
    private final MyIDictionary<Integer, IValue> heap;
    private int firstFree;

    public Heap() {
        heap = new MyDictionary<>();
        firstFree = 1;
    }

    @Override
    public void allocate(IValue value) {
        heap.add(firstFree, value);
        firstFree++;
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
}
