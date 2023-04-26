package filetracker;

public class FileInfo {

    private String firstName;
    private String lastName;
    private String letterYear;
    private String pathToFile;

    /*
     * Java bean for file info
     */
    public FileInfo(String firstName, String lastName, String letterYear, String pathToFile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.letterYear = letterYear;
        this.pathToFile = pathToFile;
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
}
