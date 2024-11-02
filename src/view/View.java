package view;

import controller.Controller;
import model.adt.*;
import model.expression.*;
import model.prgstate.PrgState;
import model.statement.*;
import model.type.*;
import model.value.*;
import repo.IRepo;

import java.util.Scanner;

public class View {
    private IRepo repo;
    private Controller controller;

    public View(IRepo repo, Controller controller) {
        this.repo = repo;
        this.controller = controller;
    }

    private void printMenu() {
        System.out.println("1. Input a program");
        System.out.println("2. Execute chosen program");
        System.out.println("3. Exit");
        System.out.print("> ");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean prgChosen = false;
        while (true) {
            printMenu();
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    chooseHardcodedProgram();
                    prgChosen = true;
                    break;
                case 2:
                    if (!prgChosen) {
                        System.out.println("No program chosen!");
                        break;
                    }
                    System.out.print("Current program: ");
                    System.out.println(repo.getCrtPrg().getExeStack().toString());
                    controller.allStep();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }

    private void loadRepo(IStmt program) {
        MyIStack<IStmt> stack = new MyStack<>();
        MyIDictionary<String, IValue> symTable = new MyDictionary<>();
        MyIList<IValue> out = new MyList<>();
        PrgState prg = new PrgState(stack, symTable, out, program);
        repo.changePrgState(prg);
    }

    private void chooseHardcodedProgram() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a program: ");
        System.out.println("1. int v; v=2; Print(v)");
        System.out.println("2. int a; int b; a=2+3*5; b=a+1; Print(b)");
        System.out.println("3. bool a; int v; a=true; (If a Then v=2 Else v=3); Print(v)");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                IStmt program1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
                loadRepo(program1);
                break;
            case 2:
                IStmt program2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)), new ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), 3), 1)), new CompStmt(new AssignStmt("b", new ArithExp(new VarExp("a"), new ValueExp(new IntValue(1)), 1)), new PrintStmt(new VarExp("b"))))));
                loadRepo(program2);
                break;
            case 3:
                IStmt program3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                        new CompStmt(new VarDeclStmt("v", new IntType()),
                                new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                        new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                                new PrintStmt(new VarExp("v"))))));
                loadRepo(program3);
                break;
        }
    }
}
