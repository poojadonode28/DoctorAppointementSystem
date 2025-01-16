package com.doctor.DoctorAppointment.controller.response;

import entities.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookAppointmentResponse {
    private Patient patient;
    private String message;

}
