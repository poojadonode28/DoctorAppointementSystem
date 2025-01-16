package com.doctor.DoctorAppointment.repository;


import com.doctor.DoctorAppointment.controller.request.AppointmentRequest;
import com.doctor.DoctorAppointment.controller.request.DoctorCreateRequest;
import com.doctor.DoctorAppointment.controller.request.SlotRequest;
import com.doctor.DoctorAppointment.controller.response.GetDoctorOnSpecialityResponse;
import data.enums.Speciality;
import entities.Doctor;
import entities.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class DoctorRepository {

    HashMap<Long, Doctor> doctorMap = new HashMap<>();
    AtomicInteger integer = new AtomicInteger(1);

    private SlotRepository slotRepository;

    @Autowired
    DoctorRepository(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    public Doctor register(DoctorCreateRequest doctorCreateRequest) {
        Doctor doctor = new Doctor();
        long id = integer.getAndIncrement();

        doctor.setId(id);
        doctor.setName(doctorCreateRequest.getName());
        doctor.setSpeciality(doctorCreateRequest.getSpeciality());
        doctorMap.put(id, doctor);
        return doctor;
    }

    public Doctor getDoctor(String doctorName) {
        for (Map.Entry<Long, Doctor> entry : doctorMap.entrySet()) {
            if (entry.getValue().getName().equals(doctorName)) {
                return entry.getValue();
            }
        }
        return null;
    }


    public List<GetDoctorOnSpecialityResponse> getDoctorsOnSpeciality(Speciality speciality) {
        List<GetDoctorOnSpecialityResponse> responseList = new ArrayList<>();
        for (Map.Entry<Long, Doctor> entry : doctorMap.entrySet()) {
            GetDoctorOnSpecialityResponse response = new GetDoctorOnSpecialityResponse();
            ArrayList<Slot> availableSlots = new ArrayList<>();
            if (entry.getValue().getSpeciality().equals(speciality)) {
                entry.getValue().getSlots().forEach(slot -> {
                    if (!slot.isBooked()) {
                        availableSlots.add(slot);

                    }
                });
            }
            response.setName(entry.getValue().getName());
            response.setAvailableSlots(availableSlots);
            responseList.add(response);
        }
        return responseList;
    }

    public Boolean bookAppointment(AppointmentRequest appointmentRequest) {
        boolean flag = false;
        for (Map.Entry<Long, Doctor> map : doctorMap.entrySet()) {
            if (map.getValue().getName().equals(appointmentRequest.getDoctorName())) {
                for (Slot slot : map.getValue().getSlots()) {
                    if (slot.getStartTime().equals(appointmentRequest.getSlot().getStartTime()) &&
                            slot.getEndTime().equals(appointmentRequest.getSlot().getEndTime()) && !slot.isBooked()) {
                        slot.setBooked(true);
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) {
                break;
            }
        }
        return flag;
    }

    public List<Doctor> getAll() {
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();
        for (Map.Entry<Long, Doctor> entry : doctorMap.entrySet()) {
            doctorArrayList.add(entry.getValue());
        }
        return doctorArrayList;

    }
}
