package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Class that represents activities that can be added to a schedule.
 * Subclasses include courses and events.
 * @author Noah Benveniste
 */
public abstract class Activity {

	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;

	public Activity(String title, String meetingDays, int startTime, int endTime) {
		setTitle(title);
		setMeetingDays(meetingDays);
		setCourseTime(startTime, endTime);
	}

	/**
	 * Gets the course's title.
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the course's title.
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title is null or an empty string
	 */
	public void setTitle(String title) {
		//Check that the input isn't null or an empty string
		if (title == null || title.equals("")) {
	        throw new IllegalArgumentException();
	    }
		this.title = title;
	}

	/**
	 * Gets the meeting days.
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the meeting days.
	 * @param meetingDays the meetingDays to set
	 * @throws IllegalArgumentException if the input is null or an empty string, if the 
	 * input has characters other than m,t,w,h,f,a, if an input contains the character "A" 
	 * with any other characters
	 */
	public void setMeetingDays(String meetingDays) {
		//Check that the input isn't null or an empty string
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException();
		}
		
		//Check for invalid characters
		for (int i = 0; i < meetingDays.length(); i++) {
			if (meetingDays.charAt(i) != 'M' && meetingDays.charAt(i) != 'T' && 
					meetingDays.charAt(i) != 'W' && meetingDays.charAt(i) != 'H' && 
					meetingDays.charAt(i) != 'F' && meetingDays.charAt(i) != 'A') {
				throw new IllegalArgumentException();
			}
			//Check that if the string is greater than 1 char, it doesn't
			//contain 'A'
			if (meetingDays.charAt(i) == 'A' && meetingDays.length() > 1) {
				throw new IllegalArgumentException();
			}
		}
		
		this.meetingDays = meetingDays;
	}

	/**
	 * Sets the startTime and the endTime for the course
	 * @param startTime the starting time of the course
	 * @param endTime the ending time of the course
	 * @throws IllegalArgumentException if meetingDays is A and the start time and end time are
	 * not both 0, if the start time and end time are not between 0 and 2359, if the minutes are
	 * not between 0 and 59, or if the start time is greater than the end time
	 */
	public void setCourseTime(int startTime, int endTime) {
		//Check that if meetingDays is "A", startTime and endTime are both zero
		if (this.getMeetingDays().equals("A") && (startTime != 0 && endTime != 0)) {
			throw new IllegalArgumentException();
		}
		//Check that the times are valid
		if (startTime < 0 || startTime > 2359) {
			throw new IllegalArgumentException();
	    //Check that the minutes are between 0 and 59
		} else if ((startTime % 100) < 0 || (startTime % 100) > 59) {
			throw new IllegalArgumentException();
		}
		if (endTime < 0 || endTime > 2359) {
			throw new IllegalArgumentException();
		} else if ((endTime % 100) < 0 || (endTime % 100) > 59) {
			throw new IllegalArgumentException();
		}
		//Check that the startTime is less than the endTime
		if (startTime > endTime) {
			throw new IllegalArgumentException();
		}
		//If all preconditions are met, set the fields with the input values
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Gets the start time.
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Gets the end time.
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Converts the startTime and endTime from military time to standard in the 
	 * form of a string
	 * @return a string representation of the course's meeting time
	 */
	public String getMeetingString() {
		if (this.getMeetingDays().equals("A")) {
			return "Arranged";
		}
	    int startHr = this.getStartTime() / 100;
	    int startMin = this.getStartTime() % 100;
	    int endHr = this.getEndTime() / 100;
	    int endMin = this.getEndTime() % 100;
	    String startMinStr = "";
	    String endMinStr = "";
	    String startStr = "";
	    String endStr = "";
	    
	    //Check formatting of the number of minutes
	    if (startMin < 10) {
	    	startMinStr = "0" + startMin;
	    } else {
	    	startMinStr += startMin;
	    }
	    if (endMin < 10 ) {
	    	endMinStr = "0" + endMin;
	    } else {
	    	endMinStr += endMin;
	    }
	    
	    if (startHr >= 12) {
	    	if (startHr > 12) {
		    	startHr -= 12;
		    }
	    	startStr += startHr + ":" + startMinStr + "PM";
	    } else {
	    	startStr += startHr + ":" + startMinStr + "AM";
	    }
	    if (endHr >= 12) {
	    	if (endHr > 12) {
		    	endHr -= 12;
		    }
	    	endStr += endHr + ":" + endMinStr + "PM";
	    } else {
	    	endStr += endHr + ":" + endMinStr + "AM";
	    }
	    return this.getMeetingDays() + " " + startStr + "-" + endStr;	
	}
	
	/**
	 * 
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * 
	 */
	public abstract String[] getLongDisplayArray();

	
	/** Overridden hashCode() and equals() */
	
	
	/**
	 * Generates a hashCode for Course using all fields.
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality over all fields.
	 * @param obj The object to compare
	 * @return true if the objects are the same over all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}