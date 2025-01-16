package com.doctor.DoctorAppointment.controller.request;

import data.enums.Speciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorCreateRequest {

    private String name;
    private Speciality speciality;
}
