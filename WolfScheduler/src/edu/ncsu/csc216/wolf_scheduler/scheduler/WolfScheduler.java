package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * Class that handles the creation and manipulation of a schedule of course and a catalog of
 * available courses
 * @author Noah Benveniste
 * @author Sarah Heckman
 */
public class WolfScheduler {
	
	/** Fields */
	
	/** Catalog of courses to be chosen from */
	private ArrayList<Course> courseCatalog;
	/** The student's schedule */
	private ArrayList<Course> schedule;
	/** The title of the schedule */
	private String title;
	
	/** Default Schedule Name */
	public static final String DEFAULT_SCHEDULE_NAME = "My Schedule";
	
	/** Constructor */
	
	/**
	 * Constructor for WolfScheduler object. Initializes course catalog and schedule array lists
	 * and attempts to populate the course catalog with courses from the input file
	 * @param inFile the name of the file to be read
	 * @throws IllegalArgumentException if the input file cannot be read
	 */
	public WolfScheduler(String inFile) {
		//Create the course catalog
		ArrayList<Course> c = new ArrayList<Course>();
		this.courseCatalog = c;
		
		//Create the schedule
		ArrayList<Course> s = new ArrayList<Course>();
		this.schedule = s;
		
		//Set the default schedule name
		this.title = DEFAULT_SCHEDULE_NAME;
		
		//Try to add the courses from the input file to the course catalog
		try {
			this.courseCatalog = CourseRecordIO.readCourseRecords(inFile);
		} catch (FileNotFoundException e ) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}

	/** Methods */
	
	/**
	 * Attempts to retrieve a course from the course catalog based on an input name and
	 * section number
	 * @param name the name of the course
	 * @param section the section for the course
	 * @return the course object corresponding to the input if it exists in the course
	 * catalog or null if the course does not exist
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < this.courseCatalog.size(); i++) {
			//Check if the currently indexed course in the catalog has the same name and
			//section as the input name and section
			if (this.courseCatalog.get(i).getName().equals(name) && 
					this.courseCatalog.get(i).getSection().equals(section)) {
				//If true, return the course
				return this.courseCatalog.get(i);
			}
		}
		//If no matches were found while indexing the course catalog, return null
		return null;
	}
	
	/**
	 * Checks if a given course (identified by name and section) can be added to the schedule
	 * and then adds it if allowed
	 * @param name the name of the course
	 * @param section the section for the course
	 * @return true if it can be added (i.e. it is not already in the schedule), false if it 
	 * does not exist in the course catalog
	 * @throws IllegalArgumentException if the course is already in the schedule
	 */
	public boolean addCourse(String name, String section) {
		//First, check that the course actually exists in the catalog
		if (this.getCourseFromCatalog(name, section) == null) {
			//Return false if it does not exist
			return false;
		}
		//Next, check that a course with the same name does not already exist in the schedule
		for (int i = 0; i < this.schedule.size(); i++) {
			//Check if the currently indexed course in the schedule has the same name as the input
			if (this.schedule.get(i).getName().equals(name)) {
				//If true, throw an exception
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}
		}
		//If the course passed the above two tests, add it to the schedule (the index of 
		//the added element changes dynamically - if a course is being added to an empty
		//schedule, the schedule size will be 0 and the course must be assigned to index
		//0 etc)
		this.schedule.add(this.schedule.size(), this.getCourseFromCatalog(name, section));
		//If the loop executes without throwing an exception, the course does not already
		//exist in the schedule
		return true;
	}
	
	/**
	 * Checks if a given course can be removed from the student's schedule and then removes
	 * it if allowed
	 * @param name the name of the course
	 * @param section the section for the course
	 * @return true if the course can be removed (i.e. it is in the schedule), false if it
	 * cannot be removed (it is not in the schedule)
	 */
	public boolean removeCourse(String name, String section) {
		for (int i = 0; i < this.schedule.size(); i++) {
			//Check if the currently indexed course in the schedule has the same name and
			//section as the input name and section
			if (this.schedule.get(i).getName().equals(name) && 
					this.schedule.get(i).getSection().equals(section)) {
				//If true, remove the course and then return true
				this.schedule.remove(this.getCourseFromCatalog(name, section));
				return true;
			}
		}
		//If the loop executes without returning true, the course does not exist in the schedule, 
		//so return false
		return false;
	}
	
	/**
	 * Creates a new empty ArrayList and assigns it to the schedule field, resetting
	 * the schedule to empty
	 */
	public void resetSchedule() {
		//Create a new empty array list of courses
		ArrayList<Course> newEmptySchedule = new ArrayList<Course>();
		//Set the schedule field of the object to the newly created empty schedule
		this.schedule = newEmptySchedule;
	}
	
