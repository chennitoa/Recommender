package filetracker;

import java.util.List;

import letter.LetterInfo;

public class MetadataManager {
    
    
    /*
     * Overloaded methods.
     * If there is a file object passed as an argument, we combine into the list of string
     */
    public List<String> converter(List<String> list, FileInfo fileObj) {
        List<String> combined = list;
        combined.add(fileObj.getFirstName());
        combined.add(fileObj.getLastName());
        combined.add(fileObj.getURL());
        combined.add(fileObj.getLastModified().toString());
        combined.add(fileObj.getLetterDate().toString());
        return combined;
    }
}
