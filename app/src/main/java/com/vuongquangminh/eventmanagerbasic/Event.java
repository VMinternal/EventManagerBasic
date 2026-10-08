package com.vuongquangminh.eventmanagerbasic;

import java.io.Serializable;

/**
 * Event Model Class
 * Represents the Event entity within the Event Management system.
 */
public class Event implements Serializable {
    private String name;
    private String location;
    private String date;
    private String time;
    private boolean requiresRiskAssessment;
    private String reporter;
    private String description;


    public Event(String name, String location, String date, String time,
                 boolean requiresRiskAssessment, String reporter, String description) {
        this.name = name;
        this.location = location;
        this.date = date;
        this.time = time;
        this.requiresRiskAssessment = requiresRiskAssessment;
        this.reporter = reporter;
        this.description = description;
    }

    // Getters và Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isRequiresRiskAssessment() {
        return requiresRiskAssessment;
    }

    public void setRequiresRiskAssessment(boolean requiresRiskAssessment) {
        this.requiresRiskAssessment = requiresRiskAssessment;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Supports data printing/logging for debugging purposes.
    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", requiresRiskAssessment=" + requiresRiskAssessment +
                ", reporter='" + reporter + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}