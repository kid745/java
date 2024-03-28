package com.devcamp.thongnh.realestate.security;

import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetails;

	public class UserPrincipal implements UserDetails {
		private Long userId;
		private String username;
		private String password;
		private Collection authorities;
		

		@Override
		public boolean isAccountNonExpired() {
			return false;
		}

		@Override
		public boolean isAccountNonLocked() {
			return false;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return false;
		}

		@Override
		public boolean isEnabled() {
			return false;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		@Override
		public String getUsername() {
			return username;
		}

		@Override
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		@Override
		public Collection getAuthorities() {
			return authorities;
		}

		public void setAuthorities(Collection authorities) {
			this.authorities = authorities;
		}

	}
