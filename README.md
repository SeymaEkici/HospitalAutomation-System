Appointment Management System
=============================

Overview
--------

This project is a Java-based appointment management system that allows users to book, view, and cancel doctor appointments. It also includes an admin interface for managing doctor schedules.

Features
--------

-   List all available doctors and their units
-   Book an appointment with a doctor
-   Cancel an existing appointment
-   View all booked appointments
-   Admin access with additional functionalities:
    -   View doctor schedules
    -   Sort doctors by availability

Technologies Used
-----------------

-   Java
-   Collections Framework (List, Map, etc.)
-   Scanner for user input
-   Object-Oriented Programming (OOP) principles

Project Structure
-----------------

```
├── src
│   ├── Main.java           # Entry point of the application
│   ├── AppointmentHours.java  # Enum for available appointment hours
│   ├── Doctor.java         # Doctor class with availability details
│   ├── Initialize.java     # Initialization of medical units and doctors
│   ├── MedicalUnit.java    # Represents different medical units

```

How to Run
----------

1.  Clone the repository:

    ```
    git clone <[repository_url](https://github.com/SeymaEkici/HospitalAutomation-System.git)>

    ```

2.  Open the project in an IDE (such as IntelliJ IDEA or Eclipse).
3.  Run the `Main.java` file.
4.  Follow the on-screen instructions to interact with the system.

Usage
-----

### 1\. User Mode

-   Choose from the menu options:
    -   `1` - View all doctors
    -   `2` - Book an appointment
    -   `3` - Cancel an appointment
    -   `4` - View all booked appointments
    -   `5` - Exit

### 2\. Admin Mode

-   Login with username `admin` and password `admin`.
-   Choose from additional options:
    -   `1` - View doctor schedules
    -   `2` - Sort doctors by availability
    -   `3` - Exit

Future Improvements
-------------------

-   Implement a database for storing appointments persistently.
-   Add GUI for better user experience.
-   Improve error handling and validation.

Contributors
------------

-   **Your Name** (Replace with actual contributors)

License
-------

This project is licensed under the MIT License.
