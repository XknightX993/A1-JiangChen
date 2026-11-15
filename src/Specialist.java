/**
 * A medical specialist with a particular area of expertise.
 */
public class Specialist extends HealthProfessional {
    private final String specialty;

    /**
     * Creates a specialist.
     *
     * @param id numeric-only identifier
     * @param name specialist's name
     * @param workingDays days the specialist works
     * @param specialty area of medical expertise
     */
    public Specialist(String id, String name, String[] workingDays, String specialty) {
        super(id, name, workingDays);
        if (specialty == null || specialty.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialty cannot be empty.");
        }
        this.specialty = specialty.trim();
    }

    /** @return the specialist's area of expertise */
    public String getSpecialty() {
        return specialty;
    }

    @Override
    public String getProfessionalType() {
        return "Specialist";
    }

    @Override
    public String toString() {
        return super.toString() + ", specialty=" + specialty;
    }
}
