import java.util.*;

public enum AppointmentHours implements Iterable<AppointmentHours> {

    NULL("No available hours"),
    EIGHT("08:00"),
    NINE("09:00"),
    TEN("10:00"),
    ELEVEN("11:00"),
    TWELVE("12:00"),
    THIRTEEN("13:00"),
    FOURTEEN("14:00"),
    FIFTEEN("15:00"),
    SIXTEEN("16:00");

    private final String hour;

    AppointmentHours( String hour) {
        this.hour = hour;
    }

    public String getHour() {
        return hour;
    }

    public void setAvailable() {
    }

    public boolean isAvailable() {
        return true; //TODO check
    }

    @Override
    public Iterator<AppointmentHours> iterator() {
        return Collections.singletonList(this).iterator();
    }
}
