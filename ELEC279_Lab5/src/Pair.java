import java.util.Objects;

// Define a generic class Pair with two type parameters T and U
public class Pair<T, U> {
    // Instance variables to store the first and second elements of the pair
    T first;
    U second;

    // Constructor to initialize the Pair with given first and second elements
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    // Override the equals method to check if two Pair objects are equal
    public boolean equals(Object obj) {
        // Check if the object being compared is the same instance
        if (this == obj)
            return true;
        // Check if the object being compared is null
        if (obj == null)
            return false;
        // Check if the classes of the objects being compared are the same
        if (getClass() != obj.getClass())
            return false;
        // Type the object to Pair class
        Pair<?, ?> other = (Pair<?, ?>) obj;
        // Check if the first and second elements of both pairs are equal
        return Objects.equals(first, other.first) && Objects.equals(second, other.second);
    }

    // Override the toString method to provide a string representation of the Pair
    public String toString() {
        return "First: " + first + "; Second: " + second;
    }
}
