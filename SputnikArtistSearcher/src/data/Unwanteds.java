package data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Unwanteds extends HashSet<String> {
	
	private static final long serialVersionUID = 1L;

	public Unwanteds(String file_path) {
		
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
	
	public void save(String path) {
		try {
			FileWriter write = new FileWriter(path);
			
			Iterator<String> itr = this.iterator();
			
			while (itr.hasNext()) {
				String next = itr.next();
				write.write(next + "\n");
			}
			
			write.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean add(String str) {
		return super.add(str.toLowerCase());
	}
}
