import java.util.*;

// Classes
class Patient {
    String id;
    String name;
    int age;
    String address;
    String phone;
    String disease;
    String doctorAssigned; // Store the doctor assigned to the patient

    public Patient(String id, String name, int age, String address, String phone, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.disease = disease;
        this.doctorAssigned = null; // Initially no doctor assigned
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Address: " + address + ", Phone: " + phone +
               ", Disease: " + disease + ", Doctor: " + doctorAssigned;
    }
}

class Doctor {
    String id;
    String name;
    String specialization;
    String phone;

    public Doctor(String id, String name, String specialization, String phone) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Specialization: " + specialization + ", Phone: " + phone;
    }
}

class Appointment {
    String patientId;
    String doctorId;
    Date date;
    String time;

    public Appointment(String patientId, String doctorId, Date date, String time) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId + ", Doctor ID: " + doctorId + ", Date: " + date + ", Time: " + time;
    }
}


class HospitalManagementSystem {
    private Map<String, Patient> patients;
    private Map<String, Doctor> doctors;
    private List<Appointment> appointments;
    private int nextPatientId = 1;
    private int nextDoctorId = 1;

    public HospitalManagementSystem() {
        patients = new HashMap<>();
        doctors = new HashMap<>();
        appointments = new ArrayList<>();
    }

    public void addPatient(String name, int age, String address, String phone, String disease) {
        String patientId = String.valueOf(nextPatientId++);
        Patient patient = new Patient(patientId, name, age, address, phone, disease);
        patients.put(patientId, patient);
        System.out.println("Patient added with ID: " + patientId);
    }

    public void addDoctor(String name, String specialization, String phone) {
        String doctorId = String.valueOf(nextDoctorId++);
        Doctor doctor = new Doctor(doctorId, name, specialization, phone);
        doctors.put(doctorId, doctor);
        System.out.println("Doctor added with ID: " + doctorId);
    }

    public Patient getPatient(String patientId) {
        return patients.get(patientId);
    }

    public Doctor getDoctor(String doctorId) {
        return doctors.get(doctorId);
    }

    public void assignDoctorToPatient(String patientId, String doctorId) {
        Patient patient = patients.get(patientId);
        Doctor doctor = doctors.get(doctorId);

        if (patient != null && doctor != null) {
            patient.doctorAssigned = doctorId; // Store the Doctor ID
            System.out.println("Doctor " + doctor.name + " assigned to patient " + patient.name);
        } else {
            System.out.println("Invalid patient or doctor ID.");
        }
    }

    public void scheduleAppointment(String patientId, String doctorId, Date date, String time) {
        if (patients.containsKey(patientId) && doctors.containsKey(doctorId)) {
            Appointment appointment = new Appointment(patientId, doctorId, date, time);
            appointments.add(appointment);
            System.out.println("Appointment scheduled: " + appointment);
        } else {
            System.out.println("Invalid patient or doctor ID.");
        }
    }

    public void listPatients() {
        System.out.println("\nPatients:");
        for (Patient patient : patients.values()) {
            System.out.println(patient);
        }
    }

    public void listDoctors() {
        System.out.println("\nDoctors:");
        for (Doctor doctor : doctors.values()) {
            System.out.println(doctor);
        }
    }

    public void listAppointments() {
        System.out.println("\nAppointments:");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    public static void main(String[] args) {
        HospitalManagementSystem system = new HospitalManagementSystem();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // For date input

        while (true) {
            System.out.println("\nHospital Management System Menu:");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Assign Doctor to Patient");
            System.out.println("4. Schedule Appointment");
            System.out.println("5. List Patients");
            System.out.println("6. List Doctors");
            System.out.println("7. List Appointments");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter patient name: ");
                        String patientName = scanner.nextLine();
                        System.out.print("Enter patient age: ");
                        int patientAge = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter patient address: ");
                        String patientAddress = scanner.nextLine();
                        System.out.print("Enter patient phone: ");
                        String patientPhone = scanner.nextLine();
                        System.out.print("Enter patient disease: ");
                        String patientDisease = scanner.nextLine();
                        system.addPatient(patientName, patientAge, patientAddress, patientPhone, patientDisease);
                        break;
                    case 2:
                        System.out.print("Enter doctor name: ");
                        String doctorName = scanner.nextLine();
                        System.out.print("Enter doctor specialization: ");
                        String doctorSpecialization = scanner.nextLine();
                        System.out.print("Enter doctor phone: ");
                        String doctorPhone = scanner.nextLine();
                        system.addDoctor(doctorName, doctorSpecialization, doctorPhone);
                        break;
                    case 3:
                        System.out.print("Enter patient ID: ");
                        String patientId = scanner.nextLine();
                        System.out.print("Enter doctor ID: ");
                        String doctorId = scanner.nextLine();
                        system.assignDoctorToPatient(patientId, doctorId);
                        break;
                    case 4:
                        System.out.print("Enter patient ID: ");
                        String appPatientId = scanner.nextLine();
                        System.out.print("Enter doctor ID: ");
                        String appDoctorId = scanner.nextLine();
                        System.out.print("Enter date (yyyy-MM-dd): ");
                        Date date = dateFormat.parse(scanner.nextLine());
                        System.out.print("Enter time (HH:mm): ");
                        String time = scanner.nextLine();
                        system.scheduleAppointment(appPatientId, appDoctorId, date, time);
                        break;
                    case 5:
                        system.listPatients();
                        break;
                    case 6:
                        system.listDoctors();
                        break;
                    case 7:
                        system.listAppointments();
                        break;
                    case 8:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter valid data.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}
