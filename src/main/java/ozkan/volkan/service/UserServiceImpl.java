package ozkan.volkan.service;

import ozkan.volkan.dao.UserDAO;
import ozkan.volkan.model.Users;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public void addUser(Users user) {
		userDAO.addUser(user);
	}

	@Override
	@Transactional
	public List<Users> getAllUsers() {
		return userDAO.getAllUsers();
	}

	public boolean usernameAlreadyExists(String username) {
		return userDAO.usernameAlreadyExists(username);
	}

	public boolean emailAlreadyExists(String email) {
		return userDAO.emailAlreadyExists(email);
	}

	@Override
	public Users getUserByUsername(String username) throws UsernameNotFoundException {
		return userDAO.getUserByUsername(username);
	}
	
}
