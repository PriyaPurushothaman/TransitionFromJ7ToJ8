package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForEachWithCollection 
{
	public static void main(String args[])
	{
		//List
		List<String> items = new ArrayList<>();
		items.add("A");
		items.add("B");
		items.add("C");
		items.add("D");
		items.add("E");
		
		//JAVA VERSION 7
		System.out.println("Using JAVA 7");
		for(String item : items){
			System.out.println(item);
		}
		
		//JAVA VERSION 8
		System.out.println("Using JAVA 8");
		items.forEach((System.out::println));
		
		// Map
		Map<String, Integer> items1 = new HashMap<>();
		items1.put("A", 10);
		items1.put("B", 20);
		items1.put("C", 30);
		items1.put("D", 40);
		items1.put("E", 50);
		items1.put("F", 60);
		
		//JAVA VERSION 7
		System.out.println("Using JAVA 7");
		for (Map.Entry<String, Integer> entry : items1.entrySet()) {
			System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
		}
		
		//JAVA VERSION 8
		System.out.println("Using JAVA 8");
		items1.forEach((k,v)->System.out.println("Item : " + k + " Count : " + v));
		
		items1.forEach((k,v)->System.out.println(v.equals(60) ) );
		
	}	
	
}
