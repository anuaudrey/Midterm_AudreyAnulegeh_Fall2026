
package LibraryManagement;

/*
 * Description:
 * The LibraryAssistant class represents an assistant working at the
 * Maple Leaf Public Library. It extends the LibraryStaff class and inherits
 * personal information, staff details, department, and monthly salary.
 *
 * It adds information about the assistant's shift type and shift allowance.
 * It validates the assistant's information and calculates total monthly
 * compensation by adding the shift allowance to the base monthly salary.
 *
 * This class demonstrates inheritance, method overriding, encapsulation,
 * constructor chaining, and validation.
 */

public class LibraryAssistant extends LibraryStaff {

    // Stores the assistant's work shift, such as Morning, Evening, or Night.
    private final String shiftType;

    // Stores the additional allowance paid for the assistant's shift.
    private final double shiftAllowance;

    // Constructor initializes inherited staff details and assistant-specific information.
    public LibraryAssistant(int personId, String name, String phoneNumber, int staffId, String department, double monthlySalary, String shiftType, double shiftAllowance) {

        // Validates assistant-specific information before calling the LibraryStaff constructor.
        super(validateAssistant(personId, shiftType, shiftAllowance), name, phoneNumber, staffId, department, monthlySalary);

        // Assigns the validated shift type and shift allowance.
        this.shiftType = shiftType;
        this.shiftAllowance = shiftAllowance;
    }

    // Validates the person's ID, shift type, and shift allowance.
    private static int validateAssistant(int personId, String shiftType, double shiftAllowance) {

        // The ID must be positive, the shift type cannot be empty,
        // and the allowance must be finite and non-negative.
        if (personId <= 0 || shiftType == null || shiftType.trim().isEmpty() || !Double.isFinite(shiftAllowance) || shiftAllowance < 0) {
            throw new IllegalArgumentException("Invalid library assistant information.");
        }

        // Returns the validated person ID to the parent constructor.
        return personId;
    }

    // Overrides the abstract parent method to calculate total monthly compensation.
    @Override
    public double calculateMonthlyCompensation() {

        // Adds the shift allowance to the assistant's base monthly salary.
        return getMonthlySalary() + shiftAllowance;
    }

    // Overrides the parent method to identify the employee's specific role.
    @Override
    public String getRoleDescription() {
        return "Library Assistant";
    }

    // Overrides the parent method to display the assistant's complete staff details.
    @Override
    public void displayStaffDetails() {

        // Reuses the parent class method to display personal and general staff details.
        super.displayStaffDetails();

        // Displays information specific to the library assistant.
        System.out.println("Shift Type: " + shiftType);
        System.out.printf("Shift Allowance: $%.2f%n", shiftAllowance);

        // Displays total monthly compensation, including the shift allowance.
        System.out.printf("Total Compensation: $%.2f%n", calculateMonthlyCompensation());
    }
}
