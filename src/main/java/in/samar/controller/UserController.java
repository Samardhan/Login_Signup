package in.samar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.samar.binding.LoginForm;
import in.samar.binding.SignUpForm;
import in.samar.binding.UnlockForm;
import in.samar.binding.resetForm;
import in.samar.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService us;

	// LOGIN

	@PostMapping("/login")
	public String loginSuc(@ModelAttribute("login") LoginForm lform, Model m) {

		String go = "login";

		if (!lform.getUserEmail().equals("") && !lform.getUserPassword().equals("")) {

			
			String loginstatus = us.loginForm(lform);

			if (loginstatus.contains("success")) {

				go = "redirect:/dashboard";

			} else {

				m.addAttribute("msg", loginstatus);

			}

		} else {
			// go = "login";
			m.addAttribute("empty", "Email and Password should not be Empty");
		}

		return go;
	}

	@GetMapping("/login")
	public String loginPage(Model model) {

		model.addAttribute("login", new LoginForm());

		return "login";
	}

	// SIGNUP

	@PostMapping("/signup")
	public String getSignup(@ModelAttribute("signup") SignUpForm form, Model model) {

		if (!form.getUserEmail().equals("") && !form.getUserName().equals("") && form.getUserPhone() != null) {
			boolean status = us.signUpForm(form);

			if (status) {
				model.addAttribute("success", " Check Your Email");
			} else {
				model.addAttribute("fail", "Account Already Exists on this Email");
			}

		} else {

			model.addAttribute("empty", "Enter All Details to SignUp");

		}
		return "signup";

	}

	@GetMapping("/signup")
	public String signupPage(Model model) {

		model.addAttribute("signup", new SignUpForm());

		return "signup";
	}

	// FORGOT PASSWORD

	@PostMapping("/forgotPwd")
	public String forgotPage(@ModelAttribute("email") String email, Model m) {

		if (!email.equals("")) {
			boolean fp = us.forgotPassword(email);

			if (fp) {

				m.addAttribute("sent", "Password Sent to Your Email");
			} else {
				m.addAttribute("wrong", "Enter Correct Email");
			}
		} else {
			m.addAttribute("empty", "Enter your Email");
		}

		return "forgotPwd";
	}

	@GetMapping("/forgotPwd")
	public String forgotpwdPage() {

		// m.addAttribute("email", new forgotPwd());

		return "forgotPwd";
	}

	// UNLOCK

	@PostMapping("/unlock")
	public String unlockAcc(@ModelAttribute("unlock") UnlockForm unForm, Model model) {

		if (!unForm.getTempPassword().equals("") && !unForm.getNewPassword().equals("")
				&& !unForm.getConfirmPassword().equals("")) {

			boolean accountStatus = us.checkAccountStatus(unForm.getUserEmail());

			if (!accountStatus) {

				if (unForm.getNewPassword().equals(unForm.getConfirmPassword())) {

					boolean status = us.unlockForm(unForm);

					if (status) {
						model.addAttribute("sucmsg", "Account Unlocked .... Login Now");
					} else {
						model.addAttribute("temperr", "Enter Temporary Password Correctly");
					}

				} else {
					model.addAttribute("pwderror", "Entered Passwords are Not matching");
				}

			} else {
				model.addAttribute("exists", "Account Already Unlocked Please login");
			}
		} else {
			model.addAttribute("empty", "Enter All Details");
		}

		return "unlock";
	}

	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email, Model model) {

		UnlockForm unForm = new UnlockForm();
		unForm.setUserEmail(email);

		model.addAttribute("unlock", unForm);
		// model.addAttribute("userEmail", unForm.getUserEmail());

		return "unlock";
	}

	// RESET PASSWORD

	@PostMapping("/resetPwd")
	public String resetPwdSucc(@ModelAttribute("resetpwd") resetForm resetPwd, Model m) {

		if (!resetPwd.getOldPassword().equals("") && !resetPwd.getNewPassword().equals("")
				&& resetPwd.getNewPassword() != null && !resetPwd.getConfirmPassword().equals("")
				&& resetPwd.getConfirmPassword() != null) {

			if (resetPwd.getNewPassword().equals(resetPwd.getConfirmPassword())) {
				boolean rp = us.resetPassword(resetPwd);

				if (rp) {
					m.addAttribute("changed", "Password Changed Successfully");
				} else {
					m.addAttribute("err", "Please Enter Correct Old Password");
				}
			} else {
				m.addAttribute("nomatch", "Passwords are Not Matching");
			}

		} else {
			m.addAttribute("empty", "Enter All Details");
		}

		return "resetPwd";
	}

	@GetMapping("/resetPwd")
	public String resetPassword(@RequestParam String email, Model m) {

		resetForm rf = new resetForm();
		rf.setUserEmail(email);

		m.addAttribute("resetpwd", rf);

		return "resetPwd";
	}
}
