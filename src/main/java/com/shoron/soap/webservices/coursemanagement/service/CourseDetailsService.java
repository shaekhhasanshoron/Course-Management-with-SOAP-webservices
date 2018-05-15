package com.shoron.soap.webservices.coursemanagement.service;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.shoron.soap.webservices.coursemanagement.bean.Course;

@Component
public class CourseDetailsService {

	private static List<Course> courses = new ArrayList<>();
	
	static {
		Course course1= new Course(1, "Spring", "Basics of Spring Framework");
		courses.add(course1);
		
		Course course2= new Course(2, "Spring Boot", "Basics of Spring Boot");
		courses.add(course2);
		
		Course course3= new Course(3, "Spring MVC", "Basics of Spring MVC");
		courses.add(course3);
		
		Course course4= new Course(4, "JPA", "Basics of JPA");
		courses.add(course4);
	}
	// course - 1
	// Course findById(int id)
	
	public Course findById(int id) {
		for(Course course:courses) {
			if(course.getId() == id) {
				return course;
			}
		}
		return null;
	}
	
	//courses
	// List<Course> findAll
	public  List<Course> findAll(){
		return courses;
	}
	
	//deleteCourse
	public int deleteById(int id) {
		
		Iterator<Course> iterator = courses.iterator();
		while(iterator.hasNext()) {
			Course course = iterator.next();
			if(course.getId() == id) {
				iterator.remove();
				return 1;
			}
		}
		return 0;
	}
	
	//update course and add
}
