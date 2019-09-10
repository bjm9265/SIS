/**
 * A professor prefers to list their courses by ascending course level, and if there is a tie then alphabetically by
 * course name.
 *
 * @author Brian Mirabito
 */

public class Professor extends User{
    public Professor(String username){
        /**
         * Create a new professor. Here you want to pass to the parent, User, a comparator that orders courses
         * first by ascending course level and second alphabetically by course name.
         */
        super(username, UserType.PROFESSOR, (Course c, Course c2) -> {
            int result = c.getLevel() - c2.getLevel();
            if(result == 0){
                return c.getName().compareTo(c2.getName());
            }
            return result;
        });

    }
}
