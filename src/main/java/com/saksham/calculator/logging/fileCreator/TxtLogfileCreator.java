package com.saksham.calculator.logging.fileCreator;

import com.saksham.calculator.Operation;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class TxtLogfileCreator implements LogfileCreator {
    @Override
    public File createLogFile(List<Operation> operations) {
    	
    	String path = System.getProperty("user.dir")+"/src/main/java/com/saksham/calculator/logging/fileCreator/txt.txt";

    	try {
    		
        	FileOutputStream out = new FileOutputStream(path);  	 
        	String data = "Value, Operation \n";
        	
        	for(int i=0;i<operations.size();i++)
        		data+=String.valueOf(i+1) + ", " + operations.get(i).toString() + '\n';
        	
			out.write(data.getBytes());
			out.close();
		} 
    	catch (Exception e) {
			e.printStackTrace();
		}
    	
    	File file = new File(path);
        return file;
    }
}
