package ozkan.volkan.dao;

import ozkan.volkan.model.*;
import java.util.List;

public interface UserDAO {

	public void addUser(Users user);

	public List<Users> getAllUsers();
	
	public Users getUserByUsername(String username);

	public boolean usernameAlreadyExists(String username);

	public boolean emailAlreadyExists(String email);

}
