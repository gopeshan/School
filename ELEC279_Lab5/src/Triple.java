import java.util.Objects;

public class Triple<T, U, V> {
    T first;
    U second;
    V third;

    public Triple(T first, U second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Triple<?, ?, ?> other = (Triple<?, ?, ?>) obj;
        return Objects.equals(first, other.first) && Objects.equals(second, other.second)
                && Objects.equals(third, other.third);
    }

    public String toString() {
        return "First: " + first + "; Second: " + second + "; Third: " + third;
    }
}
