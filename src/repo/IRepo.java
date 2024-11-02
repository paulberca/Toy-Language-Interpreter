package repo;

import model.prgstate.PrgState;

public interface IRepo {
    PrgState getCrtPrg();

    void changePrgState(PrgState prg);
}
