package controller;

import model.exception.MyException;
import model.prgstate.PrgState;
import repo.IRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private final IRepo repo;
    boolean printFlag;
    private ExecutorService executor;

    public Controller(IRepo repo) {
        this.repo = repo;
        printFlag = true;
    }

    public void oneStepForAllPrg(List<PrgState> prgList, ExecutorService ex) throws InterruptedException {
        prgList.forEach(prg -> {
            try {
                displayCrtPrgState(prg);
                repo.logPrgStateExec(prg);
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        });

        List<Callable<PrgState>> callList = prgList.stream()
                        .map((PrgState p) -> (Callable<PrgState>)(() -> { return p.oneStep(); }))
                        .toList();

        List<PrgState> newPrgList = ex.invokeAll(callList).stream()
                .map(future -> {try { return future.get(); }
                                                catch (Exception e) { throw new RuntimeException(e); }})
                .filter(p -> p != null)
                .toList();

        prgList.addAll(newPrgList);

        prgList.forEach(prg -> {
            try {
                displayCrtPrgState(prg);
                repo.logPrgStateExec(prg);
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        });

        repo.setPrgList(prgList);
    }

    public void allStep() throws InterruptedException, MyException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
        GarbageCollector collector = new GarbageCollector();

        repo.clearFile();

        while (!prgList.isEmpty()) {
            for (PrgState prg : prgList) {
                List<Integer> symTableAddr = collector.getAddrFromSymTable(new ArrayList<>(prg.getSymTable().getContent().values()));
                Set<Integer> reachableAddr = collector.computeReachableAddr(symTableAddr, prg.getHeap().getContent());
                prg.getHeap().setContent(collector.safeGarbageCollector(reachableAddr, prg.getHeap().getContent()));
            }

            oneStepForAllPrg(prgList, executor);
            prgList = removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();

        repo.setPrgList(prgList);
    }

//    public void allStep() {
//        PrgState prg = repo.getCrtPrg();
//        GarbageCollector collector = new GarbageCollector();
//        try {
//            repo.clearFile();
//            repo.logPrgStateExec();
//        } catch (MyException e) { System.out.println(e.getMessage()); }
//        while (!prg.getExeStack().isEmpty()) {
//            try {
//                if (printFlag) {
//                    displayCrtPrgState(prg);
//                }
//                oneStep();
//                try { repo.logPrgStateExec(); } catch (MyException e) { System.out.println(e.getMessage()); }
//
//                List<Integer> symTableAddr = collector.getAddrFromSymTable(new ArrayList<>(prg.getSymTable().getContent().values()));
//                Set<Integer> reachableAddr = collector.computeReachableAddr(symTableAddr, prg.getHeap().getContent());
//                prg.getHeap().setContent(collector.safeGarbageCollector(reachableAddr, prg.getHeap().getContent()));
//
//            } catch (MyException | StackException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        displayCrtPrgState(prg);
//    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream().filter(p -> p.isNotCompleted()).collect(Collectors.toList());
    }

    private void displayCrtPrgState(PrgState prg) {
        System.out.print(prg.toString());
        System.out.println("-".repeat(50));
    }
}
