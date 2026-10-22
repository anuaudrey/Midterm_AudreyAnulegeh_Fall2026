
package LibraryManagement;

import java.util.HashSet;
import java.util.Set;

/*
 * Description:
 * The LibraryReport class manages book reservations and generates reports
 * for the Maple Leaf Public Library Management System.
 *
 * It implements the Reportable and Reservable interfaces, demonstrating
 * how one class can implement multiple interfaces in Java.
 *
 * The class maintains sets of available and reserved book IDs. It allows
 * users to reserve available books, displays messages for successful or
 * failed reservations, and generates a report showing the total number
 * of people, members, staff, available books, and reserved books.
 */

public class LibraryReport implements Reportable, Reservable {

    // Stores the IDs of books that are initially available for reservation.
    // HashSet prevents duplicate book IDs.
    private final Set<String> availableBooks = new HashSet<>(Set.of("B001", "B002", "B003"));

    // Stores the IDs of books that have been successfully reserved.
    private final Set<String> reservedBooks = new HashSet<>();

    // Implements the reserveBook() method required by the Reservable interface.
    @Override
    public void reserveBook(String bookId) {

        // Checks whether the supplied book ID is null or blank.
        if (bookId == null || bookId.trim().isEmpty()) {
            System.out.println("Reservation failed: Book ID cannot be blank.");

        // Removes the book from availableBooks if it exists and is available.
        } else if (availableBooks.remove(bookId)) {

            // Adds the successfully reserved book to the reserved books set.
            reservedBooks.add(bookId);

            // Confirms that the reservation was successful.
            System.out.println("Reservation successful for book: " + bookId);

        } else {

            // Displays a message if the book does not exist or is already reserved.
            System.out.println("Reservation failed: Book " + bookId + " is unavailable or does not exist.");
        }
    }

    // Implements the generateReport() method required by the Reportable interface.
    @Override
    public void generateReport() {

        // Displays the report heading.
        System.out.println("\n===== LIBRARY REPORT =====");

        // Displays the total number of successfully created Person objects.
        System.out.println("Total People: " + Person.getPersonCount());

        // Displays the total number of successfully registered members.
        System.out.println("Total Members: " + Member.getMemberCount());

        // Displays the total number of successfully created library staff objects.
        System.out.println("Total Staff: " + LibraryStaff.getStaffCount());

        // Displays the IDs of books that are still available for reservation.
        System.out.println("Available Books: " + availableBooks);

        // Displays the IDs of books that have been reserved.
        System.out.println("Reserved Books: " + reservedBooks);
    }
}
