package ui;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Scanner;

import model.Course;
import model.StudentSchedule;
import model.TimeSlot;
import service.CoursePlannerService;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        StudentSchedule schedule = new StudentSchedule();
        CoursePlannerService service = new CoursePlannerService();

        boolean running = true;

        System.out.println("=== Course Planner ===");

        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");

            try {
                switch (choice) {
                    case 1 -> addCourse(schedule, service);
                    case 2 -> removeCourse(schedule, service);
                    case 3 -> viewSchedule(schedule);
                    case 4 -> {
                        running = false;
                        System.out.println("Goodbye!");
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // ---------------- MENU METHODS ----------------

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Add course");
        System.out.println("2. Remove course");
        System.out.println("3. View schedule");
        System.out.println("4. Exit");
    }

    private static void addCourse(StudentSchedule schedule, CoursePlannerService service) {

        String code = readString("Course code: ");
        String name = readString("Course name: ");
        int credits = readInt("Credits: ");

        DayOfWeek day = DayOfWeek.valueOf(
                readString("Day (e.g. MONDAY): ").toUpperCase()
        );

        LocalTime start = LocalTime.parse(
                readString("Start time (HH:MM): ")
        );

        LocalTime end = LocalTime.parse(
                readString("End time (HH:MM): ")
        );

        TimeSlot timeSlot = new TimeSlot(day, start, end);
        Course course = new Course(code, name, credits, timeSlot);

        service.addCourse(schedule, course);

        System.out.println("Course added successfully.");
    }

    private static void removeCourse(StudentSchedule schedule, CoursePlannerService service) {
        String code = readString("Course code to remove: ");
        service.removeCourse(schedule, code);
        System.out.println("Course removed.");
    }

    private static void viewSchedule(StudentSchedule schedule) {
        if (schedule.getCourses().isEmpty()) {
            System.out.println("Schedule is empty.");
            return;
        }

        System.out.println("\nYour Schedule:");
        for (Course c : schedule.getCourses()) {
            System.out.println(
                    c.getCode() + " | " +
                    c.getName() + " | " +
                    c.getCredits() + " credits | " +
                    c.getTimeSlot().getDay() + " " +
                    c.getTimeSlot().getStartTime() + "-" +
                    c.getTimeSlot().getEndTime()
            );
        }

        System.out.println("Total credits: " + schedule.getTotalCredits());
    }

    // ---------------- INPUT HELPERS ----------------

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Enter a number: ");
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
