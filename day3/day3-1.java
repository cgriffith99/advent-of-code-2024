package myadventcode;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.*;
import java.lang.Exception;
import java.lang.Math;
import java.util.regex.*;


public class AdventOfCode {
	public static void main(String[] args) {
		String fileContent = "";

		// read input data in
		try {
			fileContent = new String(Files.readAllBytes(Paths.get("day3-input")));
		} catch(Exception e) {

		}

		// we need to make a regex to find any occurences of
		// mul(x,y) in the fileContent
		Pattern pattern = Pattern.compile("mul\\(" + "\\d+" + "," + "\\d+" + "\\)");
		Matcher matcher = pattern.matcher(fileContent);

		System.out.println("Below are the matches: ");
		
		int count = 0;
		while(matcher.find()) {
			// extract the mult call
			String mult = fileContent.substring(matcher.start(), matcher.end());
			System.out.println(mult);
			
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

		// spit out the sum of the differences for answer
		System.out.println("Answer for Day 3 Star 1: " + count);
	}
}

