package ozkan.volkan.service;

import ozkan.volkan.model.Users;
import java.util.List;

public interface UserService {

	public void addUser(Users user);

	public List<Users> getAllUsers();
	
	public Users getUserByUsername(String username);

	public boolean usernameAlreadyExists(String username);

	public boolean emailAlreadyExists(String email);

}
