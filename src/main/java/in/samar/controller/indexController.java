package in.samar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

	@GetMapping("/index")
	public String indexPage() {
		
		return "index";
	}
	
	
	
	
}
