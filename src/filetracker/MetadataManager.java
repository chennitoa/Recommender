package filetracker;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MetadataManager {
    
    /*
     * Combine file info and letter data into new list with metadata
     */
    public static void addMetadata(List<String> letterData, FileInfo fileInfo) {
    	List<String> metadata = new ArrayList<String>();
        metadata.add("META");
        metadata.add(fileInfo.getFirstName());
        metadata.add(fileInfo.getLastName());
        metadata.add(fileInfo.getLetterDate().toString());
        metadata.add(fileInfo.getLastModified().toString());
        metadata.add(fileInfo.getPathToFile().toString());
        metadata.add("META");
        
        //Combine both lists
        letterData.addAll(metadata);
    }

    /*
     * Removes metadata from list of strings and returns the metadata as a file info object
     */
    public static FileInfo splitMetadata(List<String> letterDataWithMeta) {
    	int size = letterDataWithMeta.size();
        if (!letterDataWithMeta.get(size - 7).equals("META") &&
        		!letterDataWithMeta.get(size - 1).equals("META")) {
        	throw new IllegalArgumentException("List does not contain metadata");
        }
        FileInfo fileInfo;
		try {
			fileInfo = new FileInfo(
					letterDataWithMeta.get(size - 6),
					letterDataWithMeta.get(size - 5),
					LocalDate.parse(letterDataWithMeta.get(size - 4)),
					LocalDate.parse(letterDataWithMeta.get(size - 3)),
					new URL(letterDataWithMeta.get(size - 2))
			);
		}
		
		catch (MalformedURLException e) {
			throw new IllegalArgumentException("Invalid URL in metadata");
		}
        // Remove metadata from list
        for (int i = 0; i < 7; i++) {
        	letterDataWithMeta.remove(size - 7);
        }
        
    	return fileInfo;
    }    
}

