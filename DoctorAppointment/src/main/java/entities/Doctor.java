package entities;

import data.enums.Speciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    private Long id;
    private String name;
    private Speciality speciality;
    List<Slot> slots=new ArrayList<>();
}
