import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Album {
	
	private String name;
	private ArrayList<Photo> photos;
	
	public Album(String name, ArrayList<Photo> photos) {
		this.name = name;
		this.photos = photos;
	}
	
	public Album(String name, String filename) {
		this.name = name;
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
