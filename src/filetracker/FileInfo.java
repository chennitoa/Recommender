package filetracker;

import java.net.URL;
import java.time.LocalDate;

public class FileInfo {

    private String firstName;
    private String lastName;
    private LocalDate letterDate;
    private LocalDate lastModified;
    private URL pathToFile;

    /*
     * Public constructor
     */
    public FileInfo(String firstName, String lastName, LocalDate letterDate, LocalDate lastModified, URL pathToFile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.letterDate = letterDate;
        this.lastModified = lastModified;
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

    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }

    public LocalDate getLetterDate() {
        return letterDate;
    }

    public void setLetterDate(LocalDate letterDate) {
        this.letterDate = letterDate;
    }
    
    public URL getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(URL pathToFile) {
        this.pathToFile = pathToFile;
    }
}
