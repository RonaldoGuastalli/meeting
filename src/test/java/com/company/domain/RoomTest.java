package com.company.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class RoomTest {

    private Room room1;
    private Room room2;
    private Room room3;
    private List<Meeting> meetings;
    private List<Room> roomsAvailable;
    private List<Room> roomsNotAvailable;

    Meeting meeting;

    @BeforeEach
    void setUp() {
        room1 = new Room(2, "y");
        room2 = new Room(3, "N");
        room3 = new Room(6, "y");

        this.meetings = new ArrayList<>();
        this.roomsAvailable = Arrays.asList(room1, room2, room3);
        this.roomsNotAvailable = Collections.emptyList();

        meeting = new Meeting(LocalDate.now().toString(), "11:40", 60, 2, "Y");
    }

    @Test
    @DisplayName("Check the creation of a room.")
    void addRoom() {
        Assertions.assertEquals(2, room1.getCapacity());
        Assertions.assertEquals("Y", room1.getMultimedia());
    }

    @Test
    @DisplayName("Check list of available rooms.")
    void availableRooms() {
        Assertions.assertEquals(3, roomsAvailable.size());
        Assertions.assertEquals(0, roomsNotAvailable.size());
    }

    @Test
    @DisplayName("Test ordering by ability.")
    void ordemByAbilityRoom() {
        Assertions.assertEquals(2, room1.checkRooms(meeting).get(0).getCapacity());
    }

}