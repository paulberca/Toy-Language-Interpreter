package view.interpreter;

import controller.Controller;
import model.adt.MyDictionary;
import model.expression.*;
import model.prgstate.PrgState;
import model.prgstate.dataStruct.*;
import model.statement.*;
import model.type.*;
import model.value.*;
import repo.*;
import view.command.*;
import view.menu.TextMenu;

import java.util.ArrayList;

public class Interpreter {
    public ArrayList<IStmt> getStatements() {
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(6)), new ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), 3), 1)), new CompStmt(new AssignStmt("b", new ArithExp(new VarExp("a"), new ValueExp(new IntValue(1)), 1)), new PrintStmt(new VarExp("b"))))));
        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new VarExp("v"))))));
        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("src/test.in"))),
                        new CompStmt(new OpenRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new CloseRFile(new VarExp("varf"))))))))));
        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(2))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new VarExp("a")))))));
        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                                new PrintStmt(new ArithExp(new ReadHeapExp(new ReadHeapExp(new VarExp("a"))), new ValueExp(new IntValue(5)), 1)))))));
        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp(new ReadHeapExp(new VarExp("v")), new ValueExp(new IntValue(5)), 1))))));
        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))))))));
        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                        new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), 5), new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), 2))))));

        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new VarDeclStmt("y", new RefType(new IntType())),
                                        new CompStmt(new NewStmt("y", new ValueExp(new IntValue(30))),
                                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                                        new CompStmt(new NewStmt("y", new ValueExp(new IntValue(50))),
                                                                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))),
                                                                        new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a"))))))))))));
        IStmt ex11 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(
                                                new CompStmt(new WriteHeapStmt("a", new ValueExp(new IntValue(30))),
                                                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));

        IStmt ex12 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(
                                                new CompStmt(new VarDeclStmt("x", new IntType()),
                                                        new CompStmt(new AssignStmt("x", new ValueExp(new IntValue(5))),
                                                                new CompStmt(new PrintStmt(new VarExp("x")),
                                                                        new ForkStmt(
                                                                                new CompStmt(new VarDeclStmt("y", new IntType()),
                                                                                        new CompStmt(new AssignStmt("y", new ValueExp(new IntValue(15))),
                                                                                                new PrintStmt(new VarExp("y"))))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));

        ArrayList<IStmt> hardcodedStatements = new ArrayList<>();
        hardcodedStatements.add(ex1);
        hardcodedStatements.add(ex2);
        hardcodedStatements.add(ex3);
        hardcodedStatements.add(ex4);
        hardcodedStatements.add(ex5);
        hardcodedStatements.add(ex6);
        hardcodedStatements.add(ex7);
        hardcodedStatements.add(ex8);
        hardcodedStatements.add(ex9);
        hardcodedStatements.add(ex10);
        hardcodedStatements.add(ex11);
        hardcodedStatements.add(ex12);

        return hardcodedStatements;
    }

    public static void main(String[] args) {
        ArrayList<IStmt> hardcodedStatements = new Interpreter().getStatements();

        ArrayList<Controller> ctrs = new ArrayList<>();
        for (int i = 0; i < hardcodedStatements.size(); i++) {
            try {
                hardcodedStatements.get(i).typecheck(new MyDictionary<>());
            } catch (Exception e) {
                System.out.println("Stmt " + i +  ": " + e.getMessage());
            }
            PrgState prg = new PrgState(new ExeStack(), new SymTable(), new Output(), new FileTable(), new Heap(), hardcodedStatements.get(i));
            IRepo repo = new Repository(prg, "log" + (i+1) + ".txt");
            Controller ctr = new Controller(repo);
            ctrs.add(ctr);
        }

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));

        for (int i = 0; i < ctrs.size(); i++) {
            menu.addCommand(new RunExample(Integer.toString(i + 1), hardcodedStatements.get(i).toString(), ctrs.get(i)));
        }

        menu.show();
    }
}
