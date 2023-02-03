import org.jetbrains.annotations.NotNull;
import java.util.*;

public class Main {

    static Scanner input;
    static Map<String, String> appointments;
    static String userName;
    static String userPassword;

    public static void main(String[] args) {

        try {
            initializeData();
            showMenu();
        } catch (Exception exception) {
            System.err.println("An error occurred !!");
            showMenu();
        }
    }

    public static boolean isAdmin(@NotNull String userName, String userPassword) {

        if (userName.equals("admin") && userPassword.equals("admin")) {
            System.out.println("Welcome, admin!");
            System.out.println("What would you like to do?");
            System.out.println("1) View doctor schedules");
            System.out.println("2) Sort doctors by their availability");
            System.out.println("3) Exit");

            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    viewDoctorSchedules();
                    break;
                case 2:
                    sortDoctorsByAvailability();
                    break;
                case 3:
                    System.out.println("Goodbye, admin!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    isAdmin(userName, userPassword);
                    break;
            }
        }
        return false;
    }

    public static void showMenu() {

        while (true) {

            appointments = new HashMap<>();
            input = new Scanner(System.in);
            System.out.println("1. View all doctors");
            System.out.println("2. Book an appointment");
            System.out.println("3. Cancel an appointment");
            System.out.println("4. View all appointments");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            if(choice>0 && choice<5){
                identifyUser();
            }

            switch (choice) {
                case 1:

                    listAllDoctors();
                    break;

                case 2:

                    bookAppointment();
                    break;

                case 3:

                    cancelAppointment();
                    break;
                case 4:

                    printBookedAppointments();
                    break;

                case 5:
                    return;

                default:
                    System.err.println("Please enter a value that given above");
                    showMenu();
            }
        }



    }

    private  static void initializeData() {
        Initialize.initializeData();
    }

    private static void identifyUser() {
        System.out.println("Enter the user name: ");
        userName = input.nextLine();
        input.nextLine();
        System.out.println("Enter the password: ");
        userPassword = input.nextLine();
        isAdmin(userName, userPassword);
    }

    public static void listAllDoctors(){

        System.out.println("Here all doctors, their units and their availability: \n");

        for (Doctor doctor : Initialize.getDoctorList()) {
            System.out.println("Doctor name: " + doctor.getName() + "\t\t\t Doctor's unit: " +doctor.getUnit() + "\t\t\t Available time range is: " + doctor.getStartHour() + "-" + doctor.getEndHour());
        }

        if (isAdmin(userName, userPassword)) {
            System.out.println("Do you want to sort the doctors by their availability?");
            String adminAnswer = input.nextLine();
            if (adminAnswer.equals("yes")) {

            }
        }

    }

    public static boolean bookAppointment(){

        List<Doctor> doctors = Initialize.getDoctorList();

        System.out.println("Select an unit: ");

        int index = 1;
        for(MedicalUnit units : Initialize.getMedicalUnits()){
            System.out.println(index + ") " + units.getMedUnitsName());
            index++ ;
        }
        int selectedUnit = input.nextInt();

        if(selectedUnit > 0 && selectedUnit < 6){

            System.out.println("Select a doctor: ");

            for (Doctor doctor: doctors) {
                if ((selectedUnit - 1) == doctor.getUnit().getMedUnitsId()) {
                    System.out.println(doctor.getId() + ") " + doctor.getName());
                }
            }
            int selectedDoctor = input.nextInt();
            System.out.println(selectedDoctor);

            boolean foundSelectedDoctor = false;
            Doctor selectedDoctorObject = null;
            for (Doctor doctor: doctors){
                if(doctor.getId() == (selectedDoctor)) {
                    System.out.println("Here is all available appointments: ");
                    System.out.println(doctor.getAvailableHours());
                    for (AppointmentHours availableHours : doctor.getAvailableHours()) {
                        System.out.println(availableHours.getHour());
                    }
                    foundSelectedDoctor = true;
                    selectedDoctorObject = doctor;
                    break;
                }
            }
            if (foundSelectedDoctor) {
                System.out.println("Please select a hour for book (hh:mm): ");
                input.nextLine();
                String desiredHour = input.nextLine();
                boolean foundAvailableHour = false;
                for (AppointmentHours availableHours : selectedDoctorObject.getAvailableHours()) {
                    if (availableHours.getHour().equals(desiredHour)) {
                        availableHours.setAvailable();
                        selectedDoctorObject.getAvailableHours().remove(availableHours);
                        System.out.println("Your appointment is successfully booked at " + desiredHour + " with Dr. " + selectedDoctorObject.getName());
                        foundAvailableHour = true;
                        break;
                    }
                }
                if (!foundAvailableHour) {
                    System.out.println("The selected hour is not available. Please select another hour.");
                    bookAppointment();
                }
            }


        }else {
            System.out.println("Please enter a value that given above!");
            bookAppointment();
        }

        return false;
    }

