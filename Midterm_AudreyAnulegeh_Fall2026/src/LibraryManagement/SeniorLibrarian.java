
package LibraryManagement;

/*
 * Description:
 * The SeniorLibrarian class represents an experienced librarian who has
 * additional responsibilities or specialized knowledge in the library.
 * It extends the Librarian class and inherits its personal information,
 * staff details, daily issue limit, and number of books processed.
 *
 * It adds a specialization and a seniority allowance to the librarian's
 * information. It also overrides methods to calculate total monthly
 * compensation, describe the senior librarian's role, and display staff
 * details.
 *
 * This class demonstrates multilevel inheritance because SeniorLibrarian
 * extends Librarian, which extends LibraryStaff, which extends Person.
 */

public class SeniorLibrarian extends Librarian {

    // Stores the senior librarian's area of expertise.
    private final String specialization;

    // Stores the additional monthly allowance for seniority.
    private final double seniorityAllowance;

    // Constructor initializes inherited librarian details and senior-specific information.
    public SeniorLibrarian(int personId, String name, String phoneNumber, int staffId, String department, double monthlySalary, int dailyIssueLimit, int booksProcessed, String specialization, double seniorityAllowance) {

        // Validates senior librarian information before calling the Librarian constructor.
        super(validateSenior(personId, specialization, seniorityAllowance, dailyIssueLimit, booksProcessed), name, phoneNumber, staffId, department, monthlySalary, dailyIssueLimit, booksProcessed);

        // Assigns the validated specialization and seniority allowance.
        this.specialization = specialization;
        this.seniorityAllowance = seniorityAllowance;
    }

    // Validates the person's ID, specialization, allowance, issue limit, and books processed.
    private static int validateSenior(int personId, String specialization, double seniorityAllowance, int dailyIssueLimit, int booksProcessed) {

        // The ID and issue limit must be positive, the specialization cannot be empty,
        // the allowance must be finite and non-negative, and books processed cannot be negative.
        if (personId <= 0 || specialization == null || specialization.trim().isEmpty() || !Double.isFinite(seniorityAllowance) || seniorityAllowance < 0 || dailyIssueLimit <= 0 || booksProcessed < 0) {
            throw new IllegalArgumentException("Invalid senior librarian information.");
        }

        // Returns the validated person ID to the parent constructor.
        return personId;
    }

    // Overrides the Librarian method to include the seniority allowance in compensation.
    @Override
    public double calculateMonthlyCompensation() {

        // Calculates the librarian's normal compensation and adds the seniority allowance.
        return super.calculateMonthlyCompensation() + seniorityAllowance;
    }

    // Overrides the inherited method to identify the employee's specific role.
    @Override
    public String getRoleDescription() {
        return "Senior Librarian";
    }

    // Overrides the inherited method to display the senior librarian's complete details.
    @Override
    public void displayStaffDetails() {

        // Displays the staff and librarian details provided by the parent classes.
        super.displayStaffDetails();

        // Displays senior librarian-specific information.
        System.out.println("Specialization: " + specialization);
        System.out.printf("Seniority Allowance: $%.2f%n", seniorityAllowance);

        // Displays total monthly compensation, including the seniority allowance.
        System.out.printf("Total Compensation: $%.2f%n", calculateMonthlyCompensation());
    }
}
