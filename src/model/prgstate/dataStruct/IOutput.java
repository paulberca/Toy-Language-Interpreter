package model.prgstate.dataStruct;

import model.value.IValue;

import java.util.List;

public interface IOutput {
    void add(IValue value);

    List<IValue> toList();
}
