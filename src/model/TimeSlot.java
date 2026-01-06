package model;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class TimeSlot {

    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;

    public TimeSlot(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        if (endTime.isBefore(startTime) || endTime.equals(startTime)) {
            throw new IllegalArgumentException("End time must be after start time");
        }
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean conflictsWith(TimeSlot other) {
        if (this.day != other.day) {
            return false;
        }
        return this.startTime.isBefore(other.endTime)
                && other.startTime.isBefore(this.endTime);
    }

    public DayOfWeek getDay() {
        return day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
