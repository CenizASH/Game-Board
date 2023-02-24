import java.io.*; // File Reading
import java.util.*;

// Save Files Class is used for saving and loading the Player object to/from a file. 
public class SaveFiles{
	
	// Default save and load files
	private static String path = "./Files/";
	private static String defaultPlayerFile = "playerFile.txt";
	private static String defaultPlayerSaveFile = "playerSave.txt";

	// Extra Debugging
	private static boolean DEBUG_MODE = false;

	// Load the default player file
	public static Player loadPlayer(){
		return loadPlayer(defaultPlayerFile);
	}
	// Load a specific player file, returning a Player object
	public static Player loadPlayer(String playerFile){

		// Temporary object references
		// We will return this regardless if we can load the file
		// Return a default player if the file can't be read
		Player newPlayer;
		// The itemlist to add to the player
		ArrayList<Item> list = new ArrayList<Item>();

		// Temp strings
		String name = "Default";
		int hitPoints = 10;
		String allItems = "";

		try{
			// Start reading in from File
			FileReader fileRead = new FileReader(path+playerFile);
			BufferedReader buff = new BufferedReader(fileRead);
			String line = buff.readLine();

			// Can we read in
			if(line == null){
				throw new IOException("Invalid File Found");
			}

			// Skip the blank lines
			while(line.length() == 0){
				line = buff.readLine();
			}

			// Get Player Name
			Scanner scan = new Scanner(line);
			String token = scan.next();

			if(token.equals("Player:")){
				name = scan.next();
				if ( DEBUG_MODE) { System.out.println("Name found: " + name); }

				// Get Hit Points
				line = buff.readLine();
			}else{
				System.out.println("ERROR: No Player Name Field Found");
			}

			if(line != null){
				scan = new Scanner(line);
				token = scan.next();
				if( token.equals("Hitpoints:")){
					if(scan.hasNextInt()){
						hitPoints = scan.nextInt();
						if ( DEBUG_MODE) { System.out.println("Hitpoints found: " + hitPoints); }
					}
					
				// Get Item List
				line = buff.readLine();
					
				}else{
					System.out.println("ERROR: No Hitpoints Name Field Found");
				}
			}

			// Read in the Items line
			if(line != null){
				scan = new Scanner(line);
				token = scan.next();
				if( token.equals("Items:")){
					// Include this in spec
					scan.useDelimiter(",");
					
					// While there are more items, read them in. 
					while(scan.hasNext()){
						// Read in and create a single Item
						String itemName = scan.next();
						Item newItem = new Item(itemName);
						list.add(newItem);
						if ( DEBUG_MODE) { System.out.println("Item found: " + name); }
					}
				}else{
					System.out.println("Warning: Items not found. Invalid Key");
				}
			}

			// Create the player
			newPlayer = new Player(token, hitPoints);
			newPlayer.addItems(list);

			System.out.println(newPlayer.toString() + " " + newPlayer.getInventory());
			
			// Close the file Stream
			fileRead.close();

		}catch(IOException e){
			System.out.println("Loading the Player Failed\n " + e.toString());
			e.printStackTrace();
			// Load a default player
			newPlayer = new Player();
		}
		return newPlayer;
	}

	// Save the player to a file. 
	// You should catch both IOExceptions and NullPointerExceptions (from a null player) and handle them seperately
	// If the file saves correctly, return true, otherwise print a message and return false. 
	public static boolean savePlayer(Player thePlayer){
		return savePlayer(thePlayer, defaultPlayerSaveFile);
	}
	public static boolean savePlayer(Player thePlayer, String playerSaveFile){
		try{
			// Open the File Stream
			FileWriter writer = new FileWriter(path + playerSaveFile);
			PrintWriter printer = new PrintWriter(writer);
			// Print the player to the file
			printer.println("Player: " + thePlayer.toString());
			printer.println("Hitpoints: " + thePlayer.getHitPoints());
			printer.print("Items: " + thePlayer.formattedInventoryToString());

			// Close the FileWriter
			writer.close();
			return true;
		
		}catch(IOException e){
			System.out.println("Player object could not be saved\n" + e.toString());
			e.printStackTrace();
			return false;
		}catch(NullPointerException e){
			System.out.println("Player object passed to savePlayer was null\n" + e.toString());
			e.printStackTrace();
			return false;
		}

	}

}