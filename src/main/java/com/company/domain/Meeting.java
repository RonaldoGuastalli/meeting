package com.company.domain;

import com.company.exception.DateException;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Meeting {
    private LocalDate startDate;
    private String startTime;
    private int meetingTime;
    private int capacity;
    private String requiredMultimedia;

    public Meeting(String startDate, String startTime, int meetingTime, int capacity, String requiredMultimedia) {
        this.startDate = getParseDate(startDate);
        this.startTime = startTime;
        this.meetingTime = meetingTime;
        this.capacity = capacity;
        this.requiredMultimedia = requiredMultimedia.toUpperCase();
    }

    private LocalDate getParseDate(String startDate) {
        try {
            LocalDate parse = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE.ofPattern("yyyy-MM-dd"));
            if (!isValidDate(parse)) {
                throw new DateException("Date must equal greater than today");
            }
            return parse;
        } catch (DateException dt) {
            throw dt;
        } catch (DateTimeParseException ex) {
            throw ex;
        }
    }

    private boolean isValidDate(LocalDate parse) {
        return parse.isEqual(LocalDate.now()) || parse.isAfter(LocalDate.now());
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(int meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getRequiredMultimedia() {
        return requiredMultimedia;
    }

    public void setRequiredMultimedia(String requiredMultimedia) {
        this.requiredMultimedia = requiredMultimedia;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
