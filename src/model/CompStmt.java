package model;

public class CompStmt implements IStmt {
    IStmt first;
    IStmt snd;

    public CompStmt(IStmt first, IStmt snd) {
        this.first = first;
        this.snd = snd;
    }

    public String toString() {
        return "(" + first.toString() + ";" + snd.toString() + ")";
    }

    // PrgState execute(PrgState state) throws MyException {
    //     MyIStack<IStmt> stk = state.getStk();
    //     stk.push(snd);
    //     stk.push(first);
    //     return state;
    // }
}

