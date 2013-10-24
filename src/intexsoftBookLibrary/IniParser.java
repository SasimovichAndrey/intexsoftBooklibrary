package intexsoftBookLibrary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class IniParser {
	public HashMap<String, String> parse(File file) throws FileNotFoundException{
		HashMap<String, String> resultMap = new HashMap<String, String>();
		// Проверка, явлется ли File файлом
		if(file.isDirectory() == true)
			throw new FileNotFoundException();
		
		Scanner scanner = new Scanner(file);
		String line;
		String [] splitedLine;
		
		while(scanner.hasNextLine()){
			line = scanner.nextLine();
			splitedLine = line.split("=");
			
			if(splitedLine.length <= 1 && !(line.endsWith("="))){
				continue;
			}
			
			if(!line.endsWith("="))
				resultMap.put(splitedLine[0], splitedLine[1]);
			else 
				resultMap.put(splitedLine[0], "");
		}
		
		scanner.close();
		
		return resultMap;
	}
}
