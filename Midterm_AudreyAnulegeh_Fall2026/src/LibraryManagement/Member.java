
package LibraryManagement;

/*
 * Description:
 * The Member class represents a registered member of the Maple Leaf Public
 * Library Management System. It extends the Person class and inherits basic
 * personal information such as the person's ID, name, and phone number.
 *
 * It stores member-specific information, validates registration details,
 * tracks outstanding fees, and keeps count of registered members.
 *
 * It also demonstrates method overloading through two registerMember()
 * methods, allowing members to register with either a default Standard
 * membership or a specified membership type.
 */

public class Member extends Person {

    // Stores the member's unique membership number.
    private final int memberNumber;

    // Stores the member's age.
    private final int age;

    // Stores the member's membership type, such as Standard or Premium.
    private final String membershipType;

    // Stores the total fees the member still owes to the library.
    private double outstandingFees;

    // Tracks the total number of successfully registered members.
    private static int memberCount = 0;

    // Constructor initializes the member's personal and membership information.
    public Member(int personId, String name, String phoneNumber, int memberNumber, int age, String membershipType) {

        // Validates member-specific information before calling the Person constructor.
        super(validateMember(personId, memberNumber, age, membershipType), name, phoneNumber);

        // Assigns the validated membership information to the object's fields.
        this.memberNumber = memberNumber;
        this.age = age;
        this.membershipType = membershipType;

        // New members start with no outstanding fees.
        this.outstandingFees = 0;

        // Increases the member counter after successful initialization.
        memberCount++;
    }

    // Validates the person's ID, member number, age, and membership type.
    private static int validateMember(int personId, int memberNumber, int age, String membershipType) {

        // IDs and member numbers must be positive, members must be at least 18,
        // and the membership type cannot be null or empty.
        if (personId <= 0 || memberNumber <= 0 || age < 18 || membershipType == null || membershipType.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid member information. Members must be at least 18.");
        }

        // Returns the validated person ID to the parent constructor.
        return personId;
    }

    // Registers a member with the default membership type, Standard.
    public static Member registerMember(int id, String name, String phone, int memberNumber, int age) {
        return new Member(id, name, phone, memberNumber, age, "Standard");
    }

    // Overloaded method registers a member with a specified membership type.
    public static Member registerMember(int id, String name, String phone, int memberNumber, int age, String type) {
        return new Member(id, name, phone, memberNumber, age, type);
    }

    // Returns the total number of successfully registered members.
    public static int getMemberCount() {
        return memberCount;
    }

    // Adds an amount to the member's outstanding fees.
    public void addOutstandingFees(double amount) {

        // Rejects negative amounts, NaN, and positive or negative infinity.
        if (!Double.isFinite(amount) || amount < 0) {
            throw new IllegalArgumentException("Fee cannot be negative or invalid.");
        }

        // Adds the valid amount to the existing outstanding fees.
        outstandingFees += amount;
    }

    // Overrides the Person method to describe this person's role in the library.
    @Override
    public String getRoleDescription() {
        return "Library Member";
    }

    // Displays the member's personal information and membership details.
    public void displayMemberDetails() {

        // Reuses the method inherited from Person to display basic details.
        displayBasicDetails();

        // Displays the member's specific information.
        System.out.println("Member Number: " + memberNumber);
        System.out.println("Age: " + age);
        System.out.println("Membership Type: " + membershipType);

        // Displays outstanding fees formatted to two decimal places.
        System.out.printf("Outstanding Fees: $%.2f%n", outstandingFees);
    }
}
