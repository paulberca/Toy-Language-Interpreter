import controller.Controller;
import model.prgstate.PrgState;
import model.statement.*;
import model.type.IntType;
import model.type.StringType;
import model.value.*;
import model.expression.*;
import repo.*;
import view.View;
import model.adt.*;

import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
//        main.testHardcodedPrograms();
        main.testFile();    // Error reading from file: Stream closed
    }

    public void testHardcodedPrograms() {
        IStmt EmptyStmt = new NopStmt();
        MyIStack<IStmt> stack = new MyStack<>();
        MyIDictionary<String, IValue> symTable = new MyDictionary<>();
        MyIList<IValue> out = new MyList<>();
        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
        PrgState prg = new PrgState(stack, symTable, out, fileTable, EmptyStmt);   // this is just an empty program
        // a placeholder until the user chooses one of the three hardcoded programs

        IRepo repo = new Repository(prg, "");

        Controller controller = new Controller(repo);

        View view = new View(repo, controller);

        view.run();
    }

    public void testFile() {
        /*
            string varf;
            varf = "test.in";
            openRFile(varf);
            int varc;
            readFile(varf, varc);
            print(varc);
            readFile(varf, varc);
            print(varc);
            closeRFile(varf);
         */
        IStmt stmt = new CompStmt(new VarDeclStmt("varf", new StringType()),
                        new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("src/test.in"))),
                            new CompStmt(new OpenRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                    new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                        new CompStmt(new PrintStmt(new VarExp("varc")),
                                            new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                    new CloseRFile(new VarExp("varf"))
                                                )
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    );

        MyIStack<IStmt> stack = new MyStack<>();
        MyIDictionary<String, IValue> symTable = new MyDictionary<>();
        MyIList<IValue> out = new MyList<>();
        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
        PrgState prg = new PrgState(stack, symTable, out, fileTable, stmt);

        IRepo repo = new Repository(prg, "");
        Controller controller = new Controller(repo);
        View view = new View(repo, controller);

        view.run();
    }
}