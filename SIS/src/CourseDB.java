import java.util.Collection;
import java.util.HashMap;
/**
 * Storage of all courses where the key is the unique course id and the value is the associated Course object.
 *
 * @author Brian Mirabito
 */
public class CourseDB extends Object implements DB<Integer, Course> {
    private HashMap<Integer, Course> courses;

    public CourseDB(){
        /**
         * constructor
         */
        this.courses = new HashMap<>();
    }

    @Override
    public Course addValue(Course value) {
        /**
         * Add a value entry to the database in constant time. The database will determine the key based on the value type.
         */
        if(this.courses.containsValue(value)){
            return this.courses.get(value.getId());
        } else {
            this.courses.put(value.getId(), value);
            return null;
        }
    }

    @Override
    public Course getValue(Integer key) {
        /**
         * Get the value for an associated key in constant time.
         */
        if(this.courses.containsKey(key)){
            return this.courses.get(key);
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Integer key) {
        /**
         * Indicates whether a key is in the database or not, in constant time.
         */
        return this.courses.containsKey(key);
    }

    @Override
    public Collection<Course> getAllValues() {
        /**
         * Get all the values in the database in linearithmic, O(nlogn), time, where the elements are naturally ordered.
         */
        return this.courses.values();
    }

}
