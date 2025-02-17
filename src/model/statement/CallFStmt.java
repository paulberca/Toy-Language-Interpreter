package model.statement;

import javafx.util.Pair;
import model.adt.MyIDictionary;
import model.adt.MyIList;
import model.exception.MyException;
import model.expression.IExpression;
import model.prgstate.PrgState;
import model.prgstate.dataStruct.IHeap;
import model.prgstate.dataStruct.IProcTable;
import model.prgstate.dataStruct.ISymTable;
import model.prgstate.dataStruct.SymTable;
import model.type.IType;
import model.value.IValue;

public class CallFStmt implements IStmt {
    private final String fname;
    private final MyIList<IExpression> params;

    public CallFStmt(String name, MyIList<IExpression> parameters) {
        fname = name;
        params = parameters;
    }

    @Override
    public String toString() {
        return "call " + fname + "(" + params.toString() + ")";
    }

    @Override
    public IStmt deepCopy() {
        return new CallFStmt(fname, params);
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IProcTable procTable = state.getProcTable();
        if (!procTable.containsFunction(fname)) {
            throw new MyException("Function " + fname + " not found");
        }

        Pair<MyIList<String>, IStmt> argList = procTable.getContent().get(fname);
        MyIList<String> args = argList.getKey();
        IStmt body = argList.getValue();

        ISymTable symTable = state.getSymTable();
        IHeap heap = state.getHeap();
        ISymTable newSymTable = new SymTable();

        for (int i = 0; i < args.size(); i++) {
            IValue value = params.get(i).eval(symTable, heap);

            newSymTable.add(args.get(i), value);
        }

        state.getSymTablesStack().push(newSymTable);
        state.getExeStack().push(new ReturnStmt());
        state.getExeStack().push(body);

        return null;
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return typeEnv;
    }
}
