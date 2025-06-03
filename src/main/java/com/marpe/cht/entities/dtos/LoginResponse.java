package com.marpe.cht.entities.dtos;

import java.util.Objects;

import com.marpe.cht.entities.enums.Role;

public class LoginResponse {

	private Long id;
    private String token;
    private String username;
    private Role role;
    
	public LoginResponse(Long id, String token, String username, Role role) {
		this.id = id;
		this.token = token;
		this.username = username;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public String getToken() {
		return token;
	}

	public String getUsername() {
		return username;
	}

	public Role getRole() {
		return role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginResponse other = (LoginResponse) obj;
		return Objects.equals(id, other.id);
	}

}
