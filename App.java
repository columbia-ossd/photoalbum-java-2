import java.util.Scanner;


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
			else if (option != 0) {
				System.out.println("Invalid option");
			}

		}
		System.out.println("Good bye!");
	}

	public static int showMenu() {
		System.out.println("1: List all photos\n" + 
				"2: View a photo\n" + 
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