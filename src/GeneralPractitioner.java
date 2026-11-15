/**
 * A general practitioner with a billing preference.
 */
public class GeneralPractitioner extends HealthProfessional {
    private final boolean bulkBilling;

    /**
     * Creates a general practitioner.
     *
     * @param id numeric-only identifier
     * @param name practitioner's name
     * @param workingDays days the practitioner works
     * @param bulkBilling whether the practitioner offers bulk billing
     */
    public GeneralPractitioner(String id, String name, String[] workingDays,
            boolean bulkBilling) {
        super(id, name, workingDays);
        this.bulkBilling = bulkBilling;
    }

    /** @return whether this practitioner offers bulk billing */
    public boolean offersBulkBilling() {
        return bulkBilling;
    }

    /**
     * Demonstrates a general-practice action.
     *
     * @param medication the medication name for the demonstration
     * @return a message describing the action
     */
    public String prescribeMedication(String medication) {
        if (medication == null || medication.trim().isEmpty()) {
            throw new IllegalArgumentException("Medication cannot be empty.");
        }
        return getName() + " prescribed " + medication.trim() + ".";
    }

    @Override
    public String getProfessionalType() {
        return "General Practitioner";
    }

    @Override
    public String toString() {
        return super.toString() + ", bulk billing=" + bulkBilling;
    }
}
