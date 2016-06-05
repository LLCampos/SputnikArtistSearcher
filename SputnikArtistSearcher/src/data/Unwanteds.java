package data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.commons.io.FilenameUtils;

/**
 * This Class represents a list of entities the user doesn't want, a type of blacklist. 
 * For example, a list of artists the user doesn't want to be recommended to her.
 * 
 * @author Luis Campos
 *
 */
@SuppressWarnings("serial")
public class Unwanteds extends HashSet<String> {
	
	/**
	 * 
	 * @param file_path Is the path to the .txt file with a list of strings, separated by newlines.
	 * @throws FileNotFoundException If no file is found in the path given.
	 */
	public Unwanteds(String file_path) throws FileNotFoundException, IllegalArgumentException {
		
		Scanner scanner = new Scanner(new FileReader(file_path));
		
		if (FilenameUtils.getExtension(file_path) != "txt") {
			scanner.close();
			throw new IllegalArgumentException("The file has to be in .txt format");
		};

		while (scanner.hasNext()) {
			add(scanner.nextLine());
		}
		
		scanner.close();
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
