package filetracker;

public class FileInfo {

    private String firstName;
    private String lastName;
    private String letterYear;
    private String pathToFile;

    /**
     * Creates a file info java bean with values
     * @param firstName First name to save letter under
     * @param lastName Last name to save letter under
     * @param letterYear Year of letter
     * @param pathToFile Path to the file location of letter
     */
    public FileInfo(String firstName, String lastName, String letterYear, String pathToFile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.letterYear = letterYear;
        this.pathToFile = pathToFile;
    }
    
    /**
     * Creates a file info java bean clone of another file info
     * @param fileInfo The file info to clone
     */
    public FileInfo(FileInfo fileInfo) {
    	this.firstName = fileInfo.firstName;
    	this.lastName = fileInfo.lastName;
    	this.letterYear = fileInfo.letterYear;
    	this.pathToFile = fileInfo.pathToFile;
    }
    
    /*
     * Setters and getters
     */
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLetterYear() {
        return letterYear;
    }

    public void setLetterDate(String letterYear) {
        this.letterYear = letterYear;
    }
    
    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }
    
    /**
     * Checks the equality of two file info objects
     * @param fileInfo the file info to compare to this one
     * @return true if the files are equal, false otherwise
     */
    public boolean equals(FileInfo fileInfo) {
    	if (this.firstName.equals(fileInfo.firstName) && this.lastName.equals(fileInfo.lastName) &&
    			this.letterYear.equals(fileInfo.letterYear) && this.pathToFile.equals(fileInfo.pathToFile)) {
    		return true;
    	}
    	else return false;
    }
    
}
