package com;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class ComparisionServlet extends HttpServlet {

		public static void main(String[] args) {

			List<Developer> listDevs = getDevelopers();

			System.out.println("BEFORE SORT ");
			for (Developer developer : listDevs) {
				System.out.println(developer.getName() +" "+developer.getSalary() +" "+developer.getAge());
			} 

			//SORT BY AGE
			
			//JAVA VERSION 7
		/*	Collections.sort(listDevs, new Comparator<Developer>() {
				@Override
				public int compare(Developer o1, Developer o2) {
					return o1.getAge() - o2.getAge();
				}
			});*/
			System.out.println("/********SORT BY AGE************/");


			//lambda here! //JAVA VERSION 8
			listDevs.sort((Developer o1, Developer o2)->o1.getAge()-o2.getAge());

			//java 8 only, lambda also, to print the List //JAVA VERSION 7
			listDevs.forEach((developer)->System.out.println(developer.getAge()));

			/*for (Developer developer : listDevs) {    //JAVA VERSION 7
				System.out.println(developer.getName());
			}*/
			
			System.out.println("/********SORT BY NAME************/");

			//SORT BY NAME
			Collections.sort(listDevs, new Comparator<Developer>() {
				@Override
				public int compare(Developer o1, Developer o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});

			//lambda 2 WAYS
			listDevs.sort((Developer o1, Developer o2)->o1.getName().compareTo(o2.getName()));
			listDevs.forEach((developer)->System.out.println(developer.getName()));

			//lambda
			listDevs.sort((o1, o2)->o1.getName().compareTo(o2.getName()));
//			listDevs.forEach((developer)->System.out.println(developer.getName()));

			System.out.println("/********SORT BY SALARY************/");
			
			//SORT BY SALARY
			Collections.sort(listDevs, new Comparator<Developer>() {
				@Override
				public int compare(Developer o1, Developer o2) {
					return o1.getSalary().compareTo(o2.getSalary());
				}
			});
			
			//one way
			listDevs.sort((Developer o1,Developer o2)->o1.getSalary().compareTo(o2.getSalary() ));
			listDevs.forEach((developer)->System.out.println(developer.getSalary()));
		
			//another way
			listDevs.sort((o1, o2)->o1.getSalary().compareTo(o2.getSalary()));

			
		}

		private static List<Developer> getDevelopers() {

			List<Developer> result = new ArrayList<Developer>();

			result.add(new Developer("mkyong", new BigDecimal("70000"), 33));
			result.add(new Developer("alvin", new BigDecimal("80000"), 20));
			result.add(new Developer("jason", new BigDecimal("100000"), 10));
			result.add(new Developer("iris", new BigDecimal("170000"), 55));

			return result;

		}

	}
	
