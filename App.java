import java.util.Scanner;

import java.io.File;


public class App {

	public static void main(String[] args) {

		Album a = new Album("cartoon characters", "images.txt");

		int option = -1;

		while (option != 0) {	
			option = showMenu();

			if (option == 1) {
				listAllPhotos(a);
			}
			else if (option == 2) {
				viewPhoto(a);
			}
			else if (option == 3) {
				editPhoto(a);
			}
			else if (option == 4) {
				addPhoto(a);
			}
			else if (option != 0) {
				System.out.println("Invalid option");
			}

		}
		System.out.println("Good bye!");
	}

	public static int showMenu() {
		System.out.println("1: List all photos\n" + 
				"2: View a photo\n" + 
				"3: Edit a photo's description\n" +
				"4: Add a photo\n" +
				"0: Exit");
		System.out.print("Choose an option: ");
		Scanner in = new Scanner(System.in);
		return in.nextInt(); 
	}


	private static void listAllPhotos(Album a) {
		for (Photo p : a.getPhotos()) {
			System.out.println(p);
		}
	}

	private static void viewPhoto(Album a) {
		for (int i = 0; i < a.getNumPhotos(); i++) {
			System.out.println((i+1) + ": " + a.getDescription(i));
		}
		System.out.print("Enter number: ");
		Scanner in = new Scanner(System.in);

		int choice = in.nextInt();
		if (choice <= 0 || choice > a.getNumPhotos()) {
			System.out.println("Invalid selection");
		}
		else {
			showPhoto(a.getFilename(choice-1));
		}
	}

	private static void editPhoto(Album a) {
		for (int i = 0; i < a.getNumPhotos(); i++) {
			System.out.println((i+1) + ": " + a.getDescription(i));
		}
		System.out.print("Enter number: ");
		Scanner in = new Scanner(System.in);

		int choice = in.nextInt();
		in.nextLine(); // consume the rest of the line after the number

		if (choice <= 0 || choice > a.getNumPhotos()) {
			System.out.println("Invalid selection");
		}
		else {
			System.out.print("Enter new description: ");
			String newDescription = in.nextLine();

			if (a.editDescription(choice-1, newDescription)) {
				System.out.println("Description updated");
			}
			else {
				System.out.println("Could not update description");
			}
		}
	}

	private static void addPhoto(Album a){
		Scanner in = new Scanner(System.in);
		String filename  = "";
		String description = "";

		while(filename.trim().equals("")){
			System.out.print("Enter image filename:");
			filename = in.nextLine();
		}

		File image = new File(filename);
		if (!image.exists()){
			System.out.println("Not Found");
			return;
		}

		while (description.trim().equals("")){
			System.out.print("Enter description:");
			description = in.nextLine();

		}

		Photo p = new Photo(filename,description);
		a.addPhoto(p);

		System.out.println("Successfully Added");

	}



	/*
	 * This method can be used to show a single photo.
	 */
	private static void showPhoto(String filename) {
		try {
			StdDraw.picture(0.5, 0.5, filename, 0.8, 0.8);
			Thread.sleep(1000);
		}
		catch (IllegalArgumentException e) {
			System.out.println("ERROR: image [" + filename + "] not found");
		} 
		catch (InterruptedException e) {
		}
	}


}