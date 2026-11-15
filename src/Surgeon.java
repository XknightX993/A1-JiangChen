/**
 * A surgeon with a particular surgical area.
 */
public class Surgeon extends HealthProfessional {
    private final String surgicalArea;

    /**
     * Creates a surgeon.
     *
     * @param id numeric-only identifier
     * @param name surgeon's name
     * @param workingDays days the surgeon works
     * @param surgicalArea the surgeon's area of surgery
     */
    public Surgeon(String id, String name, String[] workingDays, String surgicalArea) {
        super(id, name, workingDays);
        if (surgicalArea == null || surgicalArea.trim().isEmpty()) {
            throw new IllegalArgumentException("Surgical area cannot be empty.");
        }
        this.surgicalArea = surgicalArea.trim();
    }

    /** @return the surgeon's area of surgery */
    public String getSurgicalArea() {
        return surgicalArea;
    }

    @Override
    public String getProfessionalType() {
        return "Surgeon";
    }

    @Override
    public String toString() {
        return super.toString() + ", surgical area=" + surgicalArea;
    }
}
