package com.company.domain;

import java.util.ArrayList;
import java.util.List;

public class Floor {

    private int nameNumber;
    private int numberOfRoom;
    private List<Room> romm = new ArrayList<>();


    public Floor(int nameNumber, int numberOfRoom) {
        this.nameNumber = nameNumber;
        this.numberOfRoom = numberOfRoom;
    }

    public int getNameNumber() {
        return nameNumber;
    }

    public void setNameNumber(int nameNumber) {
        this.nameNumber = nameNumber;
    }

    public int getNumberOfRoom() {
        return numberOfRoom;
    }

    public void setNumberOfRoom(int numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }

    public void setRooms(Room room) {
        this.romm.add(room);
    }

    public List<Room> getRooms() {
        return romm;
    }

}
