package com.doctor.DoctorAppointment.repository;


import com.doctor.DoctorAppointment.controller.request.AppointmentRequest;
import entities.Appointment;
import entities.Slot;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AppointmentRepository {

    HashMap<Long,Appointment> appointmentMap = new HashMap<>();
    AtomicInteger integer = new AtomicInteger(1);


    public Appointment bookAppointment(AppointmentRequest appointmentRequest) {
        long id = integer.getAndIncrement();
        Appointment appointment = new Appointment();
        appointment.setId(id);
        appointment.setDoctor(appointmentRequest.getDoctorName());
        appointment.setPatient(appointmentRequest.getPatientName());
        appointment.getSlots().add(appointmentRequest.getSlot());
        return appointment;
    }
}
