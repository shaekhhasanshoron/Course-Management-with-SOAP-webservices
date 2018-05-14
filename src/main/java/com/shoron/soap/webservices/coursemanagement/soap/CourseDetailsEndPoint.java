package com.shoron.soap.webservices.coursemanagement.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.shoron.course.GetCourseDetailsRequest;
import com.shoron.course.GetCourseDetailsResponse;
import com.shoron.course.CourseDetails;

@Endpoint
public class CourseDetailsEndPoint {
	
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
		
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(request.getId());
		courseDetails.setName("Soap Web Services Course");
		courseDetails.setDescription("This is a wonderful course");
		response.setCourseDetails(courseDetails);
		return response;
	}
}
