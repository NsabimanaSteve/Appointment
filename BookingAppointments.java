import java.util.Scanner;

public class BookingAppointments {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
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
            System.out.print("Enter your choice: ");
            String choiceStr = scanner.nextLine();

            try {
                int choice = Integer.parseInt(choiceStr);

                switch (choice) {
                    case 1:
                        bookAppointment(weeklySchedule, scanner);
                        break;
                    case 2:
                        rescheduleAppointment(weeklySchedule, scanner);
                        break;
                    case 3:
                        rescheduleAppointment(weeklySchedule, scanner);
                        break;
                    case 4:
                        weeklySchedule.displayWeeklyAppointments();
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer choice.");
            }
        }

        // Close the scanner
        scanner.close();
    }


    public static void bookAppointment(WeeklyApptSchedule schedule, Scanner scanner) {
        // Prompt user for details
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter venue: ");
        String venue = scanner.nextLine();
        System.out.print("Enter purpose: ");
        String purpose = scanner.nextLine();

        // Create an Appointment object
        Appointment newAppointment = new Appointment (name, venue, purpose);

        // Prompt user for the day and time slot
        System.out.print("Enter the day index (0-4, Monday-Friday): ");
        int dayIndex = scanner.nextInt();
        System.out.print("Enter the time slot index (0-8): ");
        int timeSlot = scanner.nextInt();

        // Add the appointment to the schedule
        WeeklyApptSchedule.Day day = WeeklyApptSchedule.Day.values()[dayIndex];
        boolean success = schedule.addAppointment(newAppointment, day, timeSlot);        
        if (success) {
            System.out.println("Appointment booked successfully!");
        } else {
            System.out.println("Failed to book appointment. Please try again.");
        }

        scanner.nextLine(); // Consume newline character
    }

    public static void cancelAppointment(WeeklyApptSchedule schedule, Scanner scanner) {
        // Prompt user for details
        System.out.print("Enter the day index (0-4, Monday-Friday) of the appointment to cancel: ");
        int dayIndex = scanner.nextInt();
        System.out.print("Enter the time slot index (0-8) of the appointment to cancel: ");
        int timeSlot = scanner.nextInt();
    
        // Cancel the appointment
        schedule.cancelAppointment(dayIndex, timeSlot);
        System.out.println("Appointment canceled successfully!");
    }
    
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
            System.out.println("Well done! Make Sure To come on time. Thank you.");
        }
    }
}    