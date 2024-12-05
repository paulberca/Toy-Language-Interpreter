package model.statement;

import model.exception.MyException;
import model.expression.IExpression;
import model.prgstate.PrgState;
import model.type.*;
import model.value.*;

import java.io.BufferedReader;

public class ReadFile implements IStmt {
    IExpression exp;
    String varName;

    public ReadFile(IExpression exp, String varName) {
        this.exp = exp;
        this.varName = varName;
    }

    @Override
    public String toString() {
        return "ReadFile(" + exp.toString() + ", " + varName + ")";
    }

    @Override
    public IStmt deepCopy() {
        return new ReadFile(exp.deepCopy(), varName);
    }

    @Override
    public PrgState execute(PrgState prg) throws MyException {
        if (!prg.getSymTable().isDefined(varName)) {
            throw new MyException("Variable " + varName + " is not defined");
        }
        if (!prg.getSymTable().lookup(varName).getType().equals(new IntType())) {
            throw new MyException("Variable " + varName + " is not an integer");
        }

        IValue val;
        try {
            val = exp.eval(prg.getSymTable(), prg.getHeap());
        } catch (Exception e) {
            throw new MyException("Error evaluating expression: " + e.getMessage());
        }
        if (!val.getType().equals(new StringType())) {
            throw new MyException("File name is not a string");
        }


        BufferedReader reader;
        try {
            reader = prg.getFileTable().lookup(val.toString());
            String line = reader.readLine();
            if (line == null) {
                throw new MyException("End of file reached");
            } else {
                IntValue newValue = new IntValue(Integer.parseInt(line));
                prg.getSymTable().update(varName, newValue);
            }
        } catch (Exception e) {
            throw new MyException("Error reading from file: " + e.getMessage());
        }

        return null;
    }
}
