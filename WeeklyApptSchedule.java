
    /**
    * Represents a weekly appointment schedule.
    */
     
    public class WeeklyApptSchedule {
        private DailyApptSchedule[] apptsForTheWeek;
        public static final int NUM_DAYS = 5;
        public static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        
         public enum Day {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY};
        public static final int NUM_SLOTS = DailyApptSchedule.NUM_SLOTS; // Access NUM_SLOTS from DailyApptSchedule
    
    
        // No-arg constructor creates an array of DailyApptSchedule 
        // for the week
        public WeeklyApptSchedule() {
            apptsForTheWeek = new DailyApptSchedule[NUM_DAYS];
            for (int i = 0; i < NUM_DAYS; i++) {
                apptsForTheWeek[i] = new DailyApptSchedule();
            }
        }
    
        // Display weekly appointments
        // Display the set of appointsments for the week by showing
        // the schedule for each day.
        public void displayWeeklyAppointments() {
            for (int i = 0; i < NUM_DAYS; i++) {
                System.out.println("Appointments for " + DAYS[i] + ":");
                apptsForTheWeek[i].displayAppointments();
                System.out.println();
            }
        }
    
        // Add an appointment for a given timeslot on a given day.
        // Note: You may find it helpful to invoke day.ordinal()
        // ordinal() is a method automatically defined for enumerations 
        // it gives an "index" of a particular value in the enumeration.
    
        public boolean addAppointment(Appointment appt, Day day, int timeSlot) {
            // Get the index of the day using the ordinal() method of the Day enum
            int dayIndex = day.ordinal();
            if (dayIndex >= 0 && dayIndex < NUM_DAYS) {
                return apptsForTheWeek[dayIndex].addAppointment(timeSlot, appt);
            }
            return false;
        }
    
        /**
         * Cancels the appointment at the specified day and time slot.
         * 
         * @param dayIndex2 The index of the day.
         * @param timeSlot The time slot index.
         */
         
        public void cancelAppointment(int dayIndex2, int timeSlot) {
            if (dayIndex2 >= 0 && dayIndex2 < NUM_DAYS) {
                apptsForTheWeek[dayIndex2].removeAppointment(timeSlot);
            }
        }
        
        /** 
     * Reschedules an appointment from the current day and time slot to a new day and time slot.
     * 
     * @param currentDayIndex The index of the current day.
     * @param currentTimeSlot The index of the current time slot.
     * @param newDayIndex The index of the new day.
     * @param newTimeSlot The index of the new time slot.
     * @return true if the appointment is successfully rescheduled, false otherwise.
     */
   
     public boolean rescheduleAppointment(int currentDayIndex, int currentTimeSlot, int newDayIndex, int newTimeSlot) {
        // Check if the current and new day indices are within bounds
        if (currentDayIndex >= 0 && currentDayIndex < NUM_DAYS && newDayIndex >= 0 && newDayIndex < NUM_DAYS) {
            // Get the appointment to reschedule
            Appointment appointmentToReschedule = apptsForTheWeek[currentDayIndex].apptsForTheDay[currentTimeSlot];
    
            // Check if the appointment exists
            if (appointmentToReschedule != null) {
                // Remove the appointment from the current day
                apptsForTheWeek[currentDayIndex].apptsForTheDay[currentTimeSlot] = null;
    
                // Add the appointment to the new day and time slot
                boolean addedSuccessfully = apptsForTheWeek[newDayIndex].addAppointment(newTimeSlot, appointmentToReschedule);
    
                // If adding to the new day is successful, return true
                if (addedSuccessfully) {
                    return true;
                } else {
                    // If adding fails, restore the appointment to its original day and time slot
                    apptsForTheWeek[currentDayIndex].apptsForTheDay[currentTimeSlot] = appointmentToReschedule;
                }
            }
        }
        return false;
    }
    
}