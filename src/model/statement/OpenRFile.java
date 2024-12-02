package model.statement;

import model.exception.MyException;
import model.expression.*;
import model.prgstate.PrgState;
import model.prgstate.dataStruct.IFileTable;
import model.type.*;
import model.value.*;

import java.io.BufferedReader;
import java.io.FileReader;

public class OpenRFile implements IStmt {
    IExpression exp;

    public OpenRFile(IExpression exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "openRFile(" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState prg) throws MyException {
        IValue val;
        try {
            val = exp.eval(prg.getSymTable(), prg.getHeap());
        } catch (Exception e) {
            throw new MyException("Error evaluating expression: " + e.getMessage());
        }
        if (!val.getType().equals(new StringType())) {
            throw new MyException("File name is not a string");
        }

        IFileTable fileTable = prg.getFileTable();
        if (fileTable.isDefined(val.toString())) {
            throw new MyException("File is already in the file table");
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(val.toString()));
            fileTable.add(val.toString(), reader);
        } catch (Exception e) {
            throw new MyException("File opening error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new OpenRFile(exp.deepCopy());
    }
}
