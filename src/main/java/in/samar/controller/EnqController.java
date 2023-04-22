package in.samar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnqController {

	@GetMapping("/dashboard")
	public String dashboardPage() {
		
		return "dashboard";
	}
	
	
	@GetMapping("/add-enquiry")
	public String addEnqPage() {
		
		return "add-enquiry";
	}
	
	
	@GetMapping("/view-enquiries")
	public String viewEnqPage() {
		
		return "view-enquiries";
	}
}
