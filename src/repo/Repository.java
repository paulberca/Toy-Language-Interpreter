package repo;

import java.io.*;
//import java.util.List;

import model.exception.MyException;
import model.prgstate.*;

public class Repository implements IRepo {
//    private List<PrgState> prgList;
//
//    public Repository(List<PrgState> prgList) {
//        this.prgList = prgList;
//    }
//
//    public PrgState getCrtPrg() {
//        return prgList.getLast();
//    }

    private PrgState prg;
    private String logFilePath;

    public Repository(PrgState prg, String logFilePath) {
        this.prg = prg;
        this.logFilePath = logFilePath;
    }

    public void changePrgState(PrgState newPrg) {
        prg = newPrg;
    }

    public PrgState getCrtPrg() {
        return prg;
    }

    @Override
    public void changeFilePath(String newFilePath) {
        logFilePath = newFilePath;
    }

    @Override
    public void logPrgStateExec() throws MyException{
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath,  true)))) {
            writer.print(prg.toString());
            writer.println("-".repeat(50));
        } catch (IOException e) {
            throw new MyException("Error writing to file: " + e.getMessage());
        }
    }

    public void clearFile() throws MyException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)))) {
            writer.print("");
        } catch (IOException e) {
            throw new MyException("Error clearing file: " + e.getMessage());
        }
    }
}
