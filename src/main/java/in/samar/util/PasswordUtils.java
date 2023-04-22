package in.samar.util;

import org.apache.commons.lang3.RandomStringUtils;

public class PasswordUtils {

	public String getPassword() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String pwd = RandomStringUtils.random(8, characters);

		return pwd;
	}
}
