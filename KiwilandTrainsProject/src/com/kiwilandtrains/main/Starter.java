package com.kiwilandtrains.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.kiwilandtrains.manager.CityGraphManager;

public class Starter {
	
	public static String INPUT_FILE_NAME = "input.txt";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// grab the input data from file and init the city graph with possible routes				
		String input = readInputFromFile();
		System.out.println(input);
		
		String[] inputArray = input.split("Graph: ");
		if(inputArray.length != 2) {
			System.out.println("faulty input: there is no such prefix \'Graph:\' ");
			return;
		} else {						
			input = inputArray[1].trim();
		}
		
		CityGraphManager.getInstance().initGraph(input);
	}
	
	public static String readInputFromFile() {
		
		String input = "";
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE_NAME));			
			String line = null;
			while((line = reader.readLine()) != null) {
				input += line;
			}
			
			return input;
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
			System.out.println("No such file: " + INPUT_FILE_NAME);
		} catch (IOException e) {			
			e.printStackTrace();
			System.out.println("io exception while reading: " + INPUT_FILE_NAME);
		}
		
		return input;
	}

}
