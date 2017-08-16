package Ex2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UserDataTime {
	
	/**
	 * ----------------------------------------------------------------------------
	 * this main function reads from .json file the "username" and "password" attributes
	 * of a json array that is written in the .json file
	 * and write the "username" and the hashcode of the "password" into a .text file 
	 * and prints the output
	 * ----------------------------------------------------------------------------
	 * 
	 * note that inside the .json file should be only a json array in a specific format 
	 * with "username" and "password" attributes
	 * 
	 * @author Oren Segall
	 * @version 1.0
	 * @since 08.08.2017
	 *
	 */
	private static void GetData() {
		try {

			//input from user
			//String path = getInputFromUser();
			String path = "C:\\CodeProjects\\Eclipse\\workspace\\MT_Python\\Ex2\\TestFile.json";

			//creating objects as preparation to use them ahead
			JSONParser jsonParser  = new JSONParser();
			JSONObject usersObject = new JSONObject();
			JSONArray  usersArray  = new JSONArray();

			// Parsing the .json file into a JSON Array
			JSONArray arrayFromFile = (JSONArray) jsonParser.parse(new FileReader(path));


			// passing on each object in the array and adding the "username" and "password" attributes only to WriteArr 
			// (writing the password HashCode not the password itself)
			for (int i = 0; i < arrayFromFile.size(); i++) {

				JSONObject tempObject = (JSONObject) arrayFromFile.get(i);

				// Getting the "username" and "password" from the arrayFromFile
				String username = tempObject.get("username").toString();
				int passwordHash = tempObject.get("password").toString().hashCode();

				//write the "username" and "password" hashcode inside the usersArray
				usersArray.add(username + ":" + passwordHash);

			}

			// put the crafted usersArray under the "user" Key inside the usersObject
			usersObject.put("users", usersArray);

			//write the object into a file
			try (FileWriter file = new FileWriter("users.txt")) {
				file.write(usersObject.toJSONString());
			}

			//prints the object into the screen
			System.out.println("\nJSON Object: " + usersObject);
			System.out.print("\nthe file as been saved in the project directory under the name users.txt");

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * this function gets an input from the user
	 * expecting a path of a .json file
	 * 
	 * @return a String of the user input
	 */
	private static String getInputFromUser() {

		// get file path as input from user
		System.out.print("Enter the full path of the .json file: ");
		Scanner scan = new Scanner(System.in);

		String userInput = scan.nextLine();

		scan.close();
		return userInput;
	}
	
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		GetData();
		long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("\n--- "+ elapsedTime +" miliiseconds ---");
	}

	
}