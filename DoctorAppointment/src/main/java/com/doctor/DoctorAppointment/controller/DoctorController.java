package com.doctor.DoctorAppointment.controller;


import com.doctor.DoctorAppointment.controller.request.DoctorCreateRequest;
import com.doctor.DoctorAppointment.controller.request.SlotRequest;
import com.doctor.DoctorAppointment.service.DoctorService;
import entities.Doctor;
import entities.Patient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorController {

    private DoctorService doctorService;


    DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }


    @PostMapping("/register/doctor")
    public ResponseEntity<Doctor> registerDoctor(@RequestBody DoctorCreateRequest doctorCreateRequest){
        Doctor doctor = doctorService.register(doctorCreateRequest);
        return new ResponseEntity<>(doctor,HttpStatus.CREATED);

    }

    @PostMapping("/add/slots")
    public Doctor addSlots(@RequestBody SlotRequest slotRequest){
        return doctorService.addSlots(slotRequest);
    }


    @GetMapping("/getAll/doctor")
    public List<Doctor> getAllPatients(){
        return doctorService.getAllDoctors();
    }

}
