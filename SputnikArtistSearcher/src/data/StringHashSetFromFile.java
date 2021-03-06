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
 * This Class represents a set of strings obtained from a newline-separated .txt file. 
 * Every member of the set is in lowercase.
 * 
 * @author Luis Campos
 *
 */
@SuppressWarnings("serial")
public class StringHashSetFromFile extends HashSet<String> {
	
	/**
	 * 
	 * @param file_path Is the path to the .txt file with a list of strings, separated by newlines.
	 * @throws FileNotFoundException If no file is found in the path given.
	 * @throws IllegalArgumentException If the file is not in .txt extension.
	 */
	public StringHashSetFromFile(String file_path) throws FileNotFoundException, IllegalArgumentException {
		
		Scanner scanner = new Scanner(new FileReader(file_path));
		
		if (!FilenameUtils.getExtension(file_path).equals("txt")) {
			scanner.close();
			throw new IllegalArgumentException("The file has to be in .txt format");
		};

		while (scanner.hasNext()) {
			add(scanner.nextLine());
		}
		
		scanner.close();
	}
	
	/**
	 * Saves object into a file, writing each String in a different line.
	 * 
	 * @param path Where to save the file, including the name of the file.
	 * @throws IOException
	 */
	public void save(String path) throws IOException {
		
		FileWriter write = new FileWriter(path);
		
		Iterator<String> itr = this.iterator();
		
		while (itr.hasNext()) {
			String next = itr.next();
			write.write(next + "\n");
		}
		
		write.close();	
	}
	
	
	/**
	 * Adds a String to the set.
	 * 
	 * @param str A String to add to set.
	 * @return false If String is already in set. true otherwise.
	 */
	public boolean add(String str) {
		return super.add(str.toLowerCase());
	}
}
