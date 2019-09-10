import java.util.Collection;

/**
 * A generic interface used by the Backend, CourseDB and UserDB.
 *
 * author: Brian Mirabito
 */
public interface DB<K,V> {
    V addValue(V value); // Add a value entry to the database in constant time.
    V getValue(K key); // Get all the values in the database in linearithmic, O(nlogn), time, where the elements are naturally ordered.
    boolean hasKey(K key); // Get the value for an associated key in constant time.
    Collection<V> getAllValues(); // Indicates whether a key is in the database or not, in constant time.
}
