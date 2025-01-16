package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    private Long id;
    private String patient;
    private String doctor;
    List<Slot> slots=new ArrayList<>();
}
