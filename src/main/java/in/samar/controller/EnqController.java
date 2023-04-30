package in.samar.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.samar.binding.DashBoardResponse;
import in.samar.binding.EnqForm;
import in.samar.binding.EnqSearchCriteria;
import in.samar.entity.StudentEnqEntity;
import in.samar.service.EnqService;

@Controller
public class EnqController {

	@Autowired
	private EnqService es;

	@Autowired
	private HttpSession session;

	@GetMapping("/dashboard")
	public String dashboardPage(Model m) {

		Integer uid = (Integer) session.getAttribute("userId");

		DashBoardResponse dashresp = es.dashboard(uid);

		System.out.println(dashresp);

		m.addAttribute("dash", dashresp);

		return "dashboard";
	}

	@PostMapping("/add-enquiry")
	public String addEnq(@ModelAttribute("enqform") EnqForm enqf, Model m) {

		init(m);
		if (!enqf.getStudentName().equals("") && enqf.getStudentNumber() != null && !enqf.getCourseName().equals("")
				&& !enqf.getClassMode().equals("") && !enqf.getEnqStatus().equals("")) {

			String enq = es.upsertEnq(enqf);

			if (enq == "exists") {

				m.addAttribute("exists", "Enquiry already Exists on this Mobile Number");

			} else if (enq == "saved") {

				m.addAttribute("saved", "Enquiry Saved !!");

			}
		} else {
			m.addAttribute("err", "Enter All Details");
		}

		return "add-enquiry";
	}

	@GetMapping("/add-enquiry")
	public String addEnqPage(EnqForm enqform, Model m) {

		init(m);

		m.addAttribute("enqform", new EnqForm());

		return "add-enquiry";
	}

	@GetMapping("/edit-enquiry")
	public String editEnq(@RequestParam("enqId") Integer id, EnqForm enqform, Model m) {

		System.out.println(id);

		init(m);
		EnqForm form = es.editEnq(id);

		m.addAttribute("enqform", form);

		return "add-enquiry";
	}

	private void init(Model m) {
		List<String> courses = es.course();

		List<String> status = es.enq();

		m.addAttribute("cnames", courses);
		m.addAttribute("snames", status);
	}

	@GetMapping("/view-enquiries")
	public String viewEnqPage(EnqSearchCriteria search, Model m) {

		init(m);

		Integer uid = (Integer) session.getAttribute("userId");

		List<StudentEnqEntity> list = es.getEnq(uid, new EnqSearchCriteria());

		m.addAttribute("data", list);

		return "view-enquiries";
	}

	@GetMapping("/filter")
	public String filterPage(@RequestParam String cname, @RequestParam String status, @RequestParam String classMode,Model m) {
		Integer uid = (Integer) session.getAttribute("userId");
		EnqSearchCriteria c=new EnqSearchCriteria();
		c.setCourseNames(cname);
		c.setStatusNames(status);
		c.setClassMode(classMode);
		
		List<StudentEnqEntity> filterData = es.getFilterData(c, uid);
		
		m.addAttribute("data", filterData);
		
		return "filter-enquiries";
	}

	@GetMapping("/logout")
	public String logout() {

		session.invalidate();
		return "index";
	}
}
