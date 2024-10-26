package adt;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<TKey, TValue> implements MyIDictionary<TKey, TValue> {
    private Map<TKey, TValue> dict;

    public MyDictionary() {
        dict = new HashMap<TKey, TValue>();
    }

    @Override
    public boolean isDefined(TKey key) {
        return dict.containsKey(key);
    }

    @Override
    public void update(TKey key, TValue val) {
        dict.put(key, val);
    }

    @Override
    public TValue lookup(TKey key) {
        return dict.get(key);
    }

    @Override
    public void add(TKey key, TValue val) {
        dict.put(key, val);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (TKey key : this.dict.keySet()) {
            buffer.append(key).append("->").append(this.dict.get(key)).append("\n");
        }
        return buffer.toString();
    }
}
