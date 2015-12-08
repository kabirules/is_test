package main.java.is_test.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.java.is_test.model.User;
import main.java.is_test.model.dao.UserDao;

@Controller
public class UserController {

	/**
	 * GET /get-by-id --> Return the the user having the passed id.
	 */
	@RequestMapping("/get-by-id")
	@ResponseBody
	@Cacheable("users")
	public String getByName(String id) {
		long start = System.currentTimeMillis();
		User user = null;
		try {
			user = getUser(new Long(id));
		} catch (SQLException ex) {
			// User not found; FIXME
			System.out.println("Los catch vacios hacen llorar al niño Jesus");
		}
		long elapsed = System.currentTimeMillis() - start;
		List<User> usersList = new ArrayList<User>();
		usersList.add(user);
		JSONArray jsonArray = new JSONArray(usersList);
		String myJson = jsonArray.toString();
		System.out.println("------------ Miliseconds ------------ " + elapsed);
		return myJson;
	}

	private User getUser(Long id) throws SQLException {
		return userDao.findById(id);
	}

	// Private fields

	@Autowired
	private UserDao userDao;

}