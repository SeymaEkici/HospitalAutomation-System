import java.util.ArrayList;
import java.util.List;

public class Initialize {

    private static MedicalUnit dermatology;
    private static MedicalUnit ent;
    private static MedicalUnit internalMedicine;
    private static MedicalUnit pediatry;
    private static MedicalUnit orthopedicsAndTraumatology;
    private static List<AppointmentHours> availableHoursForIdZero;
    private static List<AppointmentHours> availableHoursForIdOne;
    private static List<AppointmentHours> availableHoursForIdTwo;
    private static List<AppointmentHours> availableHoursForIdThree;
    private static List<AppointmentHours> availableHoursForIdFour;
    private static List<AppointmentHours> availableHoursForIdFive;
    private static List<AppointmentHours> availableHoursForIdSix;
    private static List<AppointmentHours> availableHoursForIdSeven;

    private static List<Doctor> doctorList;
    static List<MedicalUnit> unitList;

    public static void initializeData() {
        initializeUnits();
        initializeDoctorsAvailableHours();
        initializeDoctors();
    }

    public static void  initializeDoctors() {

        doctorList = new ArrayList<>();

        doctorList.add(new Doctor(0, "Seher Bal   ", dermatology, "NULL", "NULL", availableHoursForIdZero));
        doctorList.add(new Doctor(1, "Büşra Soylu", ent, AppointmentHours.EIGHT.getHour(), AppointmentHours.SIXTEEN.getHour(), availableHoursForIdOne));
        doctorList.add(new Doctor(2, "Hatice Keser", ent, AppointmentHours.EIGHT.getHour(), AppointmentHours.SIXTEEN.getHour(), availableHoursForIdTwo));
        doctorList.add(new Doctor(3, "Kemal Çetin", internalMedicine, AppointmentHours.TWELVE.getHour(), AppointmentHours.SIXTEEN.getHour(), availableHoursForIdThree));
        doctorList.add(new Doctor(4, "Fatih İris ", internalMedicine, AppointmentHours.TWELVE.getHour(), AppointmentHours.SIXTEEN.getHour(), availableHoursForIdFour));
        doctorList.add(new Doctor(5, "Betül Birce", internalMedicine, AppointmentHours.EIGHT.getHour(), AppointmentHours.SIXTEEN.getHour(), availableHoursForIdFive));
        doctorList.add(new Doctor(6, "Kaan Kalem ", pediatry, AppointmentHours.EIGHT.getHour(), AppointmentHours.SIXTEEN.getHour(), availableHoursForIdSix));
        doctorList.add(new Doctor(7, "Kemal Ünlü ", orthopedicsAndTraumatology, AppointmentHours.EIGHT.getHour(), AppointmentHours.SIXTEEN.getHour(), availableHoursForIdSeven));
    }

