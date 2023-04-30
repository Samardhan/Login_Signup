package in.samar.binding;

import lombok.Data;

@Data
public class resetForm {

	private String userEmail;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
}
