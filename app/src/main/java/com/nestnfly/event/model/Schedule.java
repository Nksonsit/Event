package com.nestnfly.event.model;

public class Schedule {
    private String Time;
    private String Schedule;
    private String Event;
    private String Location;

    public Schedule() {
    }

    public Schedule(String time, String schedule, String event, String location) {
        Time = time;
        Schedule = schedule;
        Event = event;
        Location = location;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getSchedule() {
        return Schedule;
    }

    public void setSchedule(String schedule) {
        Schedule = schedule;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
