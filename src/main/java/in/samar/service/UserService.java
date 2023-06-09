package in.samar.service;

import in.samar.binding.LoginForm;
import in.samar.binding.SignUpForm;
import in.samar.binding.UnlockForm;
import in.samar.binding.resetForm;


public interface UserService {

	public String loginForm(LoginForm login);
	
	public boolean signUpForm(SignUpForm signup);
	
	public boolean unlockForm(UnlockForm unlock);
	
	public boolean checkAccountStatus(String email);
		
	public boolean forgotPassword(String email);
	
	public boolean resetPassword(resetForm form);
	
}
