import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Runs the scripted demonstration for Assessment 1.
 */
public class AssignmentOne {
    /**
     * Program entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Health appointment system - Assessment 1");

        GeneralPractitioner gp = new GeneralPractitioner("0012", "Alex Tan",
                new String[] {"Monday", "Wednesday"}, true);
        GeneralPractitioner secondGp = new GeneralPractitioner("0013", "Robin Wu",
                new String[] {"Tuesday", "Thursday"}, false);
        GeneralPractitioner thirdGp = new GeneralPractitioner("0014", "Casey Lim",
                new String[] {"Monday", "Friday"}, true);
        Specialist specialist = new Specialist("0203", "Priya Shah",
                new String[] {"Tuesday", "Thursday"}, "Cardiology");
        Nurse nurse = new Nurse("0304", "Morgan Lee",
                new String[] {"Monday", "Friday"}, "Outpatient");
        Surgeon surgeon = new Surgeon("0405", "Sam Chen",
                new String[] {"Wednesday", "Friday"}, "Orthopaedics");

        HealthProfessional[] professionals = {gp, secondGp, thirdGp, specialist, nurse, surgeon};
        for (HealthProfessional professional : professionals) {
            System.out.println(professional);
        }
        System.out.println(gp.prescribeMedication("example medication"));
        System.out.println(nurse.takeBloodSample());

        Appointment firstAppointment = new Appointment("Taylor Morgan", "0412345678",
                LocalTime.of(9, 0), gp);
        System.out.println(firstAppointment);

        AppointmentManager manager = new AppointmentManager(
                LocalTime.of(8, 0), LocalTime.of(16, 0), 30);
        System.out.println(manager.addAppointment(firstAppointment));
        System.out.println(manager.addAppointment(new Appointment("Jordan Lee", "0498765432",
                LocalTime.of(9, 0), gp)));
        System.out.println(manager.addAppointment(new Appointment("Casey Wong", "0400111222",
                LocalTime.of(9, 30), specialist)));
        System.out.println(manager.addAppointment(new Appointment("Riley Park", "0411222333",
                LocalTime.of(10, 0), nurse)));
        System.out.println(manager.addAppointment(new Appointment("Jamie Fox", "0422333444",
                LocalTime.of(10, 30), surgeon)));
        System.out.println(manager.addAppointment(new Appointment("Avery Kim", "0400999000",
                LocalTime.of(9, 15), nurse)));

        System.out.println();
        System.out.println("All appointments (sorted):");
        for (Appointment appointment : manager.getAllAppointments()) {
            System.out.println(appointment);
        }

        System.out.println();
        System.out.println("Appointments for professional 0203:");
        ArrayList<Appointment> specialistBookings = manager.findByProfessionalId("0203");
        for (Appointment appointment : specialistBookings) {
            System.out.println(appointment);
        }

        System.out.println();
        System.out.println("Appointments for phone 0412345678:");
        ArrayList<Appointment> patientBookings = manager.findByPatientPhone("0412345678");
        for (Appointment appointment : patientBookings) {
            System.out.println(appointment);
        }

        System.out.println(manager.cancelByProfessionalAndTime("0203", LocalTime.of(9, 30)));
        System.out.println(manager.cancelByPatientPhone("0412345678"));
        System.out.println(manager.cancelByPatientPhone("0400000000"));

        System.out.println();
        System.out.println("Appointments after cancellations:");
        for (Appointment appointment : manager.getAllAppointments()) {
            System.out.println(appointment);
        }
    }
}


