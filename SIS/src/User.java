import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;
/**
 * Represents a user who is either a professor or student. All users have a unique username and store the courses
 * they are currently teaching (professor) or enrolled in (student), in a tree set that is organized based on their
 * preference (see subclasses).
 *
 * @author Brian Mirabito
 */
public class User extends Object implements Comparable<User> {
    public enum UserType{
        PROFESSOR, STUDENT
    }
    private String username;
    private User.UserType type;
    private TreeSet<Course> courses;

    public User(String username, User.UserType type, Comparator<Course> comp){
        /**
         * constructor
         */
        this.username = username;
        this.type = type;
        this.courses = new TreeSet<>(comp);
    }

    public String getUsername(){
        /**
         * Get the username.
         */
        return this.username;
    }

    public User.UserType getType(){
        /**
         * Get the user type.
         */
        return this.type;
    }

    public boolean addCourse(Course course){
        /**
         * Add a course for this user. For a professor it means they add it to the courses they are teaching.
         * If they are a student, they are enrolling in the course.
         */
        if(this.courses.contains(course)){
            return false;
        } else {
            this.courses.add(course);
            return true;
        }
    }

    public boolean removeCourse(Course course){
        /**
         * Remove of a course for this user. In both cases for a professor or student the meaning is the course no
         * longer exists in their collection of courses.
         */
        if(this.courses.contains(course)){
            this.courses.remove(course);
            return true;
        } else {
            return false;
        }
    }

    public Collection<Course> getCourses(){
        /**
         * Returns the courses the user is currently teaching or enrolled in.
         */
        return this.courses;
    }

    @Override
    public boolean equals(Object other){
        /**
         * Two users are equal if they have the same username.
         */
        if( other instanceof User){
            User u = (User)other;
            return u.username.equals(this.username);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        /**
         * The hash code of a user is the hash code of the username.
         */
        return this.username.hashCode();
    }

    @Override
    public String toString(){
        /**
         * Returns a string representation of the user in the format:
         *
         * User{username=USERNAME, type=TYPE, courses=COURSE_LIST}
         */
        return "User{username=" + this.getUsername() + ", type=" + this.getType() +
                ", courses=" + this.courses + "}";
    }

    @Override
    public int compareTo(User other){
        /**
         * Users are naturally ordered alphabetically by username.
         */
        return this.getUsername().compareTo(other.username);
    }
}
