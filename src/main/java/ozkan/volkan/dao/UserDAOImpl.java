package ozkan.volkan.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ozkan.volkan.model.*;

@Repository
public class UserDaoImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	// Register
	@Override
	public void addUser(Users user) {
		this.sessionFactory.getCurrentSession().save(user);
	}

	//Get All Users
	@Override
	public List<Users> getAllUsers() {
		return this.sessionFactory.getCurrentSession().createQuery("from Users").list();
	}
	
	// Get User
	@Override
	public Users getUserByUsername(String username) {
		return (Users) this.sessionFactory.getCurrentSession()
				.createCriteria(Users.class)
				.add(Restrictions.eqOrIsNull("username", username))
				.uniqueResult();
	}

	// Check Email If Exist
	@Override
	@Transactional
	public boolean emailAlreadyExists(String email) {

		int count = ((Long) this.sessionFactory.getCurrentSession()
				.createQuery("select count(*) from Users u WHERE u.email=:email_param")
				.setParameter("email_param", email)
				.uniqueResult()).intValue();
		
		return count > 0 ? true : false;
	}

	// Check Username If Exist
	@Override
	@Transactional
	public boolean usernameAlreadyExists(String username) {
		int count = ((Long) this.sessionFactory.getCurrentSession()
				.createQuery("select count(*) from Users u WHERE u.username=:username_param")
				.setParameter("username_param", username)
				.uniqueResult()).intValue();
		
		return count > 0 ? true : false;
	}
}
