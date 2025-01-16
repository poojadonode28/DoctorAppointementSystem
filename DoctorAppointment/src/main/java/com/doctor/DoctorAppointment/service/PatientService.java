package com.doctor.DoctorAppointment.service;

import com.doctor.DoctorAppointment.controller.request.AppointmentRequest;
import com.doctor.DoctorAppointment.controller.response.BookAppointmentResponse;
import com.doctor.DoctorAppointment.controller.response.GetDoctorOnSpecialityResponse;
import com.doctor.DoctorAppointment.repository.PatientRepository;
import data.enums.Speciality;
import entities.Appointment;
import entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private PatientRepository patientRepository;
    private DoctorService doctorService;

    private AppointmentService appointmentService;

    @Autowired
    public PatientService(PatientRepository patientRepository,DoctorService
                          doctorService,@Lazy AppointmentService appointmentService) {
        this.patientRepository = patientRepository;
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
    }

    public Patient registerPatient(String name) {
        return patientRepository.register(name);
    }

    public List<GetDoctorOnSpecialityResponse> getDoctorsOnSpeciality(Speciality speciality) {
        return doctorService.getDoctorsOnSpeciality(speciality);
    }

    public BookAppointmentResponse bookAppointment(AppointmentRequest appointmentRequest) {
       Boolean book = doctorService.bookAppointment(appointmentRequest);
       if(book){
           Patient patient = patientRepository.getPatient(appointmentRequest);
           Appointment appointment = appointmentService.bookAppointment(appointmentRequest);
           patient.getAppointments().add(appointment);
           return new BookAppointmentResponse(patient,"Appointment booked successfully");
       }else{
           return new BookAppointmentResponse(null,"Appointment not available");
       }
    }

    public List<Patient> getAllPatients() {
        return patientRepository.getAllPatient();
    }

    public Patient getPatientByName(String name) {
        return patientRepository.getPatientByName(name);
    }
}