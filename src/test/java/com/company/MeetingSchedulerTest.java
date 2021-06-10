package com.company;


import com.company.domain.Building;
import com.company.domain.Floor;
import com.company.domain.Meeting;
import com.company.domain.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class MeetingSchedulerTest extends MeetingScheduler {

    private Building building = null;
    private MeetingScheduler meetingScheduler = null;
    private Floor floor = null;
    private Room room1 = null;
    private Room room2 = null;
    private Meeting meeting = null;
    private List<Meeting> meetings = null;
    private List<Floor> floors = null;
    private static List<Room> roomsAvailable;
    private static List<Room> roomsNotAvailable;


    @BeforeEach
    @DisplayName("Setup before the test.")
    void setup() {
        this.building = new Building();
        this.building.setName("Building 2021");
        this.building.setNumberOfFloor(1);

        this.floor = new Floor(1, 2);
        this.floors = new ArrayList<>();
        this.roomsNotAvailable = Collections.emptyList();
        this.roomsAvailable = this.floor.getRooms();
        this.room1 = new Room(3, "Y");
        this.room2 = new Room(6, "N");
        this.floor.setRooms(room1);
        this.floor.setRooms(room2);
        this.floors.add(floor);
        this.building.setFloors(floor);

        this.meetingScheduler = new MeetingScheduler();

        meetings = new ArrayList<>();
        this.meetings.add(meeting);
        this.meeting = new Meeting(LocalDate.now().toString(), "11:40", 60, 3, "Y");

    }


    @Test
    @DisplayName("Try adding building, floor, room and media resource information.")
    void testAddBuildInformation() {
        Assertions.assertEquals("Building 2021", this.building.getName());
        Assertions.assertEquals(1, this.building.getNumberOfFloor());
        Assertions.assertEquals(2, this.building.getRooms().size());
    }

    @Test
    @DisplayName("Basic information the floor")
    void testAddFloorInformation() {
        Assertions.assertEquals(1, this.floors.size());
        Assertions.assertEquals(2, this.floor.getNumberOfRoom());
        Assertions.assertEquals(2, this.floor.getRooms().size());
    }

    @Test
    @DisplayName("Adding rooms on building floors")
    void testAddRoomInTheFloor() {
        Room newRoom = new Room(12, "Y");
        this.floor.setRooms(newRoom);
        Assertions.assertEquals(3, this.floor.getRooms().size());
    }

    @Test
    @DisplayName("Available rooms.")
    void testRoomAvailable() {
        Assertions.assertEquals(2, roomsAvailable.size());
    }



}