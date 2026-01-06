package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentSchedule {

    private List<Course> courses;

    public StudentSchedule() {
        this.courses = new ArrayList<>();
    }

    public List<Course> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(String courseCode) {
        courses.removeIf(c -> c.getCode().equalsIgnoreCase(courseCode));
    }

    public int getTotalCredits() {
        int total = 0;
        for (Course c : courses) {
            total += c.getCredits();
        }
        return total;
    }
}
