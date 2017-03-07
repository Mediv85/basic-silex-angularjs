package it.mediv.controller.adapter;

public class AuthResp {
	private Boolean success;
	private String token;
	private String username;

	@Override
	public String toString() {
		return "AuthResp [success=" + success + ", token=" + token + ", username=" + username + "]";
	}

	public AuthResp() {
		super();
	}

	public AuthResp(Boolean success, String token, String username) {
		super();
		this.success = success;
		this.token = token;
		this.username = username;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
