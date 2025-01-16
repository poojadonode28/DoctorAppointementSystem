package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Slot {
    private int id;
    private Date startTime;
    private Date endTime;
    private boolean isBooked;
}
