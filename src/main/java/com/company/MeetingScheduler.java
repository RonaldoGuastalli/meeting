package com.company;

import com.company.config.Clear;
import com.company.domain.Building;
import com.company.domain.Floor;
import com.company.domain.Meeting;
import com.company.domain.Room;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class MeetingScheduler {
    private static final String MAIN_MENU = "\n\nHome Page Menu...";
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean endProgran = false;

        Building building = new Building();

        while (!endProgran) {
            switch (menu()) {
                case 1:
                    addBuildInformation(building);
                    break;
                case 2:
                    reserveMeetingRoom(building);
                    break;
                case 3:
                    listReseverdTime();
                    break;
                case 4:
                    System.out.println("Quitting the program...");
                    endProgran = true;
                    break;
                default:
                    System.out.println("Ivalid choice! Input correct number...");
                    System.out.println(MAIN_MENU);

            }
        }

    }

    private static void addBuildInformation(Building building) {
        Clear.screen();
        String name;
        int numberOfFloor = 0;
        int numberOfRoom = 0;
        int capacity = 0;
        String multimedia;

        System.out.println("\n\t\t\t\t\t\t---- INFORMATION ABOUT ROOM ----\n");

        System.out.println("Building Name: ");
        name = input.next();
        building.setName(name);

        System.out.println("-> Number Floor: ");
        numberOfFloor = input.nextInt();

        for (int i = 1; i <= numberOfFloor; i++) {
            System.out.println(String.format("\tFloor: %d | Number Room: ", i));
            numberOfRoom = input.nextInt();
            Floor floor = new Floor(i, numberOfRoom);

            for (int j = 1; j <= numberOfRoom; j++) {
                System.out.println(String.format("\t\tFloor: %d | Room %d | Capacity: ", i, j));
                capacity = input.nextInt();
                System.out.println(String.format("\t\tFloor: %d | Room %d | Capacity: %s | Multimedia (Y/N)", i, j, capacity));
                multimedia = input.next();
                Room room = new Room(capacity, multimedia);
                floor.setRooms(room);
            }
            building.setFloors(floor);
        }

    }

    private static void listReseverdTime() {
        Clear.screen();
        System.out.println("\n\t\t\t\t\t\t---- ROOM SCHEDULE ----\n");
        AtomicInteger count = new AtomicInteger(0);
        Room.checkReservedRooms().forEach(room -> {
            System.out.println(String.format("Room #%d | capacity %s | multimedia %s\n", count.intValue(), room.getCapacity(), room.getMultimedia()));
            System.out.println(String.format("\t\tMeeting Data #%d | date %s | start time %s | meeting time %d |  number of attendees %d",
                    count.intValue(),
                    room.getMeetings().get(count.intValue()).getStartDate(),
                    room.getMeetings().get(count.intValue()).getStartTime(),
                    room.getMeetings().get(count.intValue()).getMeetingTime(),
                    room.getMeetings().get(count.intValue()).getCapacity())
            );
            count.getAndIncrement();
        });

    }

    private static void reserveMeetingRoom(Building building) {
        Clear.screen();
        String startDate;
        String startTime;
        int meetingTime;
        int capacity;
        AtomicInteger count = new AtomicInteger();
        int chooseNumber;
        String multimedia;
        System.out.println("Date Metting (yyyy-MM-dd)");
        startDate = input.next();
        System.out.println("Start Time (HH:mm)");
        startTime = input.next();
        System.out.println("Meeting Time (min)");
        meetingTime = input.nextInt();
        System.out.println("Number of Attendees");
        capacity = input.nextInt();
        System.out.println("Required Multimedia (Y/N)");
        multimedia = input.next();
        Clear.screen();

        System.out.println("\n\t\t\t\t\t\t---- ROOM FREE SCHEDULE ----\n");
        Meeting meeting = new Meeting(startDate, startTime, meetingTime, capacity, multimedia);

        List<Room> rooms = building.getRooms();
        Room.checkRooms(meeting).forEach(room -> {
            if (room.isAvailable(room)) {
                System.out.println(String.format("Room Free #%d | capacity %s | multimedia %s", count.getAndIncrement(), room.getCapacity(), room.getMultimedia()));
            }
        });
        System.out.println("Choose a room number: ");
        chooseNumber = input.nextInt();
        Room.bookRoom(rooms.get(chooseNumber), meeting);

    }

    private static int menu() {
        Clear.screen();
        System.out.println("\nMenu:");
        System.out.println(" 1 - Pre Setup of Data");
        System.out.println(" 2 - Reserve Meeting Room");
        System.out.println(" 3 - List Reserved Time Spans");
        System.out.println(" 4 - Quit");
        System.out.println("Selection menu number: ");
        return input.nextInt();
    }


}
