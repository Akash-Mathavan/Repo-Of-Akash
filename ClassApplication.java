import java.util.*;
import java.util.ArrayList;

class Classroom {
    private String name;
    private List<Student> students = new ArrayList<>();         //create list
    private List<Assignment> assignments = new ArrayList<>();   

    public Classroom(String name) {         //constructor for Class
        this.name = name;
    }

    public String getName() {               //returns name using getter function
        return name;
    }

    public void addStudent(Student student) {                   //using parameter for object
        students.add(student);
    }

    public void scheduleAssignment(Assignment assignment) {     //using parameter for object
        assignments.add(assignment);
    }

    public List<Student> getStudents() {                        //returns list
        return students;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }
}

class Student {                                         //Another class created for Student
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {            //using getter function
        return id;
    }

    public String getName() {       //using getter function
        return name;
    }
}

class Assignment {                  //Another class created Assignment
    private String details;
    private boolean submitted;

    public Assignment(String details) {     //constructor created
        this.details = details;
        this.submitted = false;
    }

    public String getDetails() {            //using getter function for details
        return details;
    }

    public boolean isSubmitted() {            //using getter function for Submitted
        return submitted;
    }

    public void markSubmitted() {
        submitted = true;
    }
}

class VirtualClassroomManager {
    private Map<String, Classroom> classrooms = new HashMap<>();        //using HashMap
    
    private Map<Integer, Student> students = new HashMap<>();

    public void addClassroom(String className) {
        Classroom classroom = new Classroom(className);

        classrooms.put(className, classroom);
        System.out.println("Classroom " + className + " has been created.");
    }

    public void addStudent(int studentId, String className) {
        if (classrooms.containsKey(className)) {
            Student student = new Student(studentId, "Student " + studentId);

            classrooms.get(className).addStudent(student);
            
            students.put(studentId, student);
            System.out.println("Student " + studentId + " has been enrolled in " + className + ".");
        } else {
            System.out.println("Classroom " + className + " not found.");
        }
    }

    public void scheduleAssignment(String className, String assignmentDetails) {
        if (classrooms.containsKey(className)) {
            Assignment assignment = new Assignment(assignmentDetails);
            classrooms.get(className).scheduleAssignment(assignment);
            System.out.println("Assignment for " + className + " has been scheduled.");
        } else {
            System.out.println("Classroom " + className + " not found.");
        }
    }

    public void submitAssignment(int studentId, String className, String assignmentDetails) {
        if (students.containsKey(studentId) && classrooms.containsKey(className)) {
            Classroom classroom = classrooms.get(className);
            List<Assignment> assignments = classroom.getAssignments();
            for (Assignment assignment : assignments) {
                if (assignment.getDetails().equals(assignmentDetails)) {
                    assignment.markSubmitted();
                    System.out.println("Assignment submitted by Student " + studentId + " in " + className + ".");
                    return;
                }
            }
            System.out.println("Assignment not found in " + className + ".");
        } else {
            System.out.println("Student " + studentId + " or Classroom " + className + " not found.");
        }
    }
}

public class ClassApplication{
    public static void main(String[] args) {
        VirtualClassroomManager manager = new VirtualClassroomManager();

        // create classroom
        manager.addClassroom("Google");

        // create student to a classroom
        manager.addStudent(2, "Google");

        // Schedule assignment
        manager.scheduleAssignment("Google", "Solve Chapter 1 Exercises");

        // Submit assignment
        manager.submitAssignment(2, "Google", "Solve Chapter 1 Exercises");
    }
}