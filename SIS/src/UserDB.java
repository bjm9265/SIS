import java.util.Collection;
import java.util.HashMap;
/**
 * Storage of all users where the key is the unique username and the value is the associated User object.
 *
 * @author Brian Mirabito
 */
public class UserDB extends Object implements DB<String, User> {
    private HashMap<String, User> users;

    public UserDB(){
        /**
         * constructor
         */
        this.users = new HashMap<>();
    }

    public User addValue(User user){
        /**
         * Add a value entry to the database in constant time. The database will determine the key
         * based on the value type.
         */
        if(this.users.containsKey(user.getUsername())){
            return this.users.get(user.getUsername());
        } else {
            this.users.put(user.getUsername(), user);
            return null;
        }
    }

    public User getValue(String username){
        /**
         * Get the value for an associated key in constant time.
         */
        if(this.users.containsKey(username)) {
            return this.users.get(username);
        } else {
            return null;
        }
    }

    public boolean hasKey(String username) {
        /**
         * Indicates whether a key is in the database or not, in constant time.
         */
        return this.users.containsKey(username);
    }

    public Collection<User> getAllValues(){
        /**
         * Get all the values in the database in linearithmic, O(nlogn), time, where the elements are naturally ordered.
         */
        return this.users.values();
    }
}
