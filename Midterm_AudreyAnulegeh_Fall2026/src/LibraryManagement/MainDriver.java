package LibraryManagement;

public class MainDriver {

    public static void main(String[] args) {

        System.out.println("===== MAPLE LEAF PUBLIC LIBRARY =====");

        Member member1 = Member.registerMember(1, "Alice Brown", "555-0101", 1001, 25);
        Member member2 = Member.registerMember(2, "James Wilson", "555-0102", 1002, 32, "Premium");

        System.out.println("\n===== MEMBER DETAILS =====");
        member1.displayMemberDetails();
        System.out.println();
        member2.displayMemberDetails();

        try {
            Member.registerMember(6, "Invalid Member", "555-0106", 1006, 15);
            System.out.println("Registration successful.");
        } catch (IllegalArgumentException e) {
            System.out.println("\nRegistration failed: " + e.getMessage());
        }

        Librarian librarian = new Librarian(3, "David Smith", "555-0103", 201, "Circulation", 4000, 50, 200);
        SeniorLibrarian senior = new SeniorLibrarian(4, "Mary Johnson", "555-0104", 202, "Management", 5000, 60, 250, "Archives", 500);
        LibraryAssistant assistant = new LibraryAssistant(5, "Peter Jones", "555-0105", 203, "Customer Service", 3000, "Evening", 200);

        System.out.println("\n===== LIBRARIAN DETAILS =====");
        librarian.displayStaffDetails();

        System.out.println("\n===== SENIOR LIBRARIAN DETAILS =====");
        senior.displayStaffDetails();

        System.out.println("\n===== LIBRARY ASSISTANT DETAILS =====");
        assistant.displayStaffDetails();

        System.out.println("\n===== LATE FEE CALCULATIONS =====");
        System.out.printf("Default fee for 5 overdue days: $%.2f%n", librarian.calculateLateFee(5));
        System.out.printf("Fee for 5 overdue days at $1.50 per day: $%.2f%n", librarian.calculateLateFee(5, 1.50));

        try {
            librarian.calculateLateFee(-2);
        } catch (IllegalArgumentException e) {
            System.out.println("Late fee error: " + e.getMessage());
        }

        try {
            librarian.calculateLateFee(5, -1.00);
        } catch (IllegalArgumentException e) {
            System.out.println("Late fee error: " + e.getMessage());
        }

        System.out.println("\n===== RUNTIME POLYMORPHISM =====");

        LibraryStaff[] staff = {librarian, senior, assistant};

        for (LibraryStaff employee : staff) {
            System.out.println("\nRole: " + employee.getRoleDescription());
            employee.displayStaffDetails();
            System.out.printf("Calculated Compensation: $%.2f%n", employee.calculateMonthlyCompensation());
        }

        System.out.println("\n===== INTERFACE DEMONSTRATION =====");

        LibraryReport report = new LibraryReport();

        Reportable reportable = report;
        reportable.generateReport();

        Reservable reservable = report;
        reservable.reserveBook("B001");
        reservable.reserveBook("B001");
        reservable.reserveBook("B999");
        reservable.reserveBook("");

        System.out.println("\n===== FINAL COUNTERS =====");
        System.out.println("Total People: " + Person.getPersonCount());
        System.out.println("Total Members: " + Member.getMemberCount());
        System.out.println("Total Staff: " + LibraryStaff.getStaffCount());

        System.out.println("\n===== APPLICATION FINISHED =====");
    }
}
