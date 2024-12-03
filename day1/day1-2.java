package myadventcode;

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
			lines = Files.readAllLines(Paths.get("day1-input"));
		} catch(Exception e) {

		}
		
		// setup lists to hold the numbers
		List<Integer> firstList = new ArrayList<Integer>();
		List<Integer> secondList = new ArrayList<Integer>();

		// iterate over and split the lines out and add numbers to lists
		for(String l: lines) {
			String[] numbers = l.split("   ");
			firstList.add(Integer.parseInt(numbers[0]));
			secondList.add(Integer.parseInt(numbers[1]));
		}

		// sort lists in ascending order
		Collections.sort(firstList);
		Collections.sort(secondList);

		int similarityScore = 0;

		// iterate over first list while checking second list each time for similarities
		for(int i: firstList) {
			int matches = 0;

			for(int j: secondList) {
				if(i == j) {
					matches += 1;
				}
			}

			similarityScore += i*matches;
		}

		// spit out the sum of the differences for answer
		System.out.println("Answer for Day 1 Star 2: " + similarityScore);
	}
}

