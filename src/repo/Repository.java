package repo;

import java.util.List;
import model.prgstate.*;

public class Repository implements IRepo {
    private List<PrgState> prgList;

    public Repository(List<PrgState> prgList) {
        this.prgList = prgList;
    }

    public PrgState getCrtPrg() {
        return prgList.getLast();
    }
}
