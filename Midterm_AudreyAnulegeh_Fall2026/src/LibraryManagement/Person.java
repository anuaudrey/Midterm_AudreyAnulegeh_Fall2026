
package LibraryManagement;

/*
 * Description:
 * The Person class represents a person in the Maple Leaf Public Library
 * Management System. It is an abstract parent class for classes such as
 * Member and LibraryStaff.
 *
 * It stores basic personal information, validates the information provided,
 * keeps track of the total number of successfully created Person objects,
 * and provides methods to display and retrieve personal details.
 *
 * Because this class is abstract, objects cannot be created directly from it.
 * Subclasses must provide their own implementation of getRoleDescription().
 */

public abstract class Person {

    // Stores the person's unique ID. The value cannot be changed after initialization.
    private final int personId;

    // Stores the person's name.
    private String name;

    // Stores the person's phone number.
    private String phoneNumber;

    // Tracks the total number of successfully created Person objects.
    private static int personCount = 0;

    // Constructor initializes the person's information and validates the input.
    public Person(int personId, String name, String phoneNumber) {

        // Checks that the ID is positive and the name and phone number are not empty.
        if (personId <= 0 || name == null || name.trim().isEmpty() || phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid person information.");
        }

        // Assigns the validated information to the object's fields.
        this.personId = personId;
        this.name = name;
        this.phoneNumber = phoneNumber;

        // Increases the counter after the person has been initialized successfully.
        personCount++;
    }

    // Returns the total number of successfully created Person objects.
    public static int getPersonCount() {
        return personCount;
    }

    // Returns this person's ID.
    public int getPersonId() {
        return personId;
    }

    // Returns this person's name.
    public String getName() {
        return name;
    }

    // Displays the person's basic information.
    public void displayBasicDetails() {
        System.out.println("Person ID: " + personId);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phoneNumber);
    }

    // Abstract method that subclasses must implement to describe their role.
    public abstract String getRoleDescription();

    // Overrides toString() to return the person's information as a String.
    @Override
    public String toString() {
        return "ID: " + personId + ", Name: " + name + ", Phone: " + phoneNumber;
    }
}
