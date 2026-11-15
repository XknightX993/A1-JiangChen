/**
 * A nurse who works in a particular department.
 */
public class Nurse extends HealthProfessional {
    private final String department;

    /**
     * Creates a nurse.
     *
     * @param id numeric-only identifier
     * @param name nurse's name
     * @param workingDays days the nurse works
     * @param department the nurse's department
     */
    public Nurse(String id, String name, String[] workingDays, String department) {
        super(id, name, workingDays);
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be empty.");
        }
        this.department = department.trim();
    }

    /** @return the nurse's department */
    public String getDepartment() {
        return department;
    }

    /**
     * Demonstrates a nursing task.
     *
     * @return a message describing the action
     */
    public String takeBloodSample() {
        return getName() + " is taking a blood sample.";
    }

    @Override
    public String getProfessionalType() {
        return "Nurse";
    }

    @Override
    public String toString() {
        return super.toString() + ", department=" + department;
    }
}
