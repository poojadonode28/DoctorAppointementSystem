package com.doctor.DoctorAppointment.repository;

import com.doctor.DoctorAppointment.controller.request.AppointmentRequest;
import entities.Appointment;
import entities.Patient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PatientRepository {

    private HashMap<Long, Patient> patientMap = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);

    public Patient register(String name) {
        Long id = idCounter.getAndIncrement();
        Patient patient = new Patient();
        patient.setId(id);
        patient.setName(name);
        patientMap.put(id, patient);
        return patient;
    }

    public Patient getPatientByName(String name) {
        for(Map.Entry<Long,Patient> map:patientMap.entrySet()){
            if(map.getValue().getName().equals(name)){
                return map.getValue();
            }
        }
        return null;
    }

    public Patient getPatient(AppointmentRequest appointmentRequest) {

        for(Map.Entry<Long,Patient> map:patientMap.entrySet()){
            if(map.getValue().getName().equals(appointmentRequest.getPatientName())){
                return map.getValue();
            }
        }
        return null;
    }

    public List<Patient> getAllPatient() {
        List<Patient> patients = new ArrayList<>();
        for(Map.Entry<Long,Patient> map:patientMap.entrySet()){
            patients.add(map.getValue());
        }
        return patients;
    }
}