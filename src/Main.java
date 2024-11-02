import controller.Controller;
import model.prgstate.PrgState;
import model.statement.*;
import model.value.*;
import repo.*;
import view.View;
import model.adt.*;

public class Main {
    public static void main(String[] args) {
        IStmt EmptyStmt = new NopStmt();
        MyIStack<IStmt> stack = new MyStack<>();
        MyIDictionary<String, IValue> symTable = new MyDictionary<>();
        MyIList<IValue> out = new MyList<>();
        PrgState prg = new PrgState(stack, symTable, out, EmptyStmt);   // this is just an empty program
        // a placeholder until the user chooses one of the three hardcoded programs

        IRepo repo = new Repository(prg);

        Controller controller = new Controller(repo);

        View view = new View(repo, controller);

        view.run();
    }
}