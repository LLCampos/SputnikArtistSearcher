package program;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 
 * This class represent the settings of the program.
 * 
 * @author Luis Campos
 *
 */

public class ProgramSettings implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4681185244268839561L;
	
	/**
	 * the path to the file with the program settings.
	 */
	private static String programsettings_path = "program_files/program_settings.ser";
	
	/**
	 * the path to the txt file with the list of unwanted artists.
	 */
	private String unwanted_artist_path;
	
	/**
	 * the path to the txt file with the list of unwanted tags.
	 */
	private String unwanted_tags_path;
	
	/**
	 * the path to the ser file where the ArtistsToTry are saved.
	 */
	private String artiststotryfile_path;
	
	/**
	 * the path to the file with the list of bands form the CamposMusic software.
	 */
	private String camposmusiclist_path;
	
	
	/**
	 * Loads the settings.
	 *
	 * @return the program settings object
	 */
	public static ProgramSettings load() {
		
		ProgramSettings settings = null;
		try {
			ObjectInputStream is = new ObjectInputStream (new FileInputStream(ProgramSettings.programsettings_path));
			settings = (ProgramSettings) is.readObject();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return settings;
	}
	
	/**
	 * Save the program settings.
	 */
	public void save() {
		try {
			ObjectOutputStream os = new ObjectOutputStream (new FileOutputStream(programsettings_path));
			os.writeObject(this);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the path to the txt file with the list of unwanted artists.
	 *
	 * @return the path to the txt file with the list of unwanted artists
	 */
	public String getUnwanted_artist_path() {
		return unwanted_artist_path;
	}
	
	/**
	 * Sets the path to the txt file with the list of unwanted artists.
	 *
	 * @param unwanted_artist_path the new path to the txt file with the list of unwanted artists
	 */
	public void setUnwanted_artist_path(String unwanted_artist_path) {
		this.unwanted_artist_path = unwanted_artist_path;
	}
	
	/**
	 * Gets the path to the txt file with the list of unwanted tags.
	 *
	 * @return the path to the txt file with the list of unwanted tags
	 */
	public String getUnwanted_tags_path() {
		return unwanted_tags_path;
	}
	
	/**
	 * Sets the path to the txt file with the list of unwanted tags.
	 *
	 * @param unwanted_tags_path the new path to the txt file with the list of unwanted tags
	 */
	public void setUnwanted_tags_path(String unwanted_tags_path) {
		this.unwanted_tags_path = unwanted_tags_path;
	}
	
	/**
	 * Gets the path to the ser file where the ArtistsToTry are saved.
	 *
	 * @return the path to the ser file where the ArtistsToTry are saved
	 */
	public String getArtiststotryfile_path() {
		return artiststotryfile_path;
	}
	
	/**
	 * Sets the path to the ser file where the ArtistsToTry are saved.
	 *
	 * @param artiststotryfile_path the new path to the ser file where the ArtistsToTry are saved
	 */
	public void setArtiststotryfile_path(String artiststotryfile_path) {
		this.artiststotryfile_path = artiststotryfile_path;
	}
	
	/**
	 * Gets the path to the file with the list of bands form the CamposMusic software.
	 *
	 * @return the path to the file with the list of bands form the CamposMusic software
	 */
	public String getCamposmusiclist_path() {
		return camposmusiclist_path;
	}
	
	/**
	 * Sets the path to the file with the list of bands form the CamposMusic software.
	 *
	 * @param camposmusiclist_path the new path to the file with the list of bands form the CamposMusic software
	 */
	public void setCamposmusiclist_path(String camposmusiclist_path) {
		this.camposmusiclist_path = camposmusiclist_path;
	}
	
	
	
	
	
	
}
