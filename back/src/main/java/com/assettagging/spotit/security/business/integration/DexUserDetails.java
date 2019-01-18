package com.assettagging.spotit.security.business.integration;

import com.assettagging.spotit.identity.domain.model.DexUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class DexUserDetails implements UserDetails{
	
	private DexUser user;
	private List<GrantedAuthority> grantedAuthorities;
	
	public DexUserDetails() {
	}

	public DexUserDetails(DexUser user, List<GrantedAuthority> grantedAuthorities) {
		this.user = user;
		this.grantedAuthorities = grantedAuthorities;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	public void setUser(DexUser user) {
		this.user = user;
	}

	public DexUser getUser() {
		return user;
	}
}
