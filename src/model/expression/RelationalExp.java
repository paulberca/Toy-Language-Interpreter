package model.expression;

import model.adt.MyIDictionary;
import model.exception.MyException;
import model.prgstate.dataStruct.IHeap;
import model.prgstate.dataStruct.ISymTable;
import model.type.BoolType;
import model.type.IType;
import model.type.IntType;
import model.value.*;

public class RelationalExp implements IExpression {
    private final IExpression e1;
    private final IExpression e2;
    private final int op;     // 1: <, 2: <=, 3: ==, 4: !=, 5: >, 6: >=

    public RelationalExp(IExpression e1, IExpression e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public IValue eval(ISymTable tbl, IHeap hp) throws MyException {
        IValue v1, v2;
        v1 = e1.eval(tbl, hp);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl, hp);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1 = i1.getVal();
                int n2 = i2.getVal();

                return switch (op) {
                    case 1 -> new BoolValue(n1 < n2);
                    case 2 -> new BoolValue(n1 <= n2);
                    case 3 -> new BoolValue(n1 == n2);
                    case 4 -> new BoolValue(n1 != n2);
                    case 5 -> new BoolValue(n1 > n2);
                    case 6 -> new BoolValue(n1 >= n2);
                    default -> throw new MyException("Invalid operator");
                };
            }
            else throw new MyException("Second operand is not an integer");
        }
        throw new MyException("First operand is not an integer");
    }

    @Override
    public IExpression deepCopy() {
        return new RelationalExp(e1.deepCopy(), e2.deepCopy(), op);
    }

    @Override
    public IType typecheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType type1, type2;
        type1 = e1.typecheck(typeEnv);
        type2 = e2.typecheck(typeEnv);
        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new BoolType();
            }
            else throw new MyException("Second operand is not an integer");
        }
        throw new MyException("First operand is not an integer");
    }

    public String toString() {
        String opStr = switch (op) {
            case 1 -> "<";
            case 2 -> "<=";
            case 3 -> "==";
            case 4 -> "!=";
            case 5 -> ">";
            case 6 -> ">=";
            default -> throw new IllegalArgumentException("Invalid operator");
        };
        return e1.toString() + opStr + e2.toString();
    }
}
