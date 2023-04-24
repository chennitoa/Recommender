package filetracker;

import java.time.LocalDate;

public class FileInfo {

    private String firstName;
    private String lastName;
    private String URL;
    private LocalDate lastModified;
    private LocalDate letterDate;

    /*
     * Public constructor
     */
    public FileInfo() {
        
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

    public String getURL() {
        return URL;
    }

    public void setURL(String uRL) {
        URL = uRL;
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
