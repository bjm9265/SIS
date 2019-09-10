/**
 * A student prefers to list their courses alphabetically by course name.
 *
 * @author Brian Mirabito
 */
public class Student extends User{
    /**
     * Create a new student. Here you want to pass to the parent, User, a comparator that orders the courses
     * ascending by course name.
     * @param username
     */
    public Student(String username){
        super(username, UserType.STUDENT, new CourseComparator());
    }
}
