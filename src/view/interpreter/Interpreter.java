package view.interpreter;

import controller.Controller;
import model.adt.MyDictionary;
import model.adt.MyIList;
import model.adt.MyList;
import model.adt.MyStack;
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

    public static void main(String[] args) {
        ArrayList<IStmt> hardcodedStatements = new Interpreter().getStatements();

        ArrayList<Controller> ctrs = new ArrayList<>();
        for (int i = 0; i < hardcodedStatements.size(); i++) {
            try {
                hardcodedStatements.get(i).typecheck(new MyDictionary<>());
            } catch (Exception e) {
                System.out.println("Stmt " + i +  ": " + e.getMessage());
            }
            PrgState prg = new PrgState(new ExeStack(), new MyStack<>(), new Output(), new FileTable(), new Heap(), new LockTable(), new ProcTable(), hardcodedStatements.get(i));
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

        // Ref int v1; Ref int v2; int x;  int q;new(v1,20);new(v2,30);newLock(x);fork(fork(lock(x);wh(v1,rh(v1)-1);unlock(x));lock(x);wh(v1,rh(v1)*10);unlock(x));newLock(q);fork(fork(lock(q);wh(v2,rh(v2)+5);unlock(q));lock(q);wh(v2,rh(v2)*10);unlock(q);nop;nop;nop;nop;lock(x); print(rh(v1)); unlock(x);lock(q); print(rh(v2)); unlock(q);
        IStmt ex13 = new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("v2", new RefType(new IntType())),
                        new CompStmt(new VarDeclStmt("x", new IntType()),
                                new CompStmt(new VarDeclStmt("q", new IntType()),
                                        new CompStmt(new NewStmt("v1", new ValueExp(new IntValue(20))),
                                                new CompStmt(new NewStmt("v2", new ValueExp(new IntValue(30))),
                                                        new CompStmt(new NewLockStmt("x"),
                                                                new CompStmt(new ForkStmt(
                                                                        new CompStmt(new ForkStmt(
                                                                                new CompStmt(new LockStmt("x"),
                                                                                        new CompStmt(new WriteHeapStmt("v1", new ArithExp(new ReadHeapExp(new VarExp("v1")), new ValueExp(new IntValue(1)), 2)),
                                                                                                new UnlockStmt("x")))),
                                                                                new CompStmt(new LockStmt("x"),
                                                                                        new CompStmt(new WriteHeapStmt("v1", new ArithExp(new ReadHeapExp(new VarExp("v1")), new ValueExp(new IntValue(10)), 3)),
                                                                                                new UnlockStmt("x"))))),
                                                                        new CompStmt(new NewLockStmt("q"),
                                                                                new CompStmt(new ForkStmt(
                                                                                        new CompStmt(new ForkStmt(
                                                                                                new CompStmt(new LockStmt("q"),
                                                                                                        new CompStmt(new WriteHeapStmt("v2", new ArithExp(new ReadHeapExp(new VarExp("v2")), new ValueExp(new IntValue(5)), 1)),
                                                                                                                new UnlockStmt("q")))),
                                                                                                new CompStmt(new LockStmt("q"),
                                                                                                        new CompStmt(new WriteHeapStmt("v2", new ArithExp(new ReadHeapExp(new VarExp("v2")), new ValueExp(new IntValue(10)), 3)),
                                                                                                                new UnlockStmt("q"))))),
                                                                                        new CompStmt(new NopStmt(),
                                                                                                new CompStmt(new NopStmt(),
                                                                                                        new CompStmt(new NopStmt(),
                                                                                                                new CompStmt(new NopStmt(),
                                                                                                                        new CompStmt(new LockStmt("x"),
                                                                                                                                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v1"))),
                                                                                                                                        new CompStmt(new UnlockStmt("x"),
                                                                                                                                                new CompStmt(new LockStmt("q"),
                                                                                                                                                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v2"))),
                                                                                                                                                                new UnlockStmt("q"))))))))))))))))))));

        IStmt ex14 = new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("v2", new RefType(new IntType())),
                        new CompStmt(new VarDeclStmt("x", new BoolType()),
                                new CompStmt(new VarDeclStmt("q", new IntType()),
                                        new CompStmt(new NewStmt("v1", new ValueExp(new IntValue(20))),
                                                new CompStmt(new NewStmt("v2", new ValueExp(new IntValue(30))),
                                                        new CompStmt(new NewLockStmt("x"),
                                                                new CompStmt(new ForkStmt(
                                                                        new CompStmt(new ForkStmt(
                                                                                new CompStmt(new LockStmt("x"),
                                                                                        new CompStmt(new WriteHeapStmt("v1", new ArithExp(new ReadHeapExp(new VarExp("v1")), new ValueExp(new IntValue(1)), 2)),
                                                                                                new UnlockStmt("x")))),
                                                                                new CompStmt(new LockStmt("x"),
                                                                                        new CompStmt(new WriteHeapStmt("v1", new ArithExp(new ReadHeapExp(new VarExp("v1")), new ValueExp(new IntValue(10)), 3)),
                                                                                                new UnlockStmt("x"))))),
                                                                        new CompStmt(new NewLockStmt("q"),
                                                                                new CompStmt(new ForkStmt(
                                                                                        new CompStmt(new ForkStmt(
                                                                                                new CompStmt(new LockStmt("q"),
                                                                                                        new CompStmt(new WriteHeapStmt("v2", new ArithExp(new ReadHeapExp(new VarExp("v2")), new ValueExp(new IntValue(5)), 1)),
                                                                                                                new UnlockStmt("q")))),
                                                                                                new CompStmt(new LockStmt("q"),
                                                                                                        new CompStmt(new WriteHeapStmt("v2", new ArithExp(new ReadHeapExp(new VarExp("v2")), new ValueExp(new IntValue(10)), 3)),
                                                                                                                new UnlockStmt("q"))))),
                                                                                        new CompStmt(new NopStmt(),
                                                                                                new CompStmt(new NopStmt(),
                                                                                                        new CompStmt(new NopStmt(),
                                                                                                                new CompStmt(new NopStmt(),
                                                                                                                        new CompStmt(new LockStmt("x"),
                                                                                                                                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v1"))),
                                                                                                                                        new CompStmt(new UnlockStmt("x"),
                                                                                                                                                new CompStmt(new LockStmt("q"),
                                                                                                                                                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v2"))),
                                                                                                                                                                new UnlockStmt("q"))))))))))))))))))));


        IStmt ex15 = new CompStmt(new VarDeclStmt("v2", new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("x", new IntType()),
                        new CompStmt(new NewStmt("v2", new ValueExp(new IntValue(30))),
                                new CompStmt(new NewLockStmt("x"), new CompStmt(new LockStmt("x"),
                                        new CompStmt(new ForkStmt(new WriteHeapStmt("v2", new ArithExp(new ReadHeapExp(new VarExp("v2")), new ValueExp(new IntValue(5)), 1))),
                                                new CompStmt(new NopStmt(), new CompStmt(new NopStmt(), new CompStmt(new NopStmt(), new UnlockStmt("x"))))))))));

        IStmt ex16 = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                new CompStmt(new NewStmt("a", new ValueExp(new IntValue(20))),
                        new CompStmt(new ForStmt("v", new ValueExp(new IntValue(0)), new ValueExp(new IntValue(3)), new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), 1),
                                new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"), new ReadHeapExp(new VarExp("a")), 3))))),
                                new PrintStmt(new ReadHeapExp(new VarExp("a"))))));


        MyIList<String> proc12 = new MyList<>();
        proc12.add("a");
        proc12.add("b");
        MyIList<IExpression> call121 = new MyList<>();
        call121.add(new ArithExp(new VarExp("v"), new ValueExp(new IntValue(10)), 3));
        call121.add(new VarExp("w"));
        MyIList<IExpression> call122 = new MyList<>();
        call122.add(new VarExp("v"));
        call122.add(new VarExp("w"));

        IStmt ex17 = new CompStmt(
                new ProcDefStmt("sum", proc12,
                        new CompStmt(
                                new VarDeclStmt("v", new IntType()),
                                new CompStmt(
                                        new AssignStmt("v", new ArithExp(new VarExp("a"), new VarExp("b"), 1)),
                                        new PrintStmt(new VarExp("v"))
                                )
                        )
                ),
                new CompStmt(
                        new ProcDefStmt("product", proc12,
                                new CompStmt(
                                        new VarDeclStmt("v", new IntType()),
                                        new CompStmt(
                                                new AssignStmt("v", new ArithExp(new VarExp("a"), new VarExp("b"), 3)),
                                                new PrintStmt(new VarExp("v"))
                                        )
                                )
                        ),
                        new CompStmt(
                                new VarDeclStmt("v", new IntType()),
                                new CompStmt(
                                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                                        new CompStmt(
                                                new VarDeclStmt("w", new IntType()),
                                                new CompStmt(
                                                        new AssignStmt("w", new ValueExp(new IntValue(5))),
                                                        new CompStmt(
                                                                new CallFStmt("sum", call121),
                                                                new CompStmt(
                                                                        new PrintStmt(new VarExp("v")),
                                                                        new ForkStmt(
                                                                                new CompStmt(
                                                                                        new CallFStmt("product", call122),
                                                                                        new ForkStmt(
                                                                                                new CallFStmt("sum", call122)
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        IStmt ex18 = new CompStmt(new VarDeclStmt("b", new BoolType()),
                new CompStmt(new VarDeclStmt("c", new IntType()),
                        new CompStmt(new AssignStmt("b", new ValueExp(new BoolValue(true))),
                                new CompStmt(new CondAssignStmt("c", new VarExp("b"), new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200))),
                                        new CompStmt(new PrintStmt(new VarExp("c")),
                                                new CompStmt(new CondAssignStmt("c", new ValueExp(new BoolValue(false)), new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200))),
                                                        new PrintStmt(new VarExp("c"))))))));

        IStmt ex19 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(0))),
                        new CompStmt(new RepeatUntilStmt(
                                new CompStmt(new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), 2)))),
                                        new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), 1))),
                                new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(3)), 3)),
                                new CompStmt(new VarDeclStmt("x", new IntType()),
                                        new CompStmt(new VarDeclStmt("y", new IntType()),
                                                new CompStmt(new VarDeclStmt("z", new IntType()),
                                                        new CompStmt(new VarDeclStmt("w", new IntType()),
                                                                new CompStmt(new AssignStmt("x", new ValueExp(new IntValue(1))),
                                                                        new CompStmt(new AssignStmt("y", new ValueExp(new IntValue(2))),
                                                                                new CompStmt(new AssignStmt("z", new ValueExp(new IntValue(3))),
                                                                                        new CompStmt(new AssignStmt("w", new ValueExp(new IntValue(4))),
                                                                                                new PrintStmt(new ArithExp(new VarExp("v"), new ValueExp(new IntValue(10)), 3)))))))))))));



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
        hardcodedStatements.add(ex13);
        hardcodedStatements.add(ex14);
        hardcodedStatements.add(ex15);
        hardcodedStatements.add(ex16);
        hardcodedStatements.add(ex17);
        hardcodedStatements.add(ex18);
        hardcodedStatements.add(ex19);

        return hardcodedStatements;
    }
}
