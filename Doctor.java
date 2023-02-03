import java.util.*;

public class Doctor {

    private long id;
    private String name;
    private String startHour;
    private String endHour;
    private MedicalUnit unit;
    private List<AppointmentHours> availableHours;

    public long getId() {
        return id;
    }

    public Doctor(long id, String name, MedicalUnit unit, String startHour, String endHour, List<AppointmentHours> availableHours){
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.startHour = startHour;
        this.endHour = endHour;
        this.availableHours = availableHours;
    }

    public String getName() {
        return name;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public MedicalUnit getUnit() {
        return unit;
    }

    public List<AppointmentHours> getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(List<AppointmentHours> availableHours) {
        this.availableHours = availableHours;
    }

    private List<AppointmentHours> bookedHours = new ArrayList<>();

    public List<AppointmentHours> getBookedHours() {
        return bookedHours;
    }

    public int getAvailabilityCount() {
        return 0;
    }



}
