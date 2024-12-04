package myadventcode.day4;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.*;
import java.lang.Exception;
import java.lang.Math;


public class AdventOfCode {
	public static void main(String[] args) {
		List<String> lines = new ArrayList<String>();

		// read input data in
		try {
			lines = Files.readAllLines(Paths.get("day4-input"));
		} catch(Exception e) {

		}
		
		// iterate over and split the strings into a 2d array for ease of logic later
		char[][] charArray;
		Integer width = lines.get(0).length();
		Integer height = lines.size();
		charArray = new char[height][width];

		for(var y=0;y<lines.size();y++) {
			// iterate over this line char by char and set the values of the line array
			char[] lineChars = lines.get(y).toCharArray();

			for(int x=0;x<lineChars.length;x++) {
				charArray[y][x] = lineChars[x];
			}
		}

		// ok now we have our char array built, lets iterate over the whole thing to make sure
		int count = 0;

		for(int y=0;y<charArray.length;y++) {
			for(int x=0;x<charArray[y].length;x++) {
				// changes from part 1
				// we are looking for two MAS that make an X now
				// so essentially we need to find an A
				// check bounds
				// then see if it has an M and S diaganol one way
				// and M and S diagonol the opposite way
				// fun fun fun
				if(charArray[y][x] == 'A') {
					if(x > 0 && x < width-1 && y > 0 && y < height-1) {
						// we are safe on bounding, lets check for the crosses
						if((charArray[y-1][x-1] == 'M' && charArray[y+1][x+1] == 'S') || (charArray[y-1][x-1] == 'S' && charArray[y+1][x+1] == 'M')) {
							if((charArray[y+1][x-1] == 'M' && charArray[y-1][x+1] == 'S') || (charArray[y+1][x-1] == 'S' && charArray[y-1][x+1] == 'M')) {
								count++;
							}
						}
					}

				}
			}
		}

		System.out.println("Answer for Day 4 Star 2: " + count);
	}
}