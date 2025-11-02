package com.swe.project.models;

import java.util.List;

public class TimeSlotResponse {
    List<Integer> listOfAvailableTimeSlots;

    public List<Integer> getListOfAvailableTimeSlots() {
        return listOfAvailableTimeSlots;
    }

    public void setListOfAvailableTimeSlots(List<Integer> listOfAvailableTimeSlots) {
        this.listOfAvailableTimeSlots = listOfAvailableTimeSlots;
    }
}
