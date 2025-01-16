package com.doctor.DoctorAppointment.controller.request;

import entities.Slot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotRequest {
   private String doctorName;
   List<Slot> slots = new ArrayList<>();
}
