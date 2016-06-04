package data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;

public class HashSetFromFile extends HashSet<String> {
	
	public HashSetFromFile(String file_path) {
		
		try {
			
			Scanner scanner = new Scanner(new FileReader(file_path));
			while (scanner.hasNext()) {
				add(scanner.nextLine());
			}
			scanner.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
}
