package com.doctor.DoctorAppointment.controller.request;

import entities.Slot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {
    private String patientName;
    private String doctorName;
    private Slot slot;
}
