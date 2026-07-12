import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Album {
	
	private String name;
	private ArrayList<Photo> photos;
	private String filename;
	
	public Album(String name, ArrayList<Photo> photos) {
		this.name = name;
		this.photos = photos;
		this.filename = null;
	}
	
	public Album(String name, String filename) {
		this.name = name;
		this.filename = filename;
		initialize(filename);
	}
	
	public String getName() { return name; }
	
	public ArrayList<Photo> getPhotos() { return photos; }
	
	public int getNumPhotos() { return photos.size(); }
	
	public String getDescription(int index) {
		if (index < 0 || index >= photos.size()) return "";
		else return photos.get(index).getDescription();
	}
	 
	public String getFilename(int index) {
		if (index < 0 || index >= photos.size()) return "";
		else return photos.get(index).getFilename();
	}

	

	/**
	 * Updates the description of the photo at the given index and
	 * persists the change back to the file the album was loaded from.
	 * Returns true if the update succeeded, false if the index was invalid.
	 */
	public boolean editDescription(int index, String newDescription) {
		if (index < 0 || index >= photos.size()) return false;

		photos.get(index).setDescription(newDescription);
		save();
		return true;
	}

	/**
	 * Writes the current list of photos back out to the file
	 * that this album was initialized from (if any).
	 */
	private void save() {
		if (filename == null) return;

		try {
			PrintWriter out = new PrintWriter(new FileWriter(filename));
			for (Photo p : photos) {
				out.println(p.getFilename() + "," + p.getDescription());
			}
			out.close();
		}
		catch (IOException e) {
			System.out.println("Error saving file");
		}
	}

	public void initialize(String filename) {
		
		photos = new ArrayList<>();
		
		try {
			Scanner in = new Scanner(new File(filename));
			while (in.hasNext()) {
				String line = in.nextLine();
				String[] parts = line.split(",");
				String fname = parts[0];
				String desc = parts[1];
				Photo p = new Photo(fname, desc);
				photos.add(p);
			}
		}
		catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Error reading file");
		}
		
	}
	
}