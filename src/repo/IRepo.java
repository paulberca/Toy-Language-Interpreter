package repo;

import model.exception.MyException;
import model.prgstate.PrgState;
import java.util.List;

public interface IRepo {
    List<PrgState> getPrgList();

    void setPrgList(List<PrgState> newPrgList);

    void logPrgStateExec(PrgState prg) throws MyException;

    void clearFile() throws MyException;
}
