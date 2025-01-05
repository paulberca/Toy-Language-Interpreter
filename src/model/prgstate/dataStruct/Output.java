package model.prgstate.dataStruct;

import model.adt.MyIList;
import model.adt.MyList;
import model.value.IValue;

import java.util.List;

public class Output implements IOutput {
    private final MyIList<IValue> list;

    public Output() {
        list = new MyList<>();
    }

    @Override
    public void add(IValue value) {
        list.add(value);
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public List<IValue> toList() {
        return list.toList();
    }
}
