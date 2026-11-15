import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Stores and manages the clinic's appointments for the current day.
 */
public class AppointmentManager {
    private final ArrayList<Appointment> appointments;
    private final LocalTime firstTimeSlot;
    private final LocalTime lastTimeSlot;
    private final int minutesBetweenSlots;

    /**
     * Creates a manager with a configurable daily schedule.
     *
     * @param firstTimeSlot first appointment time
     * @param lastTimeSlot last appointment time
     * @param minutesBetweenSlots interval between appointment start times
     */
    public AppointmentManager(LocalTime firstTimeSlot, LocalTime lastTimeSlot,
            int minutesBetweenSlots) {
        if (firstTimeSlot == null || lastTimeSlot == null
                || !lastTimeSlot.isAfter(firstTimeSlot) || minutesBetweenSlots <= 0) {
            throw new IllegalArgumentException("The appointment schedule is invalid.");
        }
        this.appointments = new ArrayList<Appointment>();
        this.firstTimeSlot = firstTimeSlot;
        this.lastTimeSlot = lastTimeSlot;
        this.minutesBetweenSlots = minutesBetweenSlots;
    }

    /**
     * Adds an appointment if its time is scheduled and the professional is free.
     *
     * @param appointment appointment to add
     * @return a message describing success or failure
     */
    public String addAppointment(Appointment appointment) {
        if (appointment == null) {
            return "Booking failed: appointment details are missing.";
        }
        if (!isScheduledTime(appointment.getAppointmentTime())) {
            return "Booking failed: " + appointment.getAppointmentTime()
                    + " is not an available time slot.";
        }
        for (Appointment existing : appointments) {
            if (existing.getProfessional().equals(appointment.getProfessional())
                    && existing.getAppointmentTime().equals(appointment.getAppointmentTime())) {
                return "Booking failed: this professional is already booked at "
                        + appointment.getAppointmentTime() + ".";
            }
        }
        appointments.add(appointment);
        return "Booking added for " + appointment.getPatientName() + " at "
                + appointment.getAppointmentTime() + ".";
    }

    /** @return all appointments sorted from earliest to latest */
    public ArrayList<Appointment> getAllAppointments() {
        return sortedCopy(appointments);
    }

    /**
     * Finds appointments for a professional ID.
     *
     * @param professionalId ID to search for
     * @return matching appointments sorted by time
     */
    public ArrayList<Appointment> findByProfessionalId(String professionalId) {
        ArrayList<Appointment> matches = new ArrayList<Appointment>();
        if (professionalId == null) {
            return matches;
        }
        for (Appointment appointment : appointments) {
            if (appointment.getProfessional().getId().equals(professionalId)) {
                matches.add(appointment);
            }
        }
        return sortedCopy(matches);
    }

    /**
     * Finds appointments for a patient phone number.
     *
     * @param patientPhone phone number to search for
     * @return matching appointments sorted by time
     */
    public ArrayList<Appointment> findByPatientPhone(String patientPhone) {
        ArrayList<Appointment> matches = new ArrayList<Appointment>();
        if (patientPhone == null) {
            return matches;
        }
        for (Appointment appointment : appointments) {
            if (appointment.getPatientPhone().equals(patientPhone)) {
                matches.add(appointment);
            }
        }
        return sortedCopy(matches);
    }

    /**
     * Cancels a professional's appointment at a specific time.
     *
     * @param professionalId ID of the professional
     * @param appointmentTime time of the booking
     * @return a message describing success or failure
     */
    public String cancelByProfessionalAndTime(String professionalId,
            LocalTime appointmentTime) {
        if (professionalId == null || appointmentTime == null) {
            return "Cancellation failed: professional ID and appointment time are required.";
        }
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            if (appointment.getProfessional().getId().equals(professionalId)
                    && appointment.getAppointmentTime().equals(appointmentTime)) {
                appointments.remove(i);
                return "Appointment cancelled.";
            }
        }
        return "Cancellation failed: no matching appointment was found.";
    }

    /**
     * Cancels a patient's appointment if the phone number identifies exactly one.
     *
     * @param patientPhone patient's phone number
     * @return a message describing success or failure
     */
    public String cancelByPatientPhone(String patientPhone) {
        if (patientPhone == null || patientPhone.trim().isEmpty()) {
            return "Cancellation failed: a patient phone number is required.";
        }
        int matchingIndex = -1;
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getPatientPhone().equals(patientPhone)) {
                if (matchingIndex >= 0) {
                    return "Cancellation failed: this phone number matches multiple appointments.";
                }
                matchingIndex = i;
            }
        }
        if (matchingIndex < 0) {
            return "Cancellation failed: no appointment was found for this phone number.";
        }
        appointments.remove(matchingIndex);
        return "Appointment cancelled.";
    }

    private boolean isScheduledTime(LocalTime time) {
        LocalTime slot = firstTimeSlot;
        while (!slot.isAfter(lastTimeSlot)) {
            if (slot.equals(time)) {
                return true;
            }
            slot = slot.plusMinutes(minutesBetweenSlots);
        }
        return false;
    }

    private ArrayList<Appointment> sortedCopy(ArrayList<Appointment> source) {
        ArrayList<Appointment> copy = new ArrayList<Appointment>(source);
        Collections.sort(copy);
        return copy;
    }
}
