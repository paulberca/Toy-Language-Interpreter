import controller.Controller;
import model.prgstate.PrgState;
import model.prgstate.dataStruct.*;
import model.statement.*;
import model.type.IntType;
import model.type.StringType;
import model.value.*;
import model.expression.*;
import repo.*;
import view.View;


public class Main {
    public static void main(String[] args) {
        Main main = new Main();
//        main.testHardcodedPrograms();
        main.testFile();
    }

    public void testPrg(IStmt stmt) {
        IExeStack stack = new ExeStack();
        ISymTable symTable = new SymTable();
        IOutput out = new Output();
        IFileTable fileTable = new FileTable();
        IHeap heap = new Heap();
        PrgState prg = new PrgState(stack, symTable, out, fileTable, heap, stmt);

        IRepo repo = new Repository(prg, "");
        Controller controller = new Controller(repo);
        View view = new View(repo, controller);

        view.run();
    }

    public void testHardcodedPrograms() {
        IStmt stmt = new NopStmt();
        testPrg(stmt);
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
                                                    new CloseRFile(new VarExp("varf"))))))))));

        testPrg(stmt);
    }
}