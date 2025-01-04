package repo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import model.exception.MyException;
import model.prgstate.*;

public class Repository implements IRepo {
    private List<PrgState> prgList;
    private final String logFilePath;

    public Repository(PrgState prg, String logFilePath) {
        this.prgList = new ArrayList<>();
        prgList.add(prg);
        this.logFilePath = logFilePath;
    }

    @Override
    public List<PrgState> getPrgList() {
        return prgList;
    }

    public int getProgramsCount() {
        return prgList.size();
    }

    @Override
    public void setPrgList(List<PrgState> newPrgList) {
        prgList = newPrgList;
    }

    @Override
    public void logPrgStateExec(PrgState prg) throws MyException{
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
