package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Streams 
{
	public static void main(String args[]) 
	{
		//Filter
		//JAVA VERSION 7
		System.out.println("Using JAVA 7");
         List<String> lines = Arrays.asList("spring", "winter", "summar");

		 List<String> result = getFilterOutput(lines, "winter");
	        for (String temp : result) {
	            System.out.println(temp);    //output : spring, node
	        }
		
		//JAVA VERSION 8
		System.out.println("Using JAVA 8");
		List<String> result1 = lines.stream()                
                .filter(line -> !"winter".equals(line))     
                .collect(Collectors.toList());             

        result.forEach(System.out::println);   
		
	}
	
	 private static List<String> getFilterOutput(List<String> lines, String filter) {
	        List<String> result = new ArrayList<>();
	        for (String line : lines) {
	            if (!"winter".equals(line)) { 
	                result.add(line);
	            }
	        }
	        return result;
	    }
}
