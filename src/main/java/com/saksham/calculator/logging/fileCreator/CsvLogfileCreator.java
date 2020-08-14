package com.saksham.calculator.logging.fileCreator;

import com.saksham.calculator.Operation;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CsvLogfileCreator implements LogfileCreator {
    @Override
    public File createLogFile(List<Operation> operations) {
        String path = System.getProperty("user.dir")+"/src/main/java/com/saksham/calculator/logging/fileCreator/csv.csv";
    	
    	List<String[]> dataLines = new ArrayList<>();
    	dataLines.add(new String[] {"Value", "Operation"});
    	for(int i=0;i<operations.size();i++)
    		dataLines.add(new String[] {String.valueOf(i+1), operations.get(i).toString()});
    	try 
    	{
			@SuppressWarnings("resource")
			CSVWriter writer = new CSVWriter(new FileWriter(path));
			writer.writeAll(dataLines);
			writer.flush();	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	File file = new File(path);
        return file;
    }
}
