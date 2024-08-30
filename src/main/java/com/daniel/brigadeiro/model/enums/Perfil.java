package com.daniel.brigadeiro.model.enums;

import lombok.Getter;

@Getter
public enum Perfil {

	ADMIN("admin"),
	USER("user");
	
	private String role;

	public String getRole() {
		return role;
	}


	private Perfil(String role) {
		this.role = role;
	}
	
	   public String toString() {
	        return this.name(); // Retorna "ADMIN" ou "USER"
	    }
}
