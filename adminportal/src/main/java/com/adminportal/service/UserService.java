package com.adminportal.service;

import java.util.Set;

import com.adminportal.domain.User;
import com.adminportal.domain.security.UserRole;

public interface UserService {
	
	User createUser(User user, Set<UserRole> userRoles);
	User save(User user);
}
