package program;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ProgramSettings implements Serializable {
	
	private static String programsettings_path = "program_files/program_settings.ser";
	private String unwanted_artist_path;
	private String unwanted_tags_path;
	private String artiststotryfile_path;
	private String camposmusiclist_path;
	
	
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
	
	public void save() {
		try {
			ObjectOutputStream os = new ObjectOutputStream (new FileOutputStream(programsettings_path));
			os.writeObject(this);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getUnwanted_artist_path() {
		return unwanted_artist_path;
	}
	
	public void setUnwanted_artist_path(String unwanted_artist_path) {
		this.unwanted_artist_path = unwanted_artist_path;
	}
	
	public String getUnwanted_tags_path() {
		return unwanted_tags_path;
	}
	
	public void setUnwanted_tags_path(String unwanted_tags_path) {
		this.unwanted_tags_path = unwanted_tags_path;
	}
	
	public String getArtiststotryfile_path() {
		return artiststotryfile_path;
	}
	
	public void setArtiststotryfile_path(String artiststotryfile_path) {
		this.artiststotryfile_path = artiststotryfile_path;
	}
	
	public String getCamposmusiclist_path() {
		return camposmusiclist_path;
	}
	
	public void setCamposmusiclist_path(String camposmusiclist_path) {
		this.camposmusiclist_path = camposmusiclist_path;
	}
	
	
	
	
	
	
}
