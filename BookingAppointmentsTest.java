import java.util.Scanner;
public class BookingAppointmentsTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Create a new WeeklyApptSchedule object to manage appointments
        WeeklyApptSchedule weeklySchedule = new WeeklyApptSchedule();

        // Display menu options
        System.out.println("Welcome to the Appointment Booking System!");
        System.out.println("1. Book an appointment");
        System.out.println("2. Cancel an appointment");
        System.out.println("3. Reschedule an appointment");
        System.out.println("4. Display weekly appointments");
        System.out.println("5. Exit");

        // Loop for user interaction
        boolean exit = false;
        while (!exit) {
            // Ask the user for their choice
            System.out.print("Enter your choice: ");
            String choiceStr = scanner.nextLine();

            try {
                // Convert the user's choice to an intege
                int choice = Integer.parseInt(choiceStr);
                // Perform actions based on the user's choice
                switch (choice) {
                    case 1:
                        // Call the method to book an appointment
                        bookAppointment(weeklySchedule, scanner);
                        break;
                    case 2:
                        // Call the method to cancel an appointment
                        cancelAppointment(weeklySchedule, scanner);
                        break;
                        
                    case 3:
                        // Call the method to reschedule an appointment
                        rescheduleAppointment(weeklySchedule, scanner);
                        break;
                    case 4:
                        // Display the weekly appointments
                        weeklySchedule.displayWeeklyAppointments();
                        break;
                    case 5:
                         // Exit the program
                        exit = true;
                        break;
                    default:
                        // Display a message for invalid choices
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                // Display a message if the user enters non-numeric input
                System.out.println("Please try again. Please enter  number between 1 and 5.");
            }
        }

        // Close the scanner
        scanner.close();
    }

    // Method to book a new appointment
    public static void bookAppointment(WeeklyApptSchedule schedule, Scanner scanner) {
        // Ask user for details
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter venue: ");
        String venue = scanner.nextLine();
        System.out.print("Enter purpose: ");
        String purpose = scanner.nextLine();
        System.out.println();
    
        // Create an Appointment object
        Appointment newAppointment = new Appointment(name, venue, purpose); // Initialize newAppointment here
    
        //  // Ask the user for the day and time slot of the appointment
        int dayIndex = -1;
        int timeSlot = -1;
        
        // Loop until valid day and time slot are entered
        while (dayIndex < 0 || dayIndex > 4 || timeSlot < 0 || timeSlot > 8) {
            System.out.println("Please specify the day and time slot for the appointment:");
            System.out.println();

            //Ask for a day index
            System.out.print("Enter the day index (0-4, representing Monday to Friday): ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 0 and 4 to represent the day of the week.");
                scanner.next(); 
            }
            dayIndex = scanner.nextInt();
            if (dayIndex < 0 || dayIndex > 4) {
                System.out.println("Invalid day index. Please enter a number between 0 and 4 to represent the day of the week.");
            }
            //Ask for time slot index
            System.out.print("Enter the time slot index (0-8): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 0 and 8 to represent the time slot.");
                scanner.next(); 
            }
            timeSlot = scanner.nextInt();
            if (timeSlot < 0 || timeSlot > 8) {
                System.out.println("Invalid time slot index. Please enter a number between 0 and 8 to represent the time slot.");
            }
    
            scanner.nextLine(); 
        }
    
        // Add the appointment to the schedule
        WeeklyApptSchedule.Day day = WeeklyApptSchedule.Day.values()[dayIndex];
        boolean success = schedule.addAppointment(newAppointment, day, timeSlot);
        if (success) {
            System.out.println("Appointment booked successfully!");
        } else {
            System.out.println("Failed to book appointment because the slot has been booked. Please try again for other Free time and if you want to see free time enter 4 as your choice.");
        }
    }
    // Method to cancel an existing appointment   
    public static void cancelAppointment(WeeklyApptSchedule schedule, Scanner scanner) {
        // Prompt user for details
        System.out.print("Enter the day index (0-4, Monday-Friday) of the appointment to cancel: ");
        int dayIndex = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter the time slot index (0-8) of the appointment to cancel: ");
        int timeSlot = scanner.nextInt();
        scanner.nextLine(); 
        // Cancel the appointment
        schedule.cancelAppointment(dayIndex, timeSlot);
        System.out.println("Appointment canceled successfully!");
    }

      // Method to reschedule an existing appointment   
    public static void rescheduleAppointment(WeeklyApptSchedule schedule, Scanner scanner) {
        // Prompt user for details
        System.out.print("Enter the current day index (0-4, Monday-Friday) of the appointment to reschedule: ");
        int currentDayIndex = scanner.nextInt();
        System.out.print("Enter the current time slot index (0-8) of the appointment to reschedule: ");
        int currentTimeSlot = scanner.nextInt();
        System.out.print("Enter the new day index (0-4, Monday-Friday) for rescheduling: ");
        int newDayIndex = scanner.nextInt();
        System.out.print("Enter the new time slot index (0-8) for rescheduling: ");
        int newTimeSlot = scanner.nextInt();

        // Reschedule the appointment
        boolean success = schedule.rescheduleAppointment(currentDayIndex, currentTimeSlot, newDayIndex, newTimeSlot);
        if (success) {
            System.out.println("Appointment rescheduled successfully!");
        } else {
            System.out.println("Sory! Failed to reschedule appointment Becuase you have not yet book an apointment. Please ensure the current appointment exists and the new time slot is available.");
        }
    }
}