    public static void initializeDoctorsAvailableHours(){

        availableHoursForIdZero = new ArrayList<>();
        availableHoursForIdZero.add(AppointmentHours.NULL);

        availableHoursForIdOne = new ArrayList<>();
        availableHoursForIdOne.add(AppointmentHours.EIGHT);
        availableHoursForIdOne.add(AppointmentHours.NINE);
        availableHoursForIdOne.add(AppointmentHours.TEN);
        availableHoursForIdOne.add(AppointmentHours.ELEVEN);
        availableHoursForIdOne.add(AppointmentHours.TWELVE);
        availableHoursForIdOne.add(AppointmentHours.THIRTEEN);
        availableHoursForIdOne.add(AppointmentHours.FOURTEEN);
        availableHoursForIdOne.add(AppointmentHours.FIFTEEN);
        availableHoursForIdOne.add(AppointmentHours.SIXTEEN);

        availableHoursForIdTwo = new ArrayList<>();
        availableHoursForIdTwo.add(AppointmentHours.EIGHT);
        availableHoursForIdTwo.add(AppointmentHours.NINE);
        availableHoursForIdTwo.add(AppointmentHours.TEN);
        availableHoursForIdTwo.add(AppointmentHours.ELEVEN);
        availableHoursForIdTwo.add(AppointmentHours.TWELVE);
        availableHoursForIdTwo.add(AppointmentHours.THIRTEEN);
        availableHoursForIdTwo.add(AppointmentHours.FOURTEEN);
        availableHoursForIdTwo.add(AppointmentHours.FIFTEEN);
        availableHoursForIdTwo.add(AppointmentHours.SIXTEEN);

        availableHoursForIdThree = new ArrayList<>();
        availableHoursForIdThree.add(AppointmentHours.THIRTEEN);
        availableHoursForIdThree.add(AppointmentHours.FOURTEEN);
        availableHoursForIdThree.add(AppointmentHours.FIFTEEN);
        availableHoursForIdThree.add(AppointmentHours.SIXTEEN);

        availableHoursForIdFour = new ArrayList<>();
        availableHoursForIdFour.add(AppointmentHours.THIRTEEN);
        availableHoursForIdFour.add(AppointmentHours.FOURTEEN);
        availableHoursForIdFour.add(AppointmentHours.FIFTEEN);
        availableHoursForIdFour.add(AppointmentHours.SIXTEEN);

        availableHoursForIdFive = new ArrayList<>();
        availableHoursForIdFive.add(AppointmentHours.EIGHT);
        availableHoursForIdFive.add(AppointmentHours.NINE);
        availableHoursForIdFive.add(AppointmentHours.TEN);
        availableHoursForIdFive.add(AppointmentHours.ELEVEN);
        availableHoursForIdFive.add(AppointmentHours.TWELVE);
        availableHoursForIdFive.add(AppointmentHours.THIRTEEN);
        availableHoursForIdFive.add(AppointmentHours.FOURTEEN);
        availableHoursForIdFive.add(AppointmentHours.FIFTEEN);
        availableHoursForIdFive.add(AppointmentHours.SIXTEEN);

        availableHoursForIdSix = new ArrayList<>();
        availableHoursForIdSix.add(AppointmentHours.EIGHT);
        availableHoursForIdSix.add(AppointmentHours.NINE);
        availableHoursForIdSix.add(AppointmentHours.TEN);
        availableHoursForIdSix.add(AppointmentHours.ELEVEN);
        availableHoursForIdSix.add(AppointmentHours.TWELVE);
        availableHoursForIdSix.add(AppointmentHours.THIRTEEN);
        availableHoursForIdSix.add(AppointmentHours.FOURTEEN);
        availableHoursForIdSix.add(AppointmentHours.FIFTEEN);
        availableHoursForIdSix.add(AppointmentHours.SIXTEEN);

        availableHoursForIdSeven = new ArrayList<>();
        availableHoursForIdSeven.add(AppointmentHours.EIGHT);
        availableHoursForIdSeven.add(AppointmentHours.NINE);
        availableHoursForIdSeven.add(AppointmentHours.TEN);
        availableHoursForIdSeven.add(AppointmentHours.ELEVEN);
        availableHoursForIdSeven.add(AppointmentHours.TWELVE);
        availableHoursForIdSeven.add(AppointmentHours.THIRTEEN);
        availableHoursForIdSeven.add(AppointmentHours.FOURTEEN);
        availableHoursForIdSeven.add(AppointmentHours.FIFTEEN);
        availableHoursForIdSeven.add(AppointmentHours.SIXTEEN);

    }

    public static void initializeUnits() {

        unitList = new ArrayList<>();

        dermatology = new MedicalUnit(0,"Dermatoloji");
        ent = new MedicalUnit(1,"KBB");
        internalMedicine = new MedicalUnit(2,"Dahiliye");
        pediatry = new MedicalUnit(3,"Çocuk Hastalıkları");
        orthopedicsAndTraumatology = new MedicalUnit(4, "Ortopedi ve Travmatoloji");

        unitList.add(dermatology);
        unitList.add(ent);
        unitList.add(internalMedicine);
        unitList.add(pediatry);
        unitList.add(orthopedicsAndTraumatology);

    }

    public static List<Doctor> getDoctorList() {
        return doctorList;
    }

    public static List<MedicalUnit> getMedicalUnits() {
        return unitList;
    }
}
