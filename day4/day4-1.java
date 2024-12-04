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
				if(charArray[y][x] == 'X') {
					// this will be the brunt of the logic here
					// we need to check all 8 directions to see if we can 
					// match X M A S
					// and we also need to keep bounds in mind as well

					// check east
					if(x < width-3) {
						if(charArray[y][x+1] == 'M' && charArray[y][x+2] == 'A' && charArray[y][x+3] == 'S') {
							count++;
						}
					}

					// check southeast
					if(x < width-3 && y < height-3) {
						if(charArray[y+1][x+1] == 'M' && charArray[y+2][x+2] == 'A' && charArray[y+3][x+3] == 'S') {
							count++;
						}
					}

					// check south
					if(y < height-3) {
						if(charArray[y+1][x] == 'M' && charArray[y+2][x] == 'A' && charArray[y+3][x] == 'S') {
							count++;
						}
					}

					// check southwest
					if(x > 2 && y < height-3) {
						if(charArray[y+1][x-1] == 'M' && charArray[y+2][x-2] == 'A' && charArray[y+3][x-3] == 'S') {
							count++;
						}
					}

					// check west
					if(x > 2) {
						if(charArray[y][x-1] == 'M' && charArray[y][x-2] == 'A' && charArray[y][x-3] == 'S') {
							count++;
						}
					}

					// check northwest
					if(x > 2 && y > 2) {
						if(charArray[y-1][x-1] == 'M' && charArray[y-2][x-2] == 'A' && charArray[y-3][x-3] == 'S') {
							count++;
						}
					}

					// check north
					if(y > 2) {
						if(charArray[y-1][x] == 'M' && charArray[y-2][x] == 'A' && charArray[y-3][x] == 'S') {
							count++;
						}
					}

					// check northeast
					if(x < width-3 && y > 2) {
						if(charArray[y-1][x+1] == 'M' && charArray[y-2][x+2] == 'A' && charArray[y-3][x+3] == 'S') {
							count++;
						}
					}

				}
			}
		}

		System.out.println("Answer for Day 4 Star 1: " + count);
	}
}