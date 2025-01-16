package com.doctor.DoctorAppointment.controller;

import com.doctor.DoctorAppointment.controller.request.AppointmentRequest;
import com.doctor.DoctorAppointment.controller.response.BookAppointmentResponse;
import com.doctor.DoctorAppointment.controller.response.GetDoctorOnSpecialityResponse;
import com.doctor.DoctorAppointment.service.PatientService;
import data.enums.Speciality;
import entities.Appointment;
import entities.Patient;
import entities.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/register/{name}")
    public Patient registerPatient(@PathVariable String name) {
        return patientService.registerPatient(name);
    }


    @GetMapping("/getDoctorsOnSpeciality/{speciality}")
    public List<GetDoctorOnSpecialityResponse> getDoctorsOnSpeciality(@PathVariable Speciality speciality) {
            return patientService.getDoctorsOnSpeciality(speciality);
    }

    @PostMapping("/bookAppointment")
    public BookAppointmentResponse bookAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        return patientService.bookAppointment(appointmentRequest);
    }


    @GetMapping("/getAll/patients")
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }


}