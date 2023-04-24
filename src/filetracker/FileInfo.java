package filetracker;

import java.net.URL;
import java.time.LocalDate;

public class FileInfo {

    private String firstName;
    private String lastName;
    private URL pathToFile;
    private LocalDate lastModified;
    private LocalDate letterDate;

    /*
     * Public constructor
     */
    public FileInfo(String firstName, String lastName, URL pathToFile, LocalDate lastModified, LocalDate letterDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pathToFile = pathToFile;
        this.lastModified = lastModified;
        this.letterDate = letterDate;
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

    public URL getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(URL pathToFile) {
        this.pathToFile = pathToFile;
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
}
