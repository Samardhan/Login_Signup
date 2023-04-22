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
import in.samar.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService us;

	@PostMapping("/login")
	public String loginSuc(@ModelAttribute("login") LoginForm lform, Model m) {

		String go = "login";
		if (lform != null ) {
			if (lform.getUserEmail() != null && !lform.getUserEmail().equals(" ")) {

				if (lform.getUserPassword() != null && !lform.getUserPassword().equals(" ")) {

					String loginstatus = us.loginForm(lform);

					if (loginstatus == "success") {

						boolean accStatus = us.checkAccountStatus(lform.getUserEmail());

						if (accStatus) {
							go = "dashboard";

						} else {
							go = "login";
							m.addAttribute("accLock", "Account is Locked.... Unlock Now");
						}

					} else if (loginstatus == "wrongPassword") {
						go = "login";
						m.addAttribute("wrongPassword", "Enter Correct Password");
					}else if(loginstatus == "empty") {
						
						go="login";
						m.addAttribute("noemail", "Account Does not Exists...Signup before Login");
						
					}
					else {
						go = "login";
						m.addAttribute("wrongEmail", "Enter Correct Email");
					}
				} else {
					go = "login";
					m.addAttribute("emptypassword", "Password Should not be Empty");
				}

			} else {
				go = "login";
				m.addAttribute("emptyEmail", "Email should not be Empty");
			}

		} else {

			go = "login";
			m.addAttribute("fill", "Enter All Details to Login");

		}

		return go;
	}

	@GetMapping("/login")
	public String loginPage(Model model) {

		model.addAttribute("login", new LoginForm());

		return "login";
	}

	@PostMapping("/signup")
	public String getSignup(@ModelAttribute("signup") SignUpForm form, Model model) {

		boolean status = us.signUpForm(form);

		if (status) {
			model.addAttribute("success", " Check Your Email");
		} else {
			model.addAttribute("fail", "Account Already Exists on this Email");
		}

		return "signup";
	}

	@GetMapping("/signup")
	public String signupPage(Model model) {

		model.addAttribute("signup", new SignUpForm());

		return "signup";
	}

	@GetMapping("/forgotPwd")
	public String forgotpwdPage() {

		return "forgotPwd";
	}

	@PostMapping("/unlock")
	public String unlockAcc(@ModelAttribute("unlock") UnlockForm unForm, Model model) {

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
}
