package service;

import model.Course;
import model.StudentSchedule;

public class CoursePlannerService {

    private static final int MAX_CREDITS = 18;

    public void addCourse(StudentSchedule schedule, Course newCourse) {

        // Check credit limit
        if (schedule.getTotalCredits() + newCourse.getCredits() > MAX_CREDITS) {
            throw new IllegalStateException("Credit limit exceeded");
        }

        // Check time conflicts
        for (int i = 0; i < schedule.getCourses().size(); i++) {
            Course existing = schedule.getCourses().get(i);

            if (existing.getTimeSlot()
                        .conflictsWith(newCourse.getTimeSlot())) {
                throw new IllegalStateException(
                        "Time conflict with " + existing.getCode());
            }
        }


        schedule.addCourse(newCourse);
    }

    public void removeCourse(StudentSchedule schedule, String courseCode) {
        schedule.removeCourse(courseCode);
    }
}
