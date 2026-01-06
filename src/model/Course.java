package model;

public class Course {

    private String code;
    private String name;
    private int credits;
    private TimeSlot timeSlot;

    public Course(String code, String name, int credits, TimeSlot timeSlot) {
        if (credits <= 0) {
            throw new IllegalArgumentException("Credits must be positive");
        }
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.timeSlot = timeSlot;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }
}
