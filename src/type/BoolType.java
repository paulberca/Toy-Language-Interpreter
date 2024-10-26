package type;

public class BoolType implements Type {
    public boolean equals(Object obj) {
        return obj instanceof BoolType;
    }

    public String toString() {
        return "bool";
    }
}

