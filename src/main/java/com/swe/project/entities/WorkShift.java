package com.swe.project.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Embeddable 
public class WorkShift {

    @Enumerated(EnumType.STRING) // Stores "MONDAY", "TUESDAY" etc. as strings
    @Column(name = "DAY_OF_WEEK", nullable = false)
    private DayOfWeek dayOfWeek;

    @Temporal(TemporalType.TIME)
    @Column(name = "START_TIME", nullable = false)
    private LocalTime startTime;

    @Temporal(TemporalType.TIME)
    @Column(name = "END_TIME", nullable = false)
    private LocalTime endTime;
}