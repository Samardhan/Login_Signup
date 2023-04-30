package in.samar.serviceImpl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.samar.binding.LoginForm;
import in.samar.binding.SignUpForm;
import in.samar.binding.UnlockForm;
import in.samar.binding.resetForm;
import in.samar.entity.UsersDetailsEntity;
import in.samar.repo.UserDetailsRepo;
import in.samar.service.UserService;
import in.samar.util.EmailUtils;
import in.samar.util.PasswordUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDetailsRepo urepo;

	@Autowired
	private EmailUtils send;

	@Autowired
	private HttpSession session;

	@Override
	public String loginForm(LoginForm login) {

		String op = null;

		UsersDetailsEntity log = urepo.findByUserEmail(login.getUserEmail());

		if (log != null) {

			if (login.getUserEmail().equals(log.getUserEmail())) {

				boolean status = checkAccountStatus(login.getUserEmail());

				if (status) {
					if (login.getUserPassword().equals(log.getUserPassword())) {

						session.setAttribute("userId", log.getUserId());
						System.out.println(log.getUserId());
						op = "success";

					} else {
						op = "Enter Correct Password";
					}
				} else {
					op = "Account is Locked..... UNLOCK before Login";
				}

			} else {
				op = "Enter Correct Email";
			}

		} else {
			op = "No  Account Found on this Email";
		}
		return op;

	}

	@Override
	public boolean signUpForm(SignUpForm signup) {

		UsersDetailsEntity email = urepo.findByUserEmail(signup.getUserEmail());

		if (email != null) {
			return false;
		}

		UsersDetailsEntity ude = new UsersDetailsEntity();
		BeanUtils.copyProperties(signup, ude);

		// password
		PasswordUtils pwd = new PasswordUtils();
		String password = pwd.getPassword();
		ude.setUserPassword(password);
		System.out.println(pwd.toString());

		ude.setAccountStatus("LOCKED");

		urepo.save(ude);
		// email

		String to = signup.getUserEmail();

		String subject = "Unlock your Account";
		StringBuffer body = new StringBuffer();
		body.append("<h3>your temporary password is :</h3>" + "<h1>" + password + "</h1>" + "<br>");
		body.append("<a href=\"http://localhost:8080/unlock?email=" + to + "\"> Unlock Account</a>");
		send.sendmail(to, subject, body.toString());

		return true;
	}

	@Override
	public boolean unlockForm(UnlockForm unlock) {

		UsersDetailsEntity ude = urepo.findByUserEmail(unlock.getUserEmail());

		if (unlock.getTempPassword().equals(ude.getUserPassword())) {

			ude.setAccountStatus("UNLOCKED");
			ude.setUserPassword(unlock.getConfirmPassword());
			urepo.save(ude);

			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean checkAccountStatus(String email) {

		UsersDetailsEntity ude = urepo.findByUserEmail(email);

		if (ude != null && !ude.equals(" ")) {
			if (ude.getAccountStatus().equals("UNLOCKED")) {

				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	@Override
	public boolean forgotPassword(String email) {

		UsersDetailsEntity ude = urepo.findByUserEmail(email);

		if (ude != null) {

			String to = email;

			String subject = "Your Account Password";

			StringBuffer body = new StringBuffer();

			body.append("<h1> password : </h1> <h2>" + ude.getUserPassword() + "<h2> <br>");

			body.append(
					"<h4> You Can also reset Your Password here </h4> <a href=\"http://localhost:8080/resetPwd?email="
							+ to + "\">Reset Password</a>");

			send.sendmail(to, subject, body.toString());

			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean resetPassword(resetForm rf) {

		UsersDetailsEntity ude = urepo.findByUserEmail(rf.getUserEmail());

		if (ude != null) {

			if (rf.getOldPassword().equals(ude.getUserPassword())) {

				ude.setUserPassword(rf.getConfirmPassword());

				urepo.save(ude);

				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}

	}

}
