import java.util.*;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

class Course {
    private String name;
    private int capacity;
    private Set<String> prerequisites;
    private int enrolledStudents;

    public Course(String name, int capacity, Set<String> prerequisites) {
        this.name = name;
        this.capacity = capacity;
        this.prerequisites = prerequisites;
        this.enrolledStudents = 0;
    }

    public void enrollStudent(Set<String> completedCourses) throws CourseFullException, PrerequisiteNotMetException {
        if (enrolledStudents >= capacity) {
            throw new CourseFullException("Error: Course is full.");
        }
        if (!completedCourses.containsAll(prerequisites)) {
            throw new PrerequisiteNotMetException("Error: Complete prerequisites before enrolling.");
        }
        enrolledStudents++;
        System.out.println("Enrollment successful in " + name);
    }

    public String getName() {
        return name;
    }
}

public class UniversityEnrollment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Set<String> completedCourses = new HashSet<>();
        completedCourses.add("Core Java"); 
        
        Course advancedJava = new Course("Advanced Java", 2, Set.of("Core Java"));
        
        try {
            System.out.print("Enroll in Course: ");
            String courseName = scanner.nextLine();
            if (courseName.equals(advancedJava.getName())) {
                advancedJava.enrollStudent(completedCourses);
            } else {
                System.out.println("Course not found.");
            }
        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

