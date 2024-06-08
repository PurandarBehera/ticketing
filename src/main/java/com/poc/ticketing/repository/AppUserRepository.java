package com.poc.ticketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.ticketing.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	AppUser findByEmail(String email);
}