    public static boolean cancelAppointment() {
        List<Doctor> doctors = Initialize.getDoctorList();
        System.out.println("Enter the id of the doctor you booked appointment with: ");
        int doctorId = input.nextInt();

        boolean foundSelectedDoctor = false;
        Doctor selectedDoctorObject = null;
        for (Doctor doctor : doctors) {
            if (doctor.getId() == doctorId) {
                foundSelectedDoctor = true;
                selectedDoctorObject = doctor;
                break;
            }
        }
        if (foundSelectedDoctor) {
            System.out.println("Enter the hour of the appointment you want to cancel: ");
            input.nextLine();
            String appointmentHour = input.nextLine();
            boolean foundAppointmentHour = false;

            Iterator<AppointmentHours> iterator = selectedDoctorObject.getBookedHours().iterator();
            while (iterator.hasNext()) {
                AppointmentHours appointment = iterator.next();
                if (appointment.getHour().equals(appointmentHour)) {
                    selectedDoctorObject.getAvailableHours().add(appointment);
                    iterator.remove();
                    foundAppointmentHour = true;
                    break;
                }
            }

            if (foundAppointmentHour) {
                System.out.println("Your appointment at " + appointmentHour + " with Dr. " + selectedDoctorObject.getName() + " has been successfully cancelled.");
            } else {
                System.out.println("The selected hour is not a booked appointment. Please enter a valid hour.");
                cancelAppointment();
            }
        } else {
            System.out.println("The doctor with the selected id was not found. Please enter a valid id.");
            cancelAppointment();
        }
        return false;
    }

    public static void printBookedAppointments() {
        List<Doctor> doctors = Initialize.getDoctorList();
        System.out.println("Booked appointments: ");
        for (Doctor doctor : doctors) {
            for (AppointmentHours availableHour : doctor.getAvailableHours()) {
                if (!availableHour.isAvailable()) {
                    System.out.println("Doctor: " + doctor.getName() + " Time: " + availableHour.getHour());
                }
            }
        }
    }

    public static void viewDoctorSchedules() {
        List<Doctor> doctors = Initialize.getDoctorList();
        System.out.println("Doctor Schedules: ");
        for (Doctor doctor : doctors) {
            System.out.println("Doctor: " + doctor.getName());
            System.out.println("Available Hours: ");
            for (AppointmentHours availableHour : doctor.getAvailableHours()) {
                System.out.println(availableHour.getHour() + " - Available: " + availableHour.isAvailable());
            }
            System.out.println("-----------------------------");
        }
    }

    public static void sortDoctorsByAvailability() {
        List<Doctor> doctors = Initialize.getDoctorList();
        System.out.println("Doctors Sorted By Availability: ");
        Collections.sort(doctors, new Comparator<Doctor>() {
            @Override
            public int compare(Doctor o1, Doctor o2) {
                return Integer.compare(o1.getAvailabilityCount(), o2.getAvailabilityCount());
            }
        });
        for (Doctor doctor : doctors) {
            System.out.println("Doctor: " + doctor.getName() + " - Availability Count: " + doctor.getAvailabilityCount());
        }
    }

}

