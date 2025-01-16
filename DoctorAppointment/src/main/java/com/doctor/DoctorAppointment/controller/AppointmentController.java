package com.doctor.DoctorAppointment.controller;

import com.doctor.DoctorAppointment.service.AppointmentService;
import entities.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppointmentController {

    private AppointmentService appointmentService;

    @Autowired
    AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/patient/checkAppointment/{patient}")
    public List<Appointment> checkPatientAppointment(@PathVariable String patient){
        return appointmentService.checkPatientAppointment(patient);
    }

    @PostMapping("/doctor/checkAppointment/{doctor}")
    public List<Appointment> checkDoctorAppointment(@PathVariable String doctor){
        return appointmentService.checkDoctorAppointment(doctor);
    }
}
