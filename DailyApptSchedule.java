/**
 * A class to represent the appointment schedule for a given day.
 * Each day is assumed to have hour-long timeslots starting at 8am and
 * with the final timeslot starting at 4pm
 **/

 public class DailyApptSchedule {
    Appointment[] apptsForTheDay;
    public static final int NUM_SLOTS = 9;
    public static final String[] TIMES = {"8am", "9am", "10am", "11am", "12noon", "1pm", "2pm", "3pm", "4pm"};
         
    // No-arg onstructor creates an empty schedule for a day
    public DailyApptSchedule() {
        apptsForTheDay = new Appointment[NUM_SLOTS];
    }

    /**
     * A method to display the list of appointments for the day
     * In the format:
     *    time: appointment_info
     *    time: appointment_info ... etc
     * Any empty slots (null Appointments in the array) should be listed as Free
     */
    public void displayAppointments() {
        for (int i = 0; i < NUM_SLOTS; i++) {
            if (apptsForTheDay[i] != null) {
                System.out.println(TIMES[i] + ": " + apptsForTheDay[i]);
            } else {
                System.out.println(TIMES[i] + ": Free");
            }
        }
    }

     /**
     * A method to add the given appointment to the schedule in the specified timeslot
     * @param whichSlot represents the index of the timeslot (Eg. 0 -> 8am, 1 -> 9am, ...)
     * @param appt represents the appointment object
     * @return true if it was successful and false if not successful
     */
    public boolean addAppointment(int whichSlot, Appointment appt) {
        if (whichSlot >= 0 && whichSlot < NUM_SLOTS && apptsForTheDay[whichSlot] == null) {
            apptsForTheDay[whichSlot] = appt;
            return true;
        }
        return false;
    }

    /**
     * A method to add an appointment for the given person, venue and purpose to the schedule in the specified timeslot.
     * @param whichSlot represents the index of the timeslot (Eg. 0 -> 8am, 1 -> 9am, ...)
     * @param n represents the name of the student
     * @param v represents the location of the appointment
     * @param p represents the reason for the meeting
     * @return true if it was successful and false if not successful (i.e. if the given slot is invalid or taken)
     */
    public boolean addAppointment(int whichSlot, String n, String v, String p) {
        if (whichSlot >= 0 && whichSlot < NUM_SLOTS && apptsForTheDay[whichSlot] == null) {
            apptsForTheDay[whichSlot] = new Appointment(n, v, p);
            return true;
        }
        return false;
    }

    /**
     * A method to add the given appointment to the schedule in any free timeslot.
     * @param appt represents the appointment object
     * @return the index of the chosen timeslot or -1 if no free timeslot exists.
     */
    public int addAppointment(Appointment appt) {
        for (int i = 0; i < NUM_SLOTS; i++) {
            if (apptsForTheDay[i] == null) {
                apptsForTheDay[i] = appt;
                return i;
            }
        }
        return -1; //Means  no free timeslot available
    }

    /**
     * A method to add an appointment for the given person, venue and purpose
     * @param n represents the name of the student
     * @param v represents the location of the appointment
     * @param p represents the reason for the meeting
     * @return the index of the chosen timeslot or -1 if no free timeslot exists.
     */
    public int addAppointment(String n, String v, String p) {
        for (int i = 0; i < NUM_SLOTS; i++) {
            if (apptsForTheDay[i] == null) {
                apptsForTheDay[i] = new Appointment(n, v, p);
                return i;
            }
        }
        return -1; //Means no free timeslot available
    }

        /**
     * Remove the appointment in the given slot
     * @param slot the index of the timeslot
     * @return true if operation was successful and false if not
     */
    public boolean removeAppointment(int slot) {
        if (slot >= 0 && slot < NUM_SLOTS && apptsForTheDay[slot] != null) {
            apptsForTheDay[slot] = null;
            return true;
        }
        return false;
    }

     /**
     * Remove the appointment corresponding to the given student name
     * @param n represents the name of the student
     * @return true if operation was successful and false if not
     */
    public boolean removeAppointment(String n) {
        for (int i = 0; i < NUM_SLOTS; i++) {
            if (apptsForTheDay[i] != null && apptsForTheDay[i].getStudentName().equals(n)) {
                apptsForTheDay[i] = null;
                return true;
            }
        }
        return false;
    }


    /**
     * A method Reschedules an appointment from the current time slot to a new time slot.
     * 
     *@param currentSlot The index of the current time slot.
     * @param newSlot The index of the new time slot.
    * @return true if the appointment is successfully rescheduled, false otherwise.
    */
     
    public boolean rescheduleAppointment(int currentSlot, int newSlot) {
        if (newSlot >= 0 && newSlot < NUM_SLOTS && apptsForTheDay[currentSlot] != null) {
            apptsForTheDay[newSlot] = apptsForTheDay[currentSlot];
            apptsForTheDay[currentSlot] = null;
            return true;
        }
        return false;
    }
}
