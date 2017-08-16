package Ex2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UserDataTimeToFile {

	private static final int EXEC_NUM = 50;

	/**
	 * ----------------------------------------------------------------------------
	 * this function reads from .json file the "username" and "password" attributes
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
			//System.out.println("\nJSON Object: " + usersObject);
			//System.out.print("\nthe file as been saved in the project directory under the name users.txt");

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}	
	}
	
	
	
	private static void WriteTimeToFile() {

		PrintWriter outPut = null;
		
		try {
		
			outPut = new PrintWriter(new File("C:\\CodeProjects\\Eclipse\\workspace\\MT_Python\\Ex2\\tempJava.txt"));
			
			for (int i = 0; i < EXEC_NUM; i++) {
				long startTime = System.currentTimeMillis();
				GetData();		
				long stopTime = System.currentTimeMillis();
				long elapsedTime = stopTime - startTime;
				//System.out.println("\n--- "+ (elapsedTime) +" miliiseconds ---");
				outPut.println("" + elapsedTime);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			outPut.close();
		}
        
        
	}
	
	
	public static void main(String[] args) {
		WriteTimeToFile();		
		
	}

	
}
