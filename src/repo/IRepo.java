package repo;

import model.exception.MyException;
import model.prgstate.PrgState;

public interface IRepo {
    PrgState getCrtPrg();

    void changePrgState(PrgState prg);

    void logPrgStateExec() throws MyException;
    void clearFile() throws MyException;

    void changeFilePath(String newFilePath);
}
