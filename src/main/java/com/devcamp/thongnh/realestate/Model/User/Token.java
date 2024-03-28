package com.devcamp.thongnh.realestate.Model.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "token")

public class Token extends BaseEntity {

	@Column(name="token",length = 1000)
	private String token;


	@Column(name="token_exp_date")
	private Date tokenExpDate;

	public Token() {

	}

	public Token(long id, boolean deleted, Date createdAt, Date updatedAt, Long createdBy, Long updatedBy, String token,
			Date tokenExpDate) {
		super(id, deleted, createdAt, updatedAt, createdBy, updatedBy);
		this.token = token;
		this.tokenExpDate = tokenExpDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenExpDate() {
		return tokenExpDate;
	}

	public void setTokenExpDate(Date tokenExpDate) {
		this.tokenExpDate = tokenExpDate;
	}

	

}
