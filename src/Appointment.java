import java.time.LocalTime;

/**
 * A booking for one patient and one health professional on the current day.
 */
public class Appointment implements Comparable<Appointment> {
    private final String patientName;
    private final String patientPhone;
    private final LocalTime appointmentTime;
    private final HealthProfessional professional;

    /**
     * Creates an appointment after checking that every required detail exists.
     *
     * @param patientName patient's name
     * @param patientPhone patient's mobile phone number
     * @param appointmentTime time for the appointment
     * @param professional chosen health professional
     */
    public Appointment(String patientName, String patientPhone,
            LocalTime appointmentTime, HealthProfessional professional) {
        if (patientName == null || patientName.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient name cannot be empty.");
        }
        if (patientPhone == null || patientPhone.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient phone cannot be empty.");
        }
        if (appointmentTime == null) {
            throw new IllegalArgumentException("Appointment time is required.");
        }
        if (professional == null) {
            throw new IllegalArgumentException("A health professional is required.");
        }

        this.patientName = patientName.trim();
        this.patientPhone = patientPhone.trim();
        this.appointmentTime = appointmentTime;
        this.professional = professional;
    }

    /** @return the patient's name */
    public String getPatientName() {
        return patientName;
    }

    /** @return the patient's phone number */
    public String getPatientPhone() {
        return patientPhone;
    }

    /** @return the appointment time */
    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    /** @return the health professional for this appointment */
    public HealthProfessional getProfessional() {
        return professional;
    }

    @Override
    public int compareTo(Appointment other) {
        return appointmentTime.compareTo(other.appointmentTime);
    }

    /**
     * Returns the appointment and the selected professional's details.
     *
     * @return readable appointment details
     */
    @Override
    public String toString() {
        return "Appointment [patient=" + patientName + ", phone=" + patientPhone
                + ", time=" + appointmentTime + ", professional=" + professional + "]";
    }
}
