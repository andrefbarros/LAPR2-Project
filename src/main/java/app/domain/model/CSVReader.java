package app.domain.model;

import java.util.ArrayList;
import java.util.List;
import app.service.FileUtils;
import app.service.readCSV.ICSVReader;
import app.service.readCSV.MissingHeaderCSVReader;

/**
 * Vaccination Center mapper
 * 
 * @autor Carlos Lopes <1211277@isep.ipp.pt>
 */
public class CSVReader {

    private String path;

    public CSVReader(String path){
        this.path = path;
    }

    public List<String[]> readSNSUserData() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        List<String> fileData = FileUtils.readFromFile(this.path);

        
        //checks separator
        String className = fileData.get(0).contains(",") ? "app.service.readCSV.MissingHeaderCSVReader" : "app.service.readCSV.HeaderCSVReader";

        Class<?> oClass = Class.forName(className);

        ICSVReader reader = (ICSVReader) oClass.newInstance();

        List<String[]> result = reader.read(fileData);


        return result;

    }

}
