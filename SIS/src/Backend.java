import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Scanner;

/**
 * This class represents that backend that SIS (frontend) interfaces with.
 * It creates the course and user databases from the input files. It is resposible for taking requests from SIS and
 * invoking the appropriate operations on the databases.
 *
 * @author Brian Mirabito
 */

public class Backend extends Object {
    /**
     * constructor
     */
    private CourseDB courseDB;
    private UserDB userDB;

    public Backend(String courseFile, String professorFile, String studentFile) throws FileNotFoundException{
        courseDB = new CourseDB();
        userDB = new UserDB();
        initializeCourseDB(courseFile);
        initializeUserDB(professorFile, studentFile);
    }

    private void initializeCourseDB(String courseFile) throws FileNotFoundException{
        /**
         * A utility method for initializing the course database.
         */
        try (Scanner input = new Scanner(new File(courseFile))) {
            while(input.hasNext()){
                String[] fields = input.nextLine().split(",");
                Course course = new Course(Integer.parseInt(fields[0]), fields[1], Integer.parseInt(fields[2]));
                this.courseDB.addValue(course);
            }
        }
    }

    private void initializeUserDB(String professorFile, String studentFile) throws FileNotFoundException{
        /**
         * A utility method for initializing the user database.
         */
        try (Scanner input = new Scanner(new File(professorFile))) {
            while(input.hasNext()){
                String[] fields = input.nextLine().split(",");
                Professor professor = new Professor(fields[0]);
                addCourses(professor, fields);
                this.userDB.addValue(professor);
            }
        }
        try (Scanner input = new Scanner(new File(studentFile))) {
            while(input.hasNext()){
                String[] fields = input.nextLine().split(",");
                Student student = new Student(fields[0]);
                addCourses(student, fields);
                this.userDB.addValue(student);
            }
        }
    }

    private void addCourses(User user, String[] courseIds){
        /**
         * A utility "utility" method. Used by initializeUserDB when adding a professor or student to a
         * collection of courses.
         */
        for(int i = 1; i<courseIds.length; i++){
            if(user.getType() == User.UserType.PROFESSOR){
                this.courseDB.getValue(Integer.parseInt(courseIds[i])).addProfessor(user.getUsername());
            } else {
                this.courseDB.getValue(Integer.parseInt(courseIds[i])).addStudent(user.getUsername());
            }
            user.addCourse(this.courseDB.getValue(Integer.parseInt(courseIds[i])));
        }
    }

    public boolean courseExists(int id){
        /**
         * Check whether a course exists or not.
         */
        return this.courseDB.hasKey(id);
    }

    public boolean enrollStudent(String username, int courseId){
        /**
         * Enroll a student in a course. This requires they are added to both the course and the student's courses.
         */
        if(this.userDB.getValue(username).getCourses().contains(this.courseDB.getValue(courseId))) {
            return false;
        } else {
            this.userDB.getValue(username).addCourse(this.courseDB.getValue(courseId));
            return true;
        }
    }

    public Collection<Course> getAllCourses(){
        /**
         * Get all the courses in the system.
         */
        return this.courseDB.getAllValues();
    }

    public Collection<User> getAllUsers(){
        /**
         * Get all users in the system.
         */
        return this.userDB.getAllValues();
    }

    public boolean isStudent(String username){
        /**
         * Check whether a username belongs to a student.
         */
        return this.userDB.getValue(username).getType() == User.UserType.STUDENT;
    }

    public Course getCourse(int id){
        /**
         * Get a course by id.
         */
        return this.courseDB.getValue(id);
    }

    public boolean unenrollStudent(String username, int courseId){
        /**
         * Unenroll a student in a course. This requires they are removed from both the course and the student's courses.
         */
        if(this.userDB.hasKey(username) && this.courseExists(courseId)){
            this.userDB.getValue(username).removeCourse(this.courseDB.getValue(courseId));
            return true;
        } else {
            return false;
        }
    }

    public boolean userExists(String username){
        /**
         * Check if a username exists in the system.
         */
        return this.userDB.hasKey(username);
    }

    public Collection<Course> getCoursesUser(String username){
        /**
         * Get the courses for a particular user.
         */
        return this.userDB.getValue(username).getCourses();
    }

}
