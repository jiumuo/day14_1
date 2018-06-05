package test.dao;

import org.junit.Test;

import top.jiumu.user.dao.UserDao;
import top.jiumu.user.domain.User;

public class UserDaoTest {
	@Test
	public void testFindByUsernaem(){
		UserDao userDao = new UserDao();
		User user = userDao.findByUsername("赵六");
		System.out.println(user);
		
	}
	@Test
	public void testAdd(){
		UserDao userDao = new UserDao();
		User user = new User();
		
		user.setUsername("李四");
		user.setPassword("lisi");
		
		userDao.add(user);
	}
}
