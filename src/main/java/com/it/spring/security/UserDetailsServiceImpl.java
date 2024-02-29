package com.it.spring.security;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.it.repository.SignupRepository;
import com.it.repository.entity.Signup;


@Service
public class UserDetailsServiceImpl implements UserDetailsService 
{
	/**
	 * Who will call this method ???? spring security
	 * UserDetailsService is coming from spring security
	 */
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SignupRepository signupRespository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{	// loadUserByUsername is a standard method defined under UserDetailsService
		if ("jack@gmail.com".equals(username)) 
		{
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ADMIN"));
			return new User(username,passwordEncoder.encode("jill"),authorities);
		} 
		Optional<Signup> optional=signupRespository.findByEmail(username);
		if(optional.isPresent()) 
		{
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(optional.get().getRole()));
			return new User(username,optional.get().getPassword(),authorities);
		}
		
		//else 
		//{
			throw new UsernameNotFoundException("User Not Found with username: " + username);
		//}
		// return UserDetailsImpl.build(signup);
	}
}