package myadventcode;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.nio.file.*;
import java.lang.Exception;
import java.lang.Math;
import java.util.regex.*;


public class AdventOfCode {
	public static void main(String[] args) {
		String fileContent = "";
		TreeMap<Integer,String> treeMap = new TreeMap<Integer, String>();
		List<Integer> doIndexes = new ArrayList<Integer>();
		List<Integer> dontIndexes = new ArrayList<Integer>();
		List<Integer> multIndexes = new ArrayList<Integer>();

		// read input data in
		try {
			fileContent = new String(Files.readAllBytes(Paths.get("day3-input")));
		} catch(Exception e) {

		}

		// we need to find any occurences of do()
		Pattern pattern = Pattern.compile("do()");
		Matcher matcher = pattern.matcher(fileContent);

		while(matcher.find()) {
			treeMap.put(matcher.start(), "do");
			doIndexes.add(matcher.start());
		}

		// we need to find any occurences of don't()
		pattern = Pattern.compile("don't()");
		matcher = pattern.matcher(fileContent);

		while(matcher.find()) {
			treeMap.put(matcher.start(), "dont");
			dontIndexes.add(matcher.start());
		}

		// we need to make a regex to find any occurences of
		// mul(x,y) in the fileContent
		pattern = Pattern.compile("mul\\(" + "\\d+" + "," + "\\d+" + "\\)");
		matcher = pattern.matcher(fileContent);
		
		int count = 0;
		while(matcher.find()) {
			treeMap.put(matcher.start(), fileContent.substring(matcher.start(), matcher.end()));
			multIndexes.add(matcher.start());
		}

		// ok, now are treeMap should be ordered by all the ints we sorted
		// lets iterate over it and do some logic to figure out what mult
		// calls should be multiplied and summed to our count
		// do is assumed at beginning
		Boolean doIt = true;

		for (Map.Entry<Integer, String> entry: treeMap.entrySet()) {
			System.out.println("Function " + entry.getValue() + " at index " + entry.getKey());
			if(entry.getValue().equalsIgnoreCase("do")) {
				doIt = true;
			}
			
			if(entry.getValue().equalsIgnoreCase("dont")) {
				doIt = false;
			}

			if(doIt && entry.getValue().contains("mul(")) {
				String mult = entry.getValue();
				// we are on a do run so lets eval this mult
				// and add the mult to our count

				// find indexes of the ( and , and )
				int openParenIndex = mult.indexOf("(");
				int commaIndex = mult.indexOf(",");
				int closeParenIndex = mult.indexOf(")");

				System.out.println("First digit: " + mult.substring(openParenIndex+1, commaIndex));
				System.out.println("Second digit: " + mult.substring(commaIndex+1, closeParenIndex));
				
				int firstDigit = Integer.parseInt(mult.substring(openParenIndex+1, commaIndex));
				int secondDigit = Integer.parseInt(mult.substring(commaIndex+1, closeParenIndex));

				count += firstDigit*secondDigit;
			}
		}

		// spit out the sum of the differences for answer
		System.out.println("Answer for Day 3 Star 2: " + count);
	}
}

