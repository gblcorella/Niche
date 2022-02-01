package com.Niche.model;

public class ResponseToken {
	private final String jwt;

	public ResponseToken(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}
}
