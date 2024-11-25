package model.statement;

import model.exception.MyException;
import model.expression.IExpression;
import model.prgstate.PrgState;
import model.type.StringType;
import model.value.IValue;

import java.io.BufferedReader;

public class CloseRFile implements IStmt {
    IExpression exp;

    public CloseRFile(IExpression exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "CloseRFile(" + exp.toString() + ")";
    }

    @Override
    public IStmt deepCopy() {
        return new CloseRFile(exp.deepCopy());
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

        try (BufferedReader reader = prg.getFileTable().lookup(val.toString())) {
            reader.close();
            prg.getFileTable().delete(val.toString());
        } catch (Exception e) {
            throw new MyException("Error closing file: " + e.getMessage());
        }

        return prg;
    }
}
