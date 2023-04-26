package filetracker;

import java.util.ArrayList;
import java.util.List;

public class MetadataManager {
    
	public static boolean containsMetadata(List<String> letterData) {
		if (letterData.size() >= 6 && letterData.get(letterData.size() - 6).equals("META") &&
        		letterData.get(letterData.size() - 1).equals("META")) {
        	return true;
        }
		else return false;
	}
	
    /*
     * Combine file info and letter data into new list with metadata
     */
    public static void addMetadata(List<String> letterData, FileInfo fileInfo) {
    	List<String> metadata = new ArrayList<String>();
        metadata.add("META");
        metadata.add(fileInfo.getFirstName());
        metadata.add(fileInfo.getLastName());
        metadata.add(fileInfo.getLetterYear());
        metadata.add(fileInfo.getPathToFile());
        metadata.add("META");
        
        //Combine both lists
        letterData.addAll(metadata);
    }

    /*
     * Removes metadata from list of strings and returns the metadata as a file info object
     */
    public static FileInfo splitMetadata(List<String> letterDataWithMeta) {
    	int size = letterDataWithMeta.size();
    	if (!containsMetadata(letterDataWithMeta)) {
    		throw new IllegalArgumentException("File does not contain metadata");
    	}
        FileInfo fileInfo;
        fileInfo = new FileInfo(
				letterDataWithMeta.get(size - 5),
				letterDataWithMeta.get(size - 4),
				letterDataWithMeta.get(size - 3),
				letterDataWithMeta.get(size - 2)
		);
        // Remove metadata from list
        for (int i = 0; i < 6; i++) {
        	letterDataWithMeta.remove(size - 6);
        }
        
    	return fileInfo;
    }   
    
}

