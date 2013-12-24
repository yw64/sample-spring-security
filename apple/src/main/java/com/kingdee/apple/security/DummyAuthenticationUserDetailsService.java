package com.kingdee.apple.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DummyAuthenticationUserDetailsService<T extends Authentication> implements AuthenticationUserDetailsService<T> {

	@Override
	public UserDetails loadUserDetails(final T token) throws UsernameNotFoundException {
		return new UserDetails() {

			private static final long serialVersionUID = 1L;

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				GrantedAuthority authority = new GrantedAuthority() {

					private static final long serialVersionUID = 1L;

					@Override
					public String getAuthority() {
						return "ROLE_USER";
					}

				};

				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(authority);
				return authorities;
			}

			@Override
			public String getPassword() {
				return null;
			}

			@Override
			public String getUsername() {
				return token.getName();
			}

			@Override
			public boolean isAccountNonExpired() {
				return true;
			}

			@Override
			public boolean isAccountNonLocked() {
				return true;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}

			@Override
			public boolean isEnabled() {
				return true;
			}

		};
	}

}
