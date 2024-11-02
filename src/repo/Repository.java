package repo;

import java.util.List;
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

    PrgState prg;

    public Repository(PrgState prg) {
        this.prg = prg;
    }

    public void changePrgState(PrgState newPrg) {
        prg = newPrg;
    }

    public PrgState getCrtPrg() {
        return prg;
    }
}
