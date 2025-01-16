package com.doctor.DoctorAppointment.repository;


import com.doctor.DoctorAppointment.controller.request.SlotRequest;
import entities.Slot;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class SlotRepository {

    private AtomicInteger integer = new AtomicInteger(1);
    HashMap<Integer,Slot> slotRepository = new HashMap<>();

    public Slot addSlot(Slot slot){
        int id = integer.getAndIncrement();
        slot.setId(id);
        slotRepository.put(id,slot);
        return slot;
    }
}
