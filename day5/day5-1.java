package myadventcode.day4;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.*;
import java.lang.Exception;
import java.lang.Math;


public class AdventOfCode {
	public static void main(String[] args) {
		List<String> rules = new ArrayList<String>();
		List<String> lines = new ArrayList<String>();

		// read input data in
		try {
			rules = Files.readAllLines(Paths.get("day5-input1"));
			lines = Files.readAllLines(Paths.get("day5-input2"));
		} catch(Exception e) {

		}

		// need to make lists of ints out of the lines
		List<List<Integer>> intLines = new ArrayList<List<Integer>>();

		for(String l: lines) {
			List<Integer> intLine = new ArrayList<Integer>();
			String[] lineArray = l.split(",");

			for(int i=0;i<lineArray.length;i++) {
				if(lineArray[i] != "") {
					intLine.add(Integer.parseInt(lineArray[i]));
				}
			}

			intLines.add(intLine);
		}

		int count = 0;

		// so now we have lists of Integers
		// lets iterate over and find what lines are valid or not
		for(List<Integer> oneLine: intLines) {
			// gotta loop through all the rules here to apply
			boolean lineValid = true;

			for(String rule: rules) {
				String[] ruleNums = rule.split("\\|");

				if(!isRuleValid(oneLine, Integer.parseInt(ruleNums[0]), Integer.parseInt(ruleNums[1]))) {
					lineValid = false;
				}
			}

			if(lineValid) {
				count += findMiddleNumber(oneLine);
			}
			
		}

		System.out.println("Answer for Day 5 Star 1: " + count);
	}

	public static boolean isRuleValid(List<Integer> nums, int num1, int num2) {
		boolean valid = true;

		// first see if list even contains the first number in rule
		// if not then it is valid by default
		if(!nums.contains(num1)) {
			return true;
		}

		// this checks to make sure rules are valid left to right
		// now iterate over and lets see
		boolean foundFirst = false, foundSecond = false;

		for(int i=0;i<nums.size();i++) {
			if(nums.get(i) == num1) {
				foundFirst = true;
			}

			if(foundFirst && nums.get(i) == num2) {
				foundSecond = true;
			}
		}

		if(foundFirst && foundSecond) {
			valid = true;
		}

		// this checks to make sure rules are valid right to left
		// now iterate over and lets see
		foundFirst = false;
		foundSecond = false;

		for(int i=0;i<nums.size();i++) {
			if(nums.get(i) == num2) {
				foundSecond = true;
			}

			if(foundSecond && nums.get(i) == num1) {
				foundFirst = true;
			}
		}

		if(foundFirst && foundSecond) {
			valid = false;
		}

		return valid;
	}

	public static Integer findMiddleNumber(List<Integer> nums) {
		return nums.get((nums.size()/2));
	}
}