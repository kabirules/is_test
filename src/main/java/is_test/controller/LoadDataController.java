package main.java.is_test.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import main.java.is_test.model.User;
import main.java.is_test.model.dao.UserDao;

@Controller
public class LoadDataController {

	/**
	 * GET /load-data --> Data load.
	 */
	@RequestMapping("/load-data")
	@ResponseBody
	@Cacheable("users")
	public String loadData() {
		Config cfg = new Config();
		HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
		Map<Long, User> mapUsers = instance.getMap("users");
		long start = System.currentTimeMillis();
		try {
			userDao.findAll();
			Iterable<User> itUsers = userDao.findAll();
			for (User userAux : itUsers) {
				mapUsers.put(userAux.getId(), userAux);
			}
		} catch (Exception ex) {
			// User not found; FIXME
			return "KO";
		}
		long elapsed = System.currentTimeMillis() - start;
		System.out.println("------------ Miliseconds ------------ " + elapsed);
		return "OK";
	}

	// Private fields

	@Autowired
	private UserDao userDao;

}
