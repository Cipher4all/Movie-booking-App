package com.abhishek.MovieBooking.Services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.abhishek.MovieBooking.Model.User;
import com.abhishek.MovieBooking.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component("userDetailsService")
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalAccount = userRepository.findByUsername(username);
		if(!optionalAccount.isPresent()) {
			throw new UsernameNotFoundException("Account not found");
		}
		
		User account = optionalAccount.get();
		Set<GrantedAuthority> grantedAuthorities = account
				.getRoles()
				.stream()
				.map((role) ->  new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toSet());
		
		return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getPassword(),  grantedAuthorities);
	}
	
	

}