	/**
	 * Creates and returns a 2D string array representation of the course catalog containing
	 * information about the courses' name, section and title
	 * @return a 2D string array of the course catalog if there are courses in the catalog,
	 * or an empty string array otherwise
	 */
	public String[][] getCourseCatalog() {
		//The number of rows is determined by the number of courses in the catalog
		int numRow = this.courseCatalog.size();
		//If there are no courses in the catalog, return an empty array
		if (numRow == 0) {
			return new String[0][0];
		}
		//Three columns: name, section, title
		int numCol = 3;
		//Create the string array with the one row per course in the catalog
		String[][] catStr = new String[numRow][numCol];
		//Index through the course catalog, finding the relevant data for each course
		//and then add it to the array in the proper index
		for (int i = 0; i < numRow; i++) {
			//Add the course name
			catStr[i][0] = this.courseCatalog.get(i).getName();
			//Add the course section
			catStr[i][1] = this.courseCatalog.get(i).getSection();
			//Add the course title
			catStr[i][2] = this.courseCatalog.get(i).getTitle();
			//Next iteration of loop adds data for next course in the catalog in the
			//next row of the array
		}
		return catStr;
	}

	/**
	 * Creates and returns a 2D string array representation of the schedule containing
	 * information about the courses' name, section and title
	 * @return a 2D string array of the schedule if there are courses in the schedule,
	 * or an empty string array otherwise
	 */
	public String[][] getScheduledCourses() {
		//The number of rows is determined by the number of courses in the schedule
		int numRow = this.schedule.size();
		//If there are no courses in the schedule, return an empty array
		if (numRow == 0) {
			return new String[0][0];
		}
		//Three columns: name, section, title
		int numCol = 3;
		//Create the string array with the one row per course in the schedule
		String[][] schedStr = new String[numRow][numCol];
		//Index through the schedule, finding the relevant data for each course
		//and then add it to the array in the proper index
		for (int i = 0; i < numRow; i++) {
			//Add the course name
			schedStr[i][0] = this.schedule.get(i).getName();
			//Add the course section
			schedStr[i][1] = this.schedule.get(i).getSection();
			//Add the course title
			schedStr[i][2] = this.schedule.get(i).getTitle();
			//Next iteration of loop adds data for next course in the catalog in the
			//next row of the array
		}
		return schedStr;
	}

	/**
	 * Creates and returns a 2D string array representation of the schedule containing
	 * information about the courses' name, section, title, credits, instructorId and meetingDays
	 * @return a 2D string array of the schedule if there are courses in the schedule,
	 * or an empty string array otherwise
	 */
	public String[][] getFullScheduledCourses() {
		//The number of rows is determined by the number of courses in the schedule
		int numRow = this.schedule.size();
		//If there are no courses in the schedule, return an empty array
		if (numRow == 0) {
			return new String[0][0];
		}
		//Six columns: name, section, title, credits, instructorId, meetingDays
		int numCol = 6;
		//Create the string array with the one row per course in the schedule
		String[][] fullSchedStr = new String[numRow][numCol];
		//Index through the schedule, finding the relevant data for each course
		//and then add it to the array in the proper index
		for (int i = 0; i < numRow; i++) {
			//Add the course name
			fullSchedStr[i][0] = this.schedule.get(i).getName();
			//Add the course section
			fullSchedStr[i][1] = this.schedule.get(i).getSection();
			//Add the course title
			fullSchedStr[i][2] = this.schedule.get(i).getTitle();
			//Add the credits (make sure to concatenate the returned int to a string
			//before attempting to add it to the array)
			fullSchedStr[i][3] = "" + this.schedule.get(i).getCredits();
			//Add the instructorId
			fullSchedStr[i][4] = this.schedule.get(i).getInstructorId();
			//Add the meetingDays
			fullSchedStr[i][5] = this.schedule.get(i).getMeetingString();
			//Next iteration of loop adds data for next course in the catalog in the
			//next row of the array
		}
		return fullSchedStr;
	}

	/**
	 * Gets the title for the schedule
	 * @return the schedule's title
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Sets the title for the schedule
	 * @param title the user's desired title for the schedule
	 * @throws IllegalArgumentException if the input is null
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		} else {
			this.title = title;
		}
	}

	/**
	 * Exports the schedule to a specified file
	 * @param fileName the name of the file the user wishes to export to
	 * @throws IllegalArgumentException if the specified file cannot be written to
	 */
	public void exportSchedule(String fileName) {
		try {
			ActivityRecordIO.writeCourseRecords(fileName, this.schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}
}
