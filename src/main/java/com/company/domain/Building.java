package com.company.domain;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Building {
    private String name;
    private int numberOfFloor;
    private List<Floor> floors = new ArrayList<>();

    public Building() {
    }

    public Building(String name, int numberOfFloor) {
        this.name = name;
        this.numberOfFloor = numberOfFloor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    public void setNumberOfFloor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    public void setFloors(Floor floors) {
        this.floors.add(floors);
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public List<Room> getRooms() {
        List<Room> rooms = new ArrayList<>();
        floors.stream()
        .map(floor -> rooms.addAll(floor.getRooms()))
        .collect(Collectors.toList());
        return rooms;
    }

}
