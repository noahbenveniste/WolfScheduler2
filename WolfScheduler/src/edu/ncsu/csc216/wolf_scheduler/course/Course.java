package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Object class that represents a course
 * @author Noah Benveniste
 * @author Sarah Heckman
 */
public class Course {
    
	/** Fields */
	
	/** Course's name. */
	private String name;
	/** Course's title. */
	private String title;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
	
	/** Constructors */
	
	/**
	 * Constructor for Course object with values for all fields.
	 * @param name Name of the Course
	 * @param title Title of the Course
	 * @param section Section number of the Course
	 * @param credits Number of credit hours for the Course
	 * @param instructorId Course instructor's unity id
	 * @param meetingDays First letter of all days the Course meets
	 * @param startTime Start time of the Course
	 * @param endTime End time of the Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		setName(name);
	    setTitle(title);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	    setMeetingDays(meetingDays);
	    setCourseTime(startTime, endTime);
	}
	
	/**
	 * Constructor for Course that has an arranged meeting time.
	 * @param name Name of the Course
	 * @param title Title of the Course
	 * @param section Section number of the Course
	 * @param credits Number of credit hours for the Course
	 * @param instructorId Course instructor's unity id
	 * @param meetingDays First letter of all days the Course meets
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}
    
	/** Getters and Setters */
	
	/**
	 * Gets the course's name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the Course's name.  If the name is null, has a length less than 4 or 
     * greater than 6, an IllegalArgumentException is thrown.
     * @param name the name to set
     * @throws IllegalArgumentException if name is null or length is less than 4 or 
     * greater than 6
	 */
	private void setName(String name) {
		//First, check that the input isn't null
		if (name == null) {
	        throw new IllegalArgumentException();
	    }
		//Next, check that the input is of the correct length
	    if (name.length() < 4 || name.length() > 6) {
	        throw new IllegalArgumentException();
	    }
		this.name = name;
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
	 * Gets the course's section.
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * Sets the course's section.
	 * @param section the section to set
	 * @throws IllegalArgumentException if the input is null, not of length 3, or not all digits
	 */
	public void setSection(String section) {
		//Check that the input isn't null
		if (section == null) {
		    throw new IllegalArgumentException();	
		}
		//Check that the input is 3 characters long
		if (section.length() != 3 ) {
			throw new IllegalArgumentException();
		}
		//Check that all characters are digits
		for (int i = 0; i < section.length(); i++) {
			//Loop through the length of the string. If any char
			//is not a digit, an exception is thrown
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException();
			}
		}
		this.section = section;
	}
	
	/**
	 * Gets the course's number of credits.
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	/**
	 * Sets the course's number of credits.
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if the number of credits is not between
	 * 1 and 5 inclusive
	 */
	public void setCredits(int credits) {
		//Check that the input is not less than 1 or greater
		//than 5
		if (credits < 1 || credits > 5) {
			throw new IllegalArgumentException();
		}
		this.credits = credits;
	}
	
	/**
	 * Gets the instructor's unity id.
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}
	/**
	 * Sets the instructor's unity id.
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if the input is null or empty
	 */
	public void setInstructorId(String instructorId) {
		//Check that the input isn't null or an empty string
		if (instructorId == null || instructorId.equals("")) {
			throw new IllegalArgumentException();
		}
		this.instructorId = instructorId;
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
    
	/** Overridden hashCode(), equals() and toString() */
	
	/**
	 * Generates a hashCode for Course using all fields.
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + credits;
		result = prime * result + endTime;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
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
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (endTime != other.endTime)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
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
	
	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
	    if (meetingDays.equals("A")) {
	        return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays;
	    }
	    return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays + "," + startTime + "," + endTime; 
	}
	
}
