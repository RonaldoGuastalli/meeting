package com.company.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Room {
    private int capacity;
    private int cleanTime;
    private String multimedia;

    private static List<Meeting> meetings = new ArrayList<>();
    private static List<Room> roomsAvailable = new ArrayList<>();
    private static List<Room> roomsNotAvailable = new ArrayList<>();

    public Room(int capacity, String multimedia) {
        this.capacity = capacity;
        this.multimedia = multimedia.toUpperCase();
        roomsAvailable.add(this);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCleanTime() {
        return cleanTime;
    }

    public void setCleanTime(int cleanTime) {
        this.cleanTime = cleanTime;
    }

    public boolean isMultimedia(String YesOrNo) {
        return this.multimedia.equalsIgnoreCase(YesOrNo);
    }

    public String getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(String multimedia) {
        this.multimedia = multimedia;
    }


    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public static List<Room> checkRooms(Meeting meeting) {
        List<Room> possibleRooms = roomsAvailable.stream()
                .filter(room -> room.getCapacity() >= meeting.getCapacity() && room.isMultimedia(meeting.getRequiredMultimedia()))
                .sorted(Comparator.comparing(Room::getCapacity))
                .collect(Collectors.toList());
        return possibleRooms;
    }

    public static void bookRoom(Room room, Meeting meeting) {
        roomsAvailable.remove(room);
        meetings.add(meeting);
        roomsNotAvailable.add(room);
    }

    public static List<Room> checkReservedRooms() {
        return roomsNotAvailable;
    }

    public static List<Room> checkNotReservedRooms() {
        return roomsAvailable;
    }


    public boolean isAvailable(Room room) {
        return roomsAvailable.contains(room);
    }
}
