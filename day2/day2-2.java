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
		List<Integer> numbers = new ArrayList<Integer>();
		List<Boolean> statuses = new ArrayList<Boolean>();
		List<String> lineStatuses = new ArrayList<String>();

		// read input data in
		try {
			lines = Files.readAllLines(Paths.get("day2-input"));
		} catch(Exception e) {

		}
		
		// iterate over and split the lines out and add numbers to lists
		for(String l: lines) {
			String[] line = l.split(" ");
			numbers = new ArrayList<Integer>();

			// lets go ahead and convert these to numbers to make it easier to test things below
			for(int i=0;i<line.length;i++) {
				numbers.add(Integer.parseInt(line[i]));
			}

			// if it is safe we can add it it now, unsafe needs more checking
			if(checkListSafety(numbers)) {
				statuses.add(true);
			} else {
				// we need to test every other list of numbers with one removed from it to check safety
				int posCounter = 0;
				Boolean subSafe = false;

				for(int i=0;i<numbers.size();i++) {
					List<Integer> subListNumbers = new ArrayList<Integer>();
					subListNumbers.addAll(numbers);

					// now we remove the current positions number we are at and check for safety
					subListNumbers.remove(i);

					if(checkListSafety(subListNumbers)) {
						subSafe = true;
						break;
					}
				}

				statuses.add(subSafe);
			}
			
			//lineStatuses.add(lineStatus);
		}

		// iterate over and count how many safe statuses we have
		int safeInstances = 0; 

		for(boolean s: statuses) {
			if(s) {
				safeInstances++;
			}
		}

		/*for(String s: lineStatuses) {
			System.out.println(s);
		}*/

		// spit out the sum of the differences for answer
		System.out.println("Answer for Day 2 Star 1: " + safeInstances);
	}

	private static Boolean checkListSafety(List<Integer> numbers) {
		// keep track of asc vs desc check
		String order = "";
		Boolean safe = true;
		String lineStatus = "";
		
		// lets iterate over and figure out if this sequence is safe or not
		// safe means it either continues to decrease or increase from left to right
		// and also each difference between numbers is at least one and at most
		// NOTE we are going to evaluate these all over again while looking an extra number ahead to see if
		// it makes the sequence safe at that point
		for(int i=0;i<numbers.size();i++) {
			// check to see if we are at end of array
			if(i == numbers.size()-1) {
				break;
			}

			// only check asc or desc on first iteration
			if(i == 0) {
				if (numbers.get(i) < numbers.get(i+1)) {
					order = "asc";
				} else {
					order = "desc";
				}
			}

			// check difference in numbers
			Integer difference = Math.abs(numbers.get(i)-numbers.get(i+1));

			if(difference >= 1 && difference <= 3) {
				// we need to also make sure that asc or desc matches initial ordering: order
				String thisOrder = "";

				if(numbers.get(i) < numbers.get(i+1)) {
					thisOrder = "asc";
				} else {
					thisOrder = "desc";
				}

				if(order.equalsIgnoreCase(thisOrder)) {
					lineStatus = "Safe";
					
				} else {
					lineStatus = "Not gradually " + order;
					return false;
				}
			} else {
				// difference is too large, mark unsafe
				lineStatus = "Difference too large";
				return false;
			}
		}

		return true;
	}
}

