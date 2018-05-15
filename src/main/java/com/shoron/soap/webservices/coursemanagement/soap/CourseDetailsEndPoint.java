package com.shoron.soap.webservices.coursemanagement.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.shoron.course.GetCourseDetailsRequest;
import com.shoron.course.GetCourseDetailsResponse;
import com.shoron.soap.webservices.coursemanagement.bean.Course;
import com.shoron.soap.webservices.coursemanagement.service.CourseDetailsService;
import com.shoron.soap.webservices.coursemanagement.service.CourseDetailsService.Status;
import com.shoron.course.CourseDetails;
import com.shoron.course.DeleteCourseDetailsRequest;
import com.shoron.course.DeleteCourseDetailsResponse;
import com.shoron.course.GetAllCourseDetailsRequest;
import com.shoron.course.GetAllCourseDetailsResponse;

@Endpoint
public class CourseDetailsEndPoint {
	
	@Autowired
	CourseDetailsService service;  
	
	// input -> GetCourseDetailsRequest
	// output -> GetCourseDetailsResponse
	
	
	//have to tell spring web services to process any
	//namespace -> http://shoron.com/course
	//and
	//name -> GetCourseDetailsRequest
	@PayloadRoot(namespace="http://shoron.com/course",
			localPart="GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest
		(@RequestPayload GetCourseDetailsRequest request) {
		
		Course course = service.findById(request.getId());
		
		return mapCourseDetails(course); 		
		
	}


	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		
		response.setCourseDetails(mapCourse(course));
		
		 return response;
	}
	
	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		
		for(Course course:courses) {
			CourseDetails mapCourse = mapCourse(course);
			response.getCourseDetails().add(mapCourse);
		}
		
		 return response;
	}


	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		
		courseDetails.setId(course.getId());
		
		courseDetails.setName(course.getName());
		
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}
	
	
	@PayloadRoot(namespace="http://shoron.com/course",
			localPart="GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest
		(@RequestPayload GetAllCourseDetailsRequest request) {

	 List<Course> courses  = service.findAll();
	
	 return mapAllCourseDetails(courses);
	 
	}
	
	@PayloadRoot(namespace="http://shoron.com/course",
			localPart="DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest
		(@RequestPayload DeleteCourseDetailsRequest request) {

	 Status status  = service.deleteById(request.getId());
	
	 DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
	 response.setStatus(mapStatus(status));
	 
	 return response;
	 
	}


	private com.shoron.course.Status mapStatus(Status status) {
		// TODO Auto-generated method stub
		if(status == Status.FAILURE) {
			return com.shoron.course.Status.FAILURE;
		}
		return com.shoron.course.Status.SUCCESS;
	}
}
