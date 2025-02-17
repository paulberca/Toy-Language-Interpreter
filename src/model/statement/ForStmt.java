package model.statement;

import model.adt.MyIDictionary;
import model.exception.MyException;
import model.expression.IExpression;
import model.expression.RelationalExp;
import model.expression.VarExp;
import model.prgstate.PrgState;
import model.type.IType;
import model.type.IntType;

public class ForStmt implements IStmt {
    private final IExpression exp1;
    private final IExpression exp2;
    private final IExpression exp3;
    private final IStmt statement;
    private final String indexName;

    public ForStmt(String indexName, IExpression exp1, IExpression exp2, IExpression exp3, IStmt statement) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        this.statement = statement;
        this.indexName = indexName;
    }

    @Override
    public String toString() {
        return "for (" + indexName + "=" + exp1.toString() + "; " + indexName + "<" + exp2.toString() + "; " + indexName + "=" + exp3.toString() + ") {" + statement.toString() + "}";
    }

    @Override
    public IStmt deepCopy() {
        return new ForStmt(indexName, exp1.deepCopy(), exp2.deepCopy(), exp3.deepCopy(), statement.deepCopy());
    }

    @Override
    public PrgState execute(PrgState state) {
        IStmt varDecl = new VarDeclStmt(indexName, new IntType());
        IStmt assignStmt = new AssignStmt(indexName, exp1);
        IExpression condExp = new RelationalExp(new VarExp(indexName), exp2, 1);
        IStmt incrementStmt = new AssignStmt(indexName, exp3);
        IStmt whileStmt = new WhileStmt(condExp, new CompStmt(statement, incrementStmt));
        state.getExeStack().push(new CompStmt(varDecl, new CompStmt(assignStmt, whileStmt)));
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        typeEnv.add(indexName, new IntType());
        IType type1 = exp1.typecheck(typeEnv);
        IType type2 = exp2.typecheck(typeEnv);
        IType type3 = exp3.typecheck(typeEnv);
        if (type1.equals(new IntType()) && type2.equals(new IntType()) && type3.equals(new IntType())) {
            statement.typecheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else {
            throw new MyException("The expressions of FOR have not the type int");
        }
    }

}
