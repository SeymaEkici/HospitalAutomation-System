package model;

public class DoctorSchedule {
    
    private int scheduleId;
    private int doctorId;
    private String scheduleDate;
    private String workHours;

    public DoctorSchedule() {}

    public DoctorSchedule(int scheduleId, int doctorId, String scheduleDate, String workHours) {
        this.scheduleId = scheduleId;
        this.doctorId = doctorId;
        this.scheduleDate = scheduleDate;
        this.workHours = workHours;
    }

    public int getScheduleId() {
        return scheduleId;
    }
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }
    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getWorkHours() {
        return workHours;
    }
    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }

    @Override
    public String toString() {
        return "DoctorSchedule [scheduleId=" + scheduleId + ", doctorId=" + doctorId + ", scheduleDate=" + scheduleDate + ", workHours=" + workHours + "]";
    }
}
