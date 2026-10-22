
package LibraryManagement;

/*
 * Description:
 * The Librarian class represents a librarian working in the library.
 * It extends LibraryStaff and stores the librarian's daily issue limit
 * and the number of books processed.
 *
 * It calculates late fees, calculates monthly compensation, and displays
 * staff details. It also demonstrates inheritance, method overloading,
 * method overriding, validation, and the use of super.
 */

public class Librarian extends LibraryStaff {

    // Final fields store the librarian's issue limit and processed books.
    private final int dailyIssueLimit;
    private final int booksProcessed;

    // Constructor initializes librarian details and validates the information.
    public Librarian(int personId, String name, String phoneNumber, int staffId, String department, double monthlySalary, int dailyIssueLimit, int booksProcessed) {

        // Calls the parent constructor after validating librarian-specific values.
        super(validateLibrarian(personId, dailyIssueLimit, booksProcessed), name, phoneNumber, staffId, department, monthlySalary);

        this.dailyIssueLimit = dailyIssueLimit;
        this.booksProcessed = booksProcessed;
    }

    // Validates the librarian's ID, daily issue limit, and processed book count.
    private static int validateLibrarian(int personId, int dailyIssueLimit, int booksProcessed) {

        // The ID and issue limit must be positive; processed books cannot be negative.
        if (personId <= 0 || dailyIssueLimit <= 0 || booksProcessed < 0) {
            throw new IllegalArgumentException("Invalid librarian information.");
        }

        // Returns the validated person ID to the parent constructor.
        return personId;
    }

    // Returns the total number of books processed by this librarian.
    public int getBooksProcessed() {
        return booksProcessed;
    }

    // Calculates the late fee using the default rate of $1.00 per day.
    public double calculateLateFee(int daysOverdue) {
        return calculateLateFee(daysOverdue, 1.00);
    }

    // Overloaded method calculates the late fee using a supplied daily rate.
    public double calculateLateFee(int daysOverdue, double dailyRate) {

        // Rejects a negative number of overdue days.
        if (daysOverdue < 0) {
            throw new IllegalArgumentException("Overdue days cannot be negative.");
        }

        // Rejects zero, negative, or non-finite daily rates.
        if (!Double.isFinite(dailyRate) || dailyRate <= 0) {
            throw new IllegalArgumentException("Daily rate must be greater than zero.");
        }

        // Calculates and returns the total late fee.
        return daysOverdue * dailyRate;
    }

    // Overrides the parent method to include the book-processing allowance.
    @Override
    public double calculateMonthlyCompensation() {
        return getMonthlySalary() + (booksProcessed * 2.00);
    }

    // Overrides the parent method to return this employee's role.
    @Override
    public String getRoleDescription() {
        return "Librarian";
    }

    // Overrides the parent method to display additional librarian details.
    @Override
    public void displayStaffDetails() {

        // Reuses the staff details displayed by the parent class.
        super.displayStaffDetails();

        // Displays the librarian's daily book issue limit.
        System.out.println("Daily Issue Limit: " + dailyIssueLimit);

        // Displays the total number of books processed.
        System.out.println("Books Processed: " + booksProcessed);

        // Displays the monthly compensation formatted to two decimal places.
        System.out.printf("Monthly Compensation: $%.2f%n", calculateMonthlyCompensation());
    }
}
