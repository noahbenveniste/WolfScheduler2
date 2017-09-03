/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Object class that represents an event
 * @author Noah Benveniste
 */
public class Event extends Activity{

	/** The number of weeks the event repeats */
	private int weeklyRepeat;
	/** Details describing the event */
	private String eventDetails;
	
	/**
	 * Constructs an event object
	 * @param title The title of the event
	 * @param meetingDays A string made up of the first letters of the days the event takes place on
	 * @param startTime Start time of the Event
	 * @param endTime End time of the Event
	 * @param weeklyRepeat The number of weeks the event repeats for
	 * @param eventDetails Details describing the event
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setWeeklyRepeat(weeklyRepeat);
		setEventDetails(eventDetails);
	}

	/**
	 * Get the number of weeks the event repeats
	 * @return the weeklyRepeat
	 */
	public int getWeeklyRepeat() {
		return weeklyRepeat;
	}

	/**
	 * Set the number of weeks the event repeats
	 * @param weeklyRepeat the weeklyRepeat to set
	 * @throws IllegalArgumentException if the input is less than 1 or greater than 4
	 */
	public void setWeeklyRepeat(int weeklyRepeat) {
		if (weeklyRepeat < 1 || weeklyRepeat > 4) {
			throw new IllegalArgumentException("Invalid weekly repeat.");
		}
		this.weeklyRepeat = weeklyRepeat;
	}

	/**
	 * Get the event details
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Set the event details
	 * @param eventDetails the eventDetails to set
	 * @throws IllegalArgumentException if the input is null
	 */
	public void setEventDetails(String eventDetails) {
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		this.eventDetails = eventDetails;
	}
	
	/**
	 * Creates and returns a string array containing two empty strings for fields that Event does not
	 * have, as well as the Event title and meetingString
	 * @return the short string array
	 */
	@Override
	public String[] getShortDisplayArray() {
		return new String[] {"", "", this.getTitle(), this.getMeetingString()};
	}

	/**
	 * Creates and returns a string array containing two empty strings for fields that Event does not have, title,
	 * another two empty strings, meetingString and eventDetails.
	 * @return the long string array
	 */
	@Override
	public String[] getLongDisplayArray() {
		return new String[] {"", "", this.getTitle(), "", "", this.getMeetingString(), "" + this.getEventDetails()};
	}

	
	/** Overridden getMeetingString, toString */
	
	
	/**
	 * Overrides the super method so that the weeklyRepeat value is appended to the string
	 * @return the meetingString
	 */
	@Override
	public String getMeetingString() {
		return super.getMeetingString() + " (every " + this.getWeeklyRepeat() + " weeks)";
	}

	/**
	 * Returns a comma separated list of the Event's state as a string
	 * @return the comma separated list
	 */
	@Override
	public String toString() {
		return this.getTitle() + "," + this.getMeetingDays() + "," + this.getStartTime() + "," +
				this.getEndTime() + "," + this.getWeeklyRepeat() + "," + this.getEventDetails();
	}
	
}