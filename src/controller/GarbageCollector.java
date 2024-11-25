package controller;

import model.value.IValue;
import model.value.RefValue;

import java.util.*;
import java.util.stream.Collectors;

public class GarbageCollector {
    public Map<Integer, IValue> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, IValue> heap) {
        return heap.entrySet().stream().filter(e->symTableAddr.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues) {
        return symTableValues.stream().filter(v->v instanceof RefValue).map(v->{RefValue v1 = (RefValue) v; return v1.getAddress();}).collect(Collectors.toList());
    }

    public List<Integer> getAddrFromHeap(Collection<IValue> heapValues, List<Integer> addresses) {
        return heapValues.stream().filter(v->v instanceof RefValue).map(v->((RefValue) v).getAddress()).filter(addr -> !addresses.contains(addr)).collect(Collectors.toList());
    }

    public Set<Integer> computeReachableAddr(List<Integer> symTableAddr, Map<Integer, IValue> heap) {
        Set<Integer> reachable = new HashSet<>(symTableAddr);
        List<Integer> newAddr;

        do {
            newAddr = getAddrFromHeap(heap.values(), new ArrayList<>(reachable));
            reachable.addAll(newAddr);
        } while (!newAddr.isEmpty());

        return reachable;
    }

    public Map<Integer, IValue> safeGarbageCollector(Set<Integer> reachableAddr, Map<Integer, IValue> heap) {
        return heap.entrySet().stream()
                .filter(e->reachableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
