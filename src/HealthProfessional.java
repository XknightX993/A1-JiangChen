/**
 * Common information and behaviour for health professionals.
 * Specific professional types extend this class.
 */
public abstract class HealthProfessional {
    private final String id;
    private final String name;
    private final String[] workingDays;

    /**
     * Creates a health professional after validating the shared details.
     *
     * @param id numeric-only identifier
     * @param name professional's name
     * @param workingDays days of the week when the professional works
     */
    protected HealthProfessional(String id, String name, String[] workingDays) {
        if (id == null || id.isEmpty() || !containsDigitsOnly(id)) {
            throw new IllegalArgumentException("ID must contain numbers only.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (workingDays == null || workingDays.length == 0) {
            throw new IllegalArgumentException("At least one working day is required.");
        }

        for (int i = 0; i < workingDays.length; i++) {
            if (!isWeekday(workingDays[i])) {
                throw new IllegalArgumentException("Each working day must be a valid weekday.");
            }
            for (int j = i + 1; j < workingDays.length; j++) {
                if (workingDays[i].equalsIgnoreCase(workingDays[j])) {
                    throw new IllegalArgumentException("Working days cannot contain duplicates.");
                }
            }
        }

        this.id = id;
        this.name = name.trim();
        this.workingDays = workingDays.clone();
    }

    private boolean containsDigitsOnly(String value) {
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) < '0' || value.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    private boolean isWeekday(String day) {
        if (day == null) {
            return false;
        }
        return day.equalsIgnoreCase("Monday")
                || day.equalsIgnoreCase("Tuesday")
                || day.equalsIgnoreCase("Wednesday")
                || day.equalsIgnoreCase("Thursday")
                || day.equalsIgnoreCase("Friday")
                || day.equalsIgnoreCase("Saturday")
                || day.equalsIgnoreCase("Sunday");
    }

    /** @return the professional's numeric identifier */
    public String getId() {
        return id;
    }

    /** @return the professional's name */
    public String getName() {
        return name;
    }

    /** @return a copy of the professional's working days */
    public String[] getWorkingDays() {
        return workingDays.clone();
    }

    /** @return a label describing this specific professional type */
    public abstract String getProfessionalType();

    /**
     * Professionals are considered equal when their IDs match.
     *
     * @param other object to compare
     * @return true when the other professional has the same ID
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HealthProfessional)) {
            return false;
        }
        HealthProfessional professional = (HealthProfessional) other;
        return id.equals(professional.id);
    }

    /** @return a hash code based on the professional's ID */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * Returns the shared details and the specific professional type.
     *
     * @return readable professional details
     */
    @Override
    public String toString() {
        String days = "";
        for (int i = 0; i < workingDays.length; i++) {
            if (i > 0) {
                days += ", ";
            }
            days += workingDays[i];
        }
        return getProfessionalType() + " [ID=" + id + ", name=" + name
                + ", working days=" + days + "]";
    }
}
