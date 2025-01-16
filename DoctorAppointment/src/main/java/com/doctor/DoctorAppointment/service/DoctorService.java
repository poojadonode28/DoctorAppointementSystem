package com.doctor.DoctorAppointment.service;

import com.doctor.DoctorAppointment.controller.request.AppointmentRequest;
import com.doctor.DoctorAppointment.controller.request.DoctorCreateRequest;
import com.doctor.DoctorAppointment.controller.request.SlotRequest;
import com.doctor.DoctorAppointment.controller.response.GetDoctorOnSpecialityResponse;
import com.doctor.DoctorAppointment.repository.DoctorRepository;
import com.doctor.DoctorAppointment.repository.SlotRepository;
import data.enums.Speciality;
import entities.Doctor;
import entities.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;
    private SlotRepository slotRepository;

    @Autowired
    DoctorService(DoctorRepository doctorRepository,SlotRepository slotRepository){
        this.doctorRepository = doctorRepository;
        this.slotRepository = slotRepository;
    }

    public Doctor register(DoctorCreateRequest doctorCreateRequest){
        return doctorRepository.register(doctorCreateRequest);
    }

    public Doctor addSlots(SlotRequest slotRequest) {
        Doctor doctor = doctorRepository.getDoctor(slotRequest.getDoctorName());
        Calendar calendar = Calendar.getInstance();

        for (Slot slot : slotRequest.getSlots()) {
            calendar.setTime(slot.getStartTime());
            int startHour = calendar.get(Calendar.HOUR_OF_DAY);
            calendar.setTime(slot.getEndTime());
            int endHour = calendar.get(Calendar.HOUR_OF_DAY);

            if (startHour >= 9 && endHour <= 21 && slot.getStartTime().compareTo(slot.getEndTime()) < 60) {
                Slot slot1 = slotRepository.addSlot(slot);
                doctor.getSlots().add(slot1);
            }
        }
        return doctor;
    }

    public List<GetDoctorOnSpecialityResponse> getDoctorsOnSpeciality(Speciality speciality) {
        return doctorRepository.getDoctorsOnSpeciality(speciality);
    }

    public Boolean bookAppointment(AppointmentRequest appointmentRequest) {
        return doctorRepository.bookAppointment(appointmentRequest);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.getAll();
    }

    public Doctor findDoctorByName(String doctorName){
        Doctor doctor = doctorRepository.getDoctor(doctorName);
        return doctor;
    }
}
