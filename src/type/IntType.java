package type;

public class IntType implements Type {
    public boolean equals(Object obj) {
        return obj instanceof IntType;
    }

    public String toString() {
        return "int";
    }
}

