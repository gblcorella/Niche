package com.Niche.util;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Niche.model.User;
import com.Niche.repository.UserRepository;

@Service
public class NicheUserDetailsService implements UserDetailsService {
	UserRepository userRepository;

	public NicheUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.userRepository.findByUsername(username)
				.map(BridgeUser::new)
				.orElseThrow(() -> new UsernameNotFoundException(username));
	}
	
	private static class BridgeUser extends User implements UserDetails {
		private static final long serialVersionUID = 1L;

		public BridgeUser(User user) {
			super(user);
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return super.getUserAuthorities().stream()
					.map(userAuthority -> new SimpleGrantedAuthority(userAuthority.getAuthority()))
					.collect(Collectors.toList());
					
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
			return super.isEnabled();
		}
	}
}
