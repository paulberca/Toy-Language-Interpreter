package view.interpreter;

import controller.Controller;
import model.expression.*;
import model.prgstate.PrgState;
import model.prgstate.dataStruct.*;
import model.statement.*;
import model.type.*;
import model.value.*;
import repo.*;
import view.command.*;
import view.menu.TextMenu;

public class Interpreter {
    public static void main(String[] args) {
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        PrgState prg1 = new PrgState(new ExeStack(), new SymTable(), new Output(), new FileTable(), new Heap(), ex1);
        IRepo repo1 = new Repository(prg1, "log1.txt");
        Controller ctr1 = new Controller(repo1);


        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(6)), new ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), 3), 1)), new CompStmt(new AssignStmt("b", new ArithExp(new VarExp("a"), new ValueExp(new IntValue(1)), 1)), new PrintStmt(new VarExp("b"))))));
        PrgState prg2 = new PrgState(new ExeStack(), new SymTable(), new Output(), new FileTable(), new Heap(), ex2);
        IRepo repo2 = new Repository(prg2, "log2.txt");
        Controller ctr2 = new Controller(repo2);


        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new VarExp("v"))))));
        PrgState prg3 = new PrgState(new ExeStack(), new SymTable(), new Output(), new FileTable(), new Heap(), ex3);
        IRepo repo3 = new Repository(prg3, "log3.txt");
        Controller ctr3 = new Controller(repo3);


        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("src/test.in"))),
                        new CompStmt(new OpenRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new CloseRFile(new VarExp("varf"))))))))));
        PrgState prg4 = new PrgState(new ExeStack(), new SymTable(), new Output(), new FileTable(), new Heap(), ex4);
        IRepo repo4 = new Repository(prg4, "log4.txt");
        Controller ctr4 = new Controller(repo4);

        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(2))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new VarExp("a")))))));
        PrgState prg5 = new PrgState(new ExeStack(), new SymTable(), new Output(), new FileTable(), new Heap(), ex5);
        IRepo repo5 = new Repository(prg5, "log5.txt");
        Controller ctr5 = new Controller(repo5);


        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                                new PrintStmt(new ArithExp(new ReadHeapExp(new ReadHeapExp(new VarExp("a"))), new ValueExp(new IntValue(5)), 1)))))));
        PrgState prg6 = new PrgState(new ExeStack(), new SymTable(), new Output(), new FileTable(), new Heap(), ex6);
        IRepo repo6 = new Repository(prg6, "log6.txt");
        Controller ctr6 = new Controller(repo6);


        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp(new ReadHeapExp(new VarExp("v")), new ValueExp(new IntValue(5)), 1))))));
        PrgState prg7 = new PrgState(new ExeStack(), new SymTable(), new Output(), new FileTable(), new Heap(), ex7);
        IRepo repo7 = new Repository(prg7, "log7.txt");
        Controller ctr7 = new Controller(repo7);


        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))))))));
        PrgState prg8 = new PrgState(new ExeStack(), new SymTable(), new Output(), new FileTable(), new Heap(), ex8);
        IRepo repo8 = new Repository(prg8, "log8.txt");
        Controller ctr8 = new Controller(repo8);


        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                        new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), 5), new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), 2))))));
        PrgState prg9 = new PrgState(new ExeStack(), new SymTable(), new Output(), new FileTable(), new Heap(), ex9);
        IRepo repo9 = new Repository(prg9, "log9.txt");
        Controller ctr9 = new Controller(repo9);


        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new VarDeclStmt("y", new RefType(new IntType())),
                                    new CompStmt(new NewStmt("y", new ValueExp(new IntValue(30))),
                                        new CompStmt(new NewStmt("a", new VarExp("v")),
                                                new CompStmt(new NewStmt("y", new ValueExp(new IntValue(50))),
                                                    new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))),
                                                            new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a"))))))))))));
        PrgState prg10 = new PrgState(new ExeStack(), new SymTable(), new Output(), new FileTable(), new Heap(), ex10);
        IRepo repo10 = new Repository(prg10, "log10.txt");
        Controller ctr10 = new Controller(repo10);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
        menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
        menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
        menu.addCommand(new RunExample("6", ex6.toString(), ctr6));
        menu.addCommand(new RunExample("7", ex7.toString(), ctr7));
        menu.addCommand(new RunExample("8", ex8.toString(), ctr8));
        menu.addCommand(new RunExample("9", ex9.toString(), ctr9));
        menu.addCommand(new RunExample("10", ex10.toString(), ctr10));
        menu.show();
    }
}
