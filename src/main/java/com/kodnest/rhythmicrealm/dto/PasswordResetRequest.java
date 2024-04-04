package com.kodnest.rhythmicrealm.dto;

public class PasswordResetRequest {
	String emailOrUsername;
    String newPassword;
    String confirmPassword;
	public PasswordResetRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PasswordResetRequest(String emailOrUsername, String newPassword, String confirmPassword) {
		super();
		this.emailOrUsername = emailOrUsername;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}
	public String getEmailOrUsername() {
		return emailOrUsername;
	}
	public void setEmailOrUsername(String emailOrUsername) {
		this.emailOrUsername = emailOrUsername;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	@Override
	public String toString() {
		return "PasswordResetRequest [emailOrUsername=" + emailOrUsername + ", newPassword=" + newPassword
				+ ", confirmPassword=" + confirmPassword + "]";
	}
    
    

}
