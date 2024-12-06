package myadventcode.day4;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.*;
import java.lang.Exception;
import java.lang.Math;


public class AdventOfCode {
	public static Integer width = 0;
	public static Integer height = 0;
	public static char[][] mapArray;
	public static int count = 0;
	public static char guard;
	public static int guardX = 0;
	public static int guardY = 0;
	public static String guardDir = "";
	public static boolean exitedMap = false;

	public static void main(String[] args) {
		List<String> lines = new ArrayList<String>();
		Map<String, String> visitedSpots = new HashMap<String, String>();

		// read input data in
		try {
			lines = Files.readAllLines(Paths.get("day6-input1"));
		} catch(Exception e) {

		}

		// iterate over and split the strings into a 2d array for ease of logic later
		
		width = lines.get(0).length();
		height = lines.size();
		mapArray = new char[height][width];

		for(var y=0;y<lines.size();y++) {
			// iterate over this line char by char and set the values of the line array
			char[] lineChars = lines.get(y).toCharArray();

			for(int x=0;x<lineChars.length;x++) {
				mapArray[y][x] = lineChars[x];
			}
		}

		// ok now we have our char array built, lets iterate over the whole thing to make sure
		// we are just finding the guard this first time
		for(int y=0;y<mapArray.length;y++) {
			for(int x=0;x<mapArray[y].length;x++) {
				String dir = getGuardDirection(x, y);

				if(dir != null) {
					guard = mapArray[y][x];
					guardX = x;
					guardY = y;
					guardDir = dir;

					break;
				}
			}
		}

		// ok we have found the guard
		// lets loop and checkCollision and move

		System.out.println("Guard: " + guard);
		System.out.println("Guard at: " + guardX + " " + guardY);
		System.out.println("Guard direction: " + guardDir);

		visitedSpots.put(guardX+","+guardY, "visited");

		while(moveGuard(guardX, guardY, guardDir) && !exitedMap) {
			System.out.println("Guard: " + guard);
			System.out.println("Guard at: " + guardX + " " + guardY);
			System.out.println("Guard direction: " + guardDir);
			visitedSpots.put(guardX+","+guardY, "visited");
		}



		System.out.println("Answer for Day 6 Star 1: " + visitedSpots.size());
	}

	private static String getGuardDirection(int guardX, int guardY) {
		char guard = mapArray[guardY][guardX];

		if(guard == '^') {
			return "north";
		}
		if(guard == '>') {
			return "east";
		}
		if(guard == 'v') {
			return "south";
		}
		if(guard == '<') {
			return "west";
		}

		return null;
	}

	private static boolean checkCollisionNextSpace(int x, int y, String dir) {
		if(dir == "north") {
			// check bounds
			if(y<1) {
				return false;
			}

			System.out.println("Next spot: " + mapArray[y-1][x]);
			if(mapArray[y-1][x] == '#') {
				return true;
			} else {
				return false;
			}
		}

		if(dir == "east") {
			// check bounds
			if(x<width-1) {
				return false;
			}

			System.out.println("Next spot: " + mapArray[y][x+1]);
			if(mapArray[y][x+1] == '#') {
				return true;
			} else {
				return false;
			}
		}

		if(dir == "south") {
			// check bounds
			if(y<height-1) {
				return false;
			}

			System.out.println("Next spot: " + mapArray[y+1][x]);
			if(mapArray[y+1][x] == '#') {
				return true;
			} else {
				return false;
			}
		}

		if(dir == "west") {
			// check bounds
			if(x<1) {
				return false;
			}

			System.out.println("Next spot: " + mapArray[y][x-1]);
			if(mapArray[y][x-1] == '#') {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	private static void turnRight() {
		if(guard == '^') {
			guard = '>';
			guardDir = "east";
			System.out.println("Now facing: " + guardDir);
			return;
		}
		if(guard == '>') {
			guard = 'v';
			guardDir = "south";
			System.out.println("Now facing: " + guardDir);
			return;
		}
		if(guard == 'v') {
			guard = '<';
			guardDir = "west";
			System.out.println("Now facing: " + guardDir);
			return;
		}
		if(guard == '<') {
			guard = '^';
			guardDir = "north";
			System.out.println("Now facing: " + guardDir);
			return;
		}

		return;
	}

	private static boolean moveGuard(int x, int y, String dir) {
		if(dir == "north") {
			// check bounds
			if(y<1) {
				exitedMap = true;
				return true;
			}

			if(mapArray[y-1][x] == '.') {
				guardY -= 1;
				return true;
			} if(mapArray[y-1][x] == '#') {
				turnRight();
				return true;
			} else {
				return false;
			}
		}

		if(dir == "east") {
			// check bounds
			if(x==width-1) {
				exitedMap = true;
				return true;
			}

			if(mapArray[y][x+1] == '.') {
				guardX += 1;
				return true;
			} if(mapArray[y][x+1] == '#') {
				turnRight();
				return true;
			} else {
				return false;
			}
		}

		if(dir == "south") {
			// check bounds
			if(y==height-1) {
				exitedMap = true;
				return true;
			}

			if(mapArray[y+1][x] == '.') {
				guardY += 1;
				return true;
			} if(mapArray[y+1][x] == '#') {
				turnRight();
				return true;
			} else {
				return false;
			}
		}

		if(dir == "west") {
			// check bounds
			if(x<1) {
				exitedMap = true;
				return true;
			}

			if(mapArray[y][x-1] == '.') {
				guardX -= 1;
				return true;
			} if(mapArray[y][x-1] == '#') {
				turnRight();
				return true;
			} else {
				return false;
			}
		}

		return false;
	}
}