package main.java.is_test.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import main.java.is_test.model.User;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {

	/**
	 * This method will find an User instance in the database by its id. Note
	 * that this method is not implemented and its working code will be
	 * automagically generated from its signature by Spring Data JPA.
	 */
	public User findById(Long id);

}