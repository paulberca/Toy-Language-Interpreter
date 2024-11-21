package view;

import controller.Controller;
import model.expression.*;
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

        String logFilePath;
        System.out.print("Enter log file path: ");
        logFilePath = scanner.nextLine();
        repo.changeFilePath(logFilePath);

        while (true) {
            printMenu();
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    chooseHardcodedProgram();
                    break;
                case 2:
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

    private void chooseHardcodedProgram() {
        IStmt program1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        IStmt program2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(6)), new ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), 3), 1)), new CompStmt(new AssignStmt("b", new ArithExp(new VarExp("a"), new ValueExp(new IntValue(1)), 1)), new PrintStmt(new VarExp("b"))))));
        IStmt program3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new VarExp("v"))))));
        IStmt program4 = new CompStmt(new IfStmt(new RelationalExp(new ValueExp(new IntValue(2)), new ValueExp(new IntValue(3)), 2), new PrintStmt(new ValueExp(new StringValue("true"))), new PrintStmt(new ValueExp(new StringValue("false")))), new NopStmt());


        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a program: ");
        System.out.println("1. " + program1.toString());
        System.out.println("2. " + program2.toString());
        System.out.println("3. " + program3.toString());
        System.out.println("4. " + program4.toString());
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                controller.changePrgState(program1);
                break;
            case 2:
                controller.changePrgState(program2);
                break;
            case 3:
                controller.changePrgState(program3);
                break;
            case 4:
                controller.changePrgState(program4);
                break;
            default:
                System.out.println("Invalid option");
        }
    }
}
