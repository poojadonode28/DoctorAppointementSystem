package com.doctor.DoctorAppointment.controller.response;

import entities.Slot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDoctorOnSpecialityResponse {

    private String name;
    private List<Slot> availableSlots;
}
