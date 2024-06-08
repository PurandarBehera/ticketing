package com.poc.ticketing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.ticketing.entity.AppUser;
import com.poc.ticketing.repository.AppUserRepository;

@Service
public class AppUserService {
	@Autowired
	private AppUserRepository appUserRepository;

	public AppUser createUser(AppUser user) {
		return appUserRepository.save(user);
	}

	public List<AppUser> getAllUsers() {
		return appUserRepository.findAll();
	}

	public AppUser getUserById(Long id) {
		Optional<AppUser> userOpt = appUserRepository.findById(id);
		return userOpt.orElse(null);
	}

	public void deleteUser(Long id) {
		appUserRepository.deleteById(id);
	}
}
