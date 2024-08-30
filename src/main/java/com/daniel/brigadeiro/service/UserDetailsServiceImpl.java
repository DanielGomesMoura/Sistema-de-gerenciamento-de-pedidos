package com.daniel.brigadeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.daniel.brigadeiro.repository.AdminRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return adminRepository.findByEmail(email);
	}

}
