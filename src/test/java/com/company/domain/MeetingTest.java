package com.company.domain;

import com.company.exception.DateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MeetingTest {

    private Meeting meeting;

    @BeforeEach
    void setUp() {
        meeting = new Meeting(LocalDate.now().toString(), "12:12", 60, 4, "y");
    }

    @Test
    @DisplayName("Wrong date format.")
    void formatDate() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            new Meeting("3", "12:12", 60, 4, "y");
        });
    }

    @Test
    @DisplayName("Correct date format.")
    void formatDateOK() {
        Assertions.assertEquals(LocalDate.now(), meeting.getStartDate());
    }

    @Test
    @DisplayName("Correct date format.")
    void formatDateBeforeToday() {
        DateException exception = assertThrows(DateException.class, () -> {
            meeting = new Meeting("1980-12-12", "12:12", 60, 4, "y");
        });
    }
}