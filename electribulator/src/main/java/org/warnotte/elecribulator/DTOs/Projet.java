package orw.warnotte.elecribulator.DTOs;

public class Projet {
	
	String file_preset;
	String file_modulateur;
	
	/**
	 * 
	 * @param file_preset
	 * @param file_modulateur
	 */
	public Projet(String file_preset, String file_modulateur) {
		super();
		this.file_preset = file_preset;
		this.file_modulateur = file_modulateur;
	}
	public synchronized String getFile_preset() {
		return file_preset;
	}
	public synchronized void setFile_preset(String file_preset) {
		this.file_preset = file_preset;
	}
	public synchronized String getFile_modulateur() {
		return file_modulateur;
	}
	public synchronized void setFile_modulateur(String file_modulateur) {
		this.file_modulateur = file_modulateur;
	}

}
