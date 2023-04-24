package filetracker;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MetadataManager {
    
    /*
     * If there is a file object passed as an argument, we combine into the list of string
     */
    public void addMetadata(List<String> list, FileInfo fileObj) {

        //Runs through the entire list and making sure each string is metadata
        for(String str : list){
            if(!str.substring(0, 4).equals("META")) {
                //Throws illegal exception
                return; //Will use this for now.
            }
        }

        list.add(fileObj.getFirstName());
        list.add(fileObj.getLastName());
        list.add(fileObj.getPathToFile().toString());
        list.add(fileObj.getLastModified().toString());
        list.add(fileObj.getLetterDate().toString());
    }

    /*
     * If there is no file object passed as an argument, we split them up
     */
    public FileInfo splitMetadata(List<String> list) throws MalformedURLException {
        List<String> info = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).length() < 4) {
                info.add(list.get(i));
                list.remove(i);
                i--;
                continue;
            }
            String temp = list.get(i).substring(0, 4);
            if(!temp.equals("META")) {
                info.add(list.get(i));
                list.remove(i);
                i--;
                
            }
        }
        LocalDate lastMod = LocalDate.parse(info.get(3), formatter);
        LocalDate letterDate = LocalDate.parse(info.get(4), formatter);
        URL path = new URL(info.get(2));
        return (new FileInfo(info.get(0), info.get(1), path, lastMod,letterDate));
        }

        // public static void main(String[] args) throws MalformedURLException {
        //     List<String> ls = new ArrayList<>(Arrays.asList("METAmsg" , "METAmsg2", "firstN", "lastName", "METAurl", "https://www.tutorialspoint.com/", "04/23/2023", "04/22/2023"));
        //     FileInfo f = splitMetadata(ls);
        //     System.out.println(f.getFirstName()+ f.getLastName()+ f.getPathToFile().toString()+ f.getLastModified().toString()+ f.getLetterDate().toString());
        //     for(String str : ls) {
        //         System.out.println(str);
        //     }
        //     addMetadata(ls, f);
        //     System.out.println("");
        //     for(String str : ls) {
        //         System.out.println(str);
        //     }
        // }
    }

