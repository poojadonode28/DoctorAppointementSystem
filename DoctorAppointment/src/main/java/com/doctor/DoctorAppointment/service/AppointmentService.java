package com.doctor.DoctorAppointment.service;


import com.doctor.DoctorAppointment.controller.request.AppointmentRequest;
import com.doctor.DoctorAppointment.repository.AppointmentRepository;
import com.doctor.DoctorAppointment.repository.PatientRepository;
import entities.Appointment;
import entities.Doctor;
import entities.Patient;
import entities.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private PatientService patientService;

    private DoctorService doctorService;

    @Autowired
    AppointmentService(AppointmentRepository appointmentRepository, PatientService patientService, DoctorService doctorService) {
        this.appointmentRepository = appointmentRepository;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }


    public Appointment bookAppointment(AppointmentRequest appointmentRequest) {
        return appointmentRepository.bookAppointment(appointmentRequest);
    }

    public List<Appointment> checkPatientAppointment(String name) {
        Patient patient = patientService.getPatientByName(name);
        return patient.getAppointments();
    }

    public List<Appointment> checkDoctorAppointment(String name) {
        Doctor doctor = doctorService.findDoctorByName(name);
        List<Patient> patients = patientService.getAllPatients();
        List<Appointment> appointments = new ArrayList<>();
        List<Slot> slots = doctor.getSlots();

        for (Slot slot : slots) {
            if (slot.isBooked()) {
                for (Patient patient : patients) {
                    for (Appointment appointment : patient.getAppointments()) {
                        for (Slot appointmentSlot : appointment.getSlots()) {
                            if (appointment.getDoctor().equals(doctor.getName()) &&
                                    appointmentSlot.getStartTime().equals(slot.getStartTime()) &&
                                    appointmentSlot.getEndTime().equals(slot.getEndTime())) {
                                Appointment newAppointment = new Appointment();
                                newAppointment.setDoctor(doctor.getName());
                                newAppointment.getSlots().add(slot);
                                newAppointment.setPatient(patient.getName());
                                appointments.add(newAppointment);
                            }
                        }
                    }
                }
            }
        }
        return appointments;
    }

}
