
package LibraryManagement;

/*
 * Description:
 * The LibraryStaff class represents a staff member working at the
 * Maple Leaf Public Library. It extends the Person class and inherits
 * basic personal information such as the person's ID, name, and phone number.
 *
 * It stores staff-specific information, including staff ID, department,
 * and monthly salary. It validates staff information and tracks the total
 * number of successfully created staff objects.
 *
 * This class is abstract because different types of library staff may
 * calculate their monthly compensation differently. Subclasses such as
 * Librarian, SeniorLibrarian, and LibraryAssistant must implement the
 * calculateMonthlyCompensation() method.
 */

public abstract class LibraryStaff extends Person {

    // Stores the staff member's unique ID.
    private final int staffId;

    // Stores the department where the staff member works.
    private final String department;

    // Stores the staff member's base monthly salary.
    private final double monthlySalary;

    // Tracks the total number of successfully created LibraryStaff objects.
    private static int staffCount = 0;

    // Constructor initializes the staff member's personal and employment details.
    public LibraryStaff(int personId, String name, String phoneNumber, int staffId, String department, double monthlySalary) {

        // Validates staff-specific information before calling the Person constructor.
        super(validateStaff(personId, staffId, department, monthlySalary), name, phoneNumber);

        // Assigns the validated employment information to the object's fields.
        this.staffId = staffId;
        this.department = department;
        this.monthlySalary = monthlySalary;

        // Increases the staff counter after successful initialization.
        staffCount++;
    }

    // Validates the person's ID, staff ID, department, and monthly salary.
    private static int validateStaff(int personId, int staffId, String department, double monthlySalary) {

        // IDs must be positive, the department cannot be empty, and the salary
        // must be a valid finite number that is not negative.
        if (personId <= 0 || staffId <= 0 || department == null || department.trim().isEmpty() || !Double.isFinite(monthlySalary) || monthlySalary < 0) {
            throw new IllegalArgumentException("Invalid staff information.");
        }

        // Returns the validated person ID to the parent constructor.
        return personId;
    }

    // Returns the total number of successfully created library staff objects.
    public static int getStaffCount() {
        return staffCount;
    }

    // Returns the staff member's ID.
    public int getStaffId() {
        return staffId;
    }

    // Returns the staff member's department.
    public String getDepartment() {
        return department;
    }

    // Returns the staff member's base monthly salary.
    public double getMonthlySalary() {
        return monthlySalary;
    }

    // Abstract method that subclasses must implement to calculate compensation.
    public abstract double calculateMonthlyCompensation();

    // Displays the staff member's personal and employment details.
    public void displayStaffDetails() {

        // Reuses the method inherited from Person to display basic details.
        displayBasicDetails();

        // Displays staff-specific information.
        System.out.println("Staff ID: " + staffId);
        System.out.println("Department: " + department);

        // Displays the base monthly salary formatted to two decimal places.
        System.out.printf("Monthly Salary: $%.2f%n", monthlySalary);
    }

    // Overrides the Person method to describe the person's general role.
    @Override
    public String getRoleDescription() {
        return "Library Staff";
    }
}
